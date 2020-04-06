package com.marco.securityservice.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.securityservice.model.RefreshTokenDocument;

/**
 * Standard Spring repository to access the RefreshDocument collection
 * 
 * @author msolina
 *
 */

public interface RefreshTokenDocumentRepository extends MongoRepository<RefreshTokenDocument, String> {
    Optional<RefreshTokenDocument> findByTokenId(String tokenId);
}
