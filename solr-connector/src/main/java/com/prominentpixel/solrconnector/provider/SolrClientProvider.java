package com.prominentpixel.solrconnector.provider;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

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
}
