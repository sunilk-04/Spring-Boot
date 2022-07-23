package com.prominentpixel.solrconnector.provider;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.client.solrj.response.CoreAdminResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.params.CoreAdminParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public class SolrClientProvider {

    private SolrClient solrClient;

    private SolrClientProvider(String url) {
        this.solrClient = new HttpSolrClient.Builder(url).build();
    }

    public static SolrClientProvider getInstance(String url) {
        return new SolrClientProvider(url);
    }

    public SolrClient getSolrClient() {
        return this.solrClient;
    }

    public List<String> getCollections() {
        CoreAdminRequest request = new CoreAdminRequest();
        request.setAction(CoreAdminParams.CoreAdminAction.STATUS);
        CoreAdminResponse cores = null;
        try {
            cores = request.process(this.solrClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> coreList = new ArrayList<>();
        // coreList.add("");
        for (int i = 0; i < Objects.requireNonNull(cores).getCoreStatus().size(); i++) {
            coreList.add(cores.getCoreStatus().getName(i));
        }
        return coreList;
    }

    public List<String> getFields(String collection, String url) throws SolrServerException, IOException {
        SolrClient client = new HttpSolrClient.Builder(url + "/" + collection).build();
        SolrQuery query = new SolrQuery();
        query.add(CommonParams.QT, "/schema/fields");
        QueryResponse response = client.query(query);
        NamedList responseHeader = response.getResponseHeader();
        ArrayList<SimpleOrderedMap> fields = (ArrayList<SimpleOrderedMap>) response.getResponse().get("fields");
        List<String> fieldList = new ArrayList<>();
        // fieldList.add("");
        for (SimpleOrderedMap field : fields) {
            fieldList.add(field.get("name").toString());
        }
        return fieldList.stream().filter(field -> !field.startsWith("_")).collect(Collectors.toList());
    }
}
