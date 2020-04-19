package com.marco.ingridientsservice.repositories.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.ingridientsservice.model.nosql.WebClientConfigDocument;

public interface WebClientsRepository extends MongoRepository<WebClientConfigDocument, String> {

}
