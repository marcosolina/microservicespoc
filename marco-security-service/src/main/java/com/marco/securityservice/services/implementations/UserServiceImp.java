package com.marco.securityservice.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.marco.securityservice.model.UserDocument;
import com.marco.securityservice.services.interfaces.UserServiceInt;
import com.marco.securityservice.utils.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;

public class UserServiceImp implements UserServiceInt {

    @Autowired
    private MongoClient dbClient;

    @Value("${spring.data.mongodb.database}")
    private String dbName;
    @Value("${com.ixico.database.collenctions.users}")
    private String collentionName;

    private <T> MongoCollection<T> getMongoDbCollention(String cName, Class<T> documentClass) {
        MongoDatabase db = dbClient.getDatabase(dbName).withCodecRegistry(Utils.pojoCodecRegistry);
        return db.getCollection(cName, documentClass);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MongoCollection<UserDocument> collection = getMongoDbCollention(collentionName, UserDocument.class);
        UserDocument document = collection.find(Filters.eq("userName", username), UserDocument.class).first();
        if (document == null) {
            throw new UsernameNotFoundException("Ivalid user or password");
        }

        List<SimpleGrantedAuthority> authorities = document.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new User(username, document.getPassword(), authorities);

    }

    @Override
    public List<UserDocument> findAllUsers() {
        MongoCollection<UserDocument> collection = getMongoDbCollention(collentionName, UserDocument.class);
        FindIterable<UserDocument> result = collection.find();

        List<UserDocument> documents = new ArrayList<>();
        MongoCursor<UserDocument> cursor = result.cursor();
        while (cursor.hasNext()) {
            documents.add(cursor.next());
        }

        return documents;
    }

    @Override
    public boolean insertUser(UserDocument user) {
        MongoCollection<UserDocument> collection = getMongoDbCollention(collentionName, UserDocument.class);
        collection.insertOne(user);
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        MongoCollection<UserDocument> collection = getMongoDbCollention(collentionName, UserDocument.class);

        BasicDBObject query = new BasicDBObject();
        query.put("userName", userId);
        DeleteResult result = collection.deleteOne(query);

        return result.getDeletedCount() != 0;
    }

}
