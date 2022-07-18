package com.prominentpixel.springdatasolr.repository;

import com.prominentpixel.springdatasolr.model.Employee;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface EmployeeRepository extends SolrCrudRepository<Employee, Integer> {
}
