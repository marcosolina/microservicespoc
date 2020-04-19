package com.marco.pricesservice.repositories.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.pricesservice.model.nosql.WebClientConfigDocument;

public interface WebClientsRepository extends MongoRepository<WebClientConfigDocument, String> {

}
