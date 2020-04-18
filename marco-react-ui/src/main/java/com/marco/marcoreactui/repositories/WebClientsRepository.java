package com.marco.marcoreactui.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.marcoreactui.model.WebClientConfigDocument;

public interface WebClientsRepository extends MongoRepository<WebClientConfigDocument, String> {

}
