package com.marco.menuservice.repositories.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.menuservice.model.nosql.WebClientConfigDocument;

public interface WebClientsRepository extends MongoRepository<WebClientConfigDocument, String> {

}
