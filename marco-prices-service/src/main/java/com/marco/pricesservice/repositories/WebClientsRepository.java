package com.marco.pricesservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.pricesservice.model.WebClientConfigDocument;

public interface WebClientsRepository extends MongoRepository<WebClientConfigDocument, String> {

}
