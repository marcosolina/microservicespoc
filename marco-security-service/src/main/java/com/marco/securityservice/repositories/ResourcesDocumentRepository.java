package com.marco.securityservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.securityservice.model.ResourcesDocument;

/**
 * Standard Spring repository to access the ResourceDocument collection
 * 
 * @author msolina
 *
 */
public interface ResourcesDocumentRepository extends MongoRepository<ResourcesDocument, String> {

}
