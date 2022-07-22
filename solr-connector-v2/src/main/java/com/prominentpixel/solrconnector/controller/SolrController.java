package com.prominentpixel.solrconnector.controller;

import com.prominentpixel.solrconnector.provider.SolrClientProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

import static org.apache.solr.client.solrj.SolrQuery.ORDER.asc;
import static org.apache.solr.client.solrj.SolrQuery.ORDER.desc;

@RestController
@RequestMapping("/api/solr/{collection}")
@SuppressWarnings("all")
@Tag(name = "Solr", description = "Solr connector")
public class SolrController {

    @Value("${solr-host}")
    private String host;

    @Value("${solr-port}")
    private int port;

    private SolrClient client;

    private void connect(String collection) {
        this.client = SolrClientProvider.getInstance("http://" + this.host + ":" + this.port + "/solr/" + collection).getSolrClient();
    }

    @PreDestroy
    private void close() throws IOException {
        this.client.close();
    }

    @Operation(summary = "Gets all the documents.", description = "Gets all the documents.")
    @GetMapping("/docs")
    public List getAll(@PathVariable String collection,
                       @Parameter(description = "Rows to return") @RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows,
                       @Parameter(description = "Field to sort") @RequestParam(value = "sb", required = false, defaultValue = "id") String sortBy,
                       @Parameter(description = "Sort by in a specific order") @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
                       @Parameter(description = "Fields to list") @RequestParam(value = "fl", required = false, defaultValue = "*") String fields) throws Exception {
        this.connect(collection);
        return this.client.query(new SolrQuery("*:*")
                .setRows(rows)
                .addSort(sortBy, order.toLowerCase().equals("asc") ? asc : desc)
                .setFields(fields)).getResults();
    }

    @Operation(summary = "Gets a document by id.", description = "Gets a document by id.")
    @GetMapping("/docs/{id}")
    public SolrDocument get(@PathVariable String collection, @PathVariable String id) {
        this.connect(collection);
        try {
            return this.client.query(new SolrQuery("id:" + id)).getResults().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Operation(summary = "Deletes a document by id.", description = "Deletes a document by id.")
    @DeleteMapping("/docs/{id}")
    public void delete(@PathVariable String collection, @PathVariable String id) throws SolrServerException, IOException {
        this.connect(collection);
        this.client.deleteById(String.valueOf(id));
        this.client.commit();
    }

    @Operation(summary = "Updates a document by id.", description = "Updates a document by id.")
    @PutMapping("/docs/{id}")
    public void update(@PathVariable String collection, @PathVariable String id, @RequestBody SolrDocument requestBody) throws SolrServerException, IOException {
        this.connect(collection);
        this.addOrUpdate(id, requestBody, true);
    }

    @Operation(summary = "Adds a document.", description = "Adds a document.")
    @PostMapping("/docs")
    public void add(@PathVariable String collection, @RequestBody SolrDocument requestBody) throws SolrServerException, IOException {
        this.connect(collection);
        this.addOrUpdate(null, requestBody, false);
    }

    @Operation(summary = "Queries from the documents.", description = "Queries from the documents.")
    @GetMapping("/docs/select")
    public List query(@PathVariable String collection,
                      @Parameter(description = "Query to perform (Format : field:value)", required = true) @RequestParam("q") String query,
                      @Parameter(description = "Rows to return") @RequestParam(value = "rows", required = false, defaultValue = "20") Integer rows,
                      @Parameter(description = "Field to sort") @RequestParam(value = "sb", required = false, defaultValue = "id") String sortBy,
                      @Parameter(description = "Sort by in a specific order") @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
                      @Parameter(description = "Fields to list") @RequestParam(value = "fl", required = false, defaultValue = "*") String fields) throws Exception {
        this.connect(collection);
        return this.client.query(new SolrQuery(query)
                .setRows(rows)
                .addSort(sortBy, order.toLowerCase().equals("asc") ? asc : desc)
                .setFields(fields)).getResults();
    }

    private void addOrUpdate(String id, SolrDocument solrDocument, boolean shouldUpdate) throws SolrServerException, IOException {
        SolrInputDocument document = new SolrInputDocument();
        solrDocument.forEach((key, value) -> document.addField(key, value));
        document.remove("_version_"); // To prevent user changing the version
        if (shouldUpdate) {
            document.setField("id", id);
        }
        new UpdateRequest().add(document).setAction(UpdateRequest.ACTION.COMMIT, false, false).process(this.client);
    }
}
