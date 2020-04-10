package com.marco.securityservice.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marco.securityservice.model.AccessTokenDocument;

/**
 * Standard Spring repository to access the AccessTokenDocument collection
 * 
 * @author msolina
 *
 */
public interface AccessTokenDocumentRepository extends MongoRepository<AccessTokenDocument, String> {
    List<AccessTokenDocument> findByClientId(String clientId);

    List<AccessTokenDocument> findByClientIdAndUsername(String clientId, String username);

    Optional<AccessTokenDocument> findByTokenId(String tokenId);

    Optional<AccessTokenDocument> findByRefreshToken(String refreshToken);

    Optional<AccessTokenDocument> findByAuthenticationId(String authenticationId);
}
