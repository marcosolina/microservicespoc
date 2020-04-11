package com.marco.marcoreactui.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.marcoreactui.model.ResourcesDocument;

public interface ResourcesDocumentRepository extends MongoRepository<ResourcesDocument, String>{

}
