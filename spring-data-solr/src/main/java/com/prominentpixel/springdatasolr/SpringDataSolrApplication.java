package com.prominentpixel.springdatasolr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@SpringBootApplication
@EnableSolrRepositories
public class SpringDataSolrApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataSolrApplication.class, args);
	}

}
