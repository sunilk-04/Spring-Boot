package com.prominentpixel.solrconnector.controller;

import com.google.gson.GsonBuilder;
import com.prominentpixel.solrconnector.provider.SolrClientProvider;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SuppressWarnings("all")
public class SolrController {

    @Value("${solr-host}")
    private String host;

    @Value("${solr-port}")
    private int port;

    @Autowired
    private Environment environment;

    @GetMapping("/")
    public String home(Model model, @RequestParam(name = "collection", defaultValue = "") String collection) throws SolrServerException, IOException {
        String url = "http://" + this.host + ":" + this.port + "/solr/";
        SolrClientProvider provider = SolrClientProvider.getInstance(url);
        model.addAttribute("collections", provider.getCollections());
        model.addAttribute("fields", collection.equals("") ? new ArrayList<>() : provider.getFields(collection, url));
        return "index";
    }

    @GetMapping("/query")
    public String execute(@RequestParam(name = "fields", defaultValue = "") String field,
                          @RequestParam(name = "query", defaultValue = "") String query,
                          @RequestParam(name = "collections", defaultValue = "") String collection,
                          @RequestParam(name = "rows", defaultValue = "20") Integer rows,
                          Model model) throws Exception {
        String url = "http://" + this.host + ":" + this.port + "/solr/";
        SolrClient client = SolrClientProvider.getInstance(url + collection).getSolrClient();
        SolrClientProvider provider = SolrClientProvider.getInstance(url);
        model.addAttribute("collections", provider.getCollections());
        model.addAttribute("fields", collection.equals("") ? new ArrayList<>() : provider.getFields(collection, url));
        if (!(field.equals("") || query.equals(""))) {
            RestTemplate template = new RestTemplate();
            String restUrl = "http://" + this.host + ":" + this.environment.getProperty("local.server.port") + "/api/solr/" + collection + "/docs/select?q=" + field + ":" + query + "&rows=" + rows;
            List result = template.getForObject(restUrl, List.class);
            model.addAttribute("result", this.preetify(result));
        }
        client.close();
        return "index";
    }

    private String preetify(List response) throws Exception {
        return new GsonBuilder().setPrettyPrinting().create().toJson(response);
    }
}
