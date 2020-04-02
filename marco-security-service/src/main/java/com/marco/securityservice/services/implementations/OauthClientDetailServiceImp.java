package com.marco.securityservice.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import com.marco.securityservice.model.OauthClientsDocument;
import com.marco.securityservice.services.interfaces.OauthClientDetailServiceInt;
import com.marco.securityservice.utils.Utils;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class OauthClientDetailServiceImp implements OauthClientDetailServiceInt {
    @Autowired
    private MongoClient dbClient;

    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Value("${com.ixico.database.collenctions.oauthclients}")
    private String collentionName;

    private <T> MongoCollection<T> getMongoDbCollention(String cName, Class<T> documentClass) {
        MongoDatabase db = dbClient.getDatabase(dbName).withCodecRegistry(Utils.pojoCodecRegistry);
        return db.getCollection(cName, documentClass);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        MongoCollection<OauthClientsDocument> collection = getMongoDbCollention(collentionName, OauthClientsDocument.class);
        OauthClientsDocument document = collection.find(Filters.eq("client_id", clientId), OauthClientsDocument.class).first();
        if (document == null) {
            throw new ClientRegistrationException("Oauth client not found");
        }

        BaseClientDetails cd = new BaseClientDetails();
        cd.setClientId(document.getClient_id());
        cd.setClientSecret(document.getClient_secret());
        cd.setAuthorizedGrantTypes(document.getAuthorized_grant_types());
        cd.setScope(document.getScopes());
        cd.setAccessTokenValiditySeconds(document.getAccess_token_validity());
        cd.setRefreshTokenValiditySeconds(document.getRefresh_token_validity());
        return cd;
    }

}
