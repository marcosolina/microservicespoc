package com.marco.securityservice.utils;

import org.apache.commons.codec.binary.Base64;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

import com.mongodb.MongoClientSettings;

/**
 * Utils class
 * 
 * @author msolina
 *
 */
public class Utils {
    private static Logger LOGGER = LoggerFactory.getLogger(Utils.class);

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

    /**
     * It serialises the input object into a Base64 string
     * 
     * @param <T>
     * @param object
     * @return
     */
    public static <T> String serializeObject(T object) {
        if (object == null) {
            return null;
        }
        LOGGER.trace("Encoding object");
        byte[] bytes = SerializationUtils.serialize(object);
        return Base64.encodeBase64String(bytes);
    }

    /**
     * IT de-serialises the Base64 input string into an instance of that object
     * 
     * @param <T>
     * @param clazz
     * @param encodedObject
     * @return
     */
    public static <T> T deserializeObject(Class<T> clazz, String encodedObject) {
        if (encodedObject == null) {
            return null;
        }
        LOGGER.trace("Decoding object");
        byte[] bytes = Base64.decodeBase64(encodedObject);
        return clazz.cast(SerializationUtils.deserialize(bytes));
    }
}
