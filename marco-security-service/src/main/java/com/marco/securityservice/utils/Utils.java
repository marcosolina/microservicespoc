package com.marco.securityservice.utils;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;

public class Utils {
    private Utils() {
    }

    /**
     * This let's you convert a MongoDB Document into a Java POJO
     * 
     * @see <a href=
     *      "http://mongodb.github.io/mongo-java-driver/3.7/driver/getting-started/quick-start-pojo/">Mongo
     *      DB Driver quick start</>
     * 
     */
    public static final CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
}
