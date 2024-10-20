package de.hbrs.ia.code;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBHandler {

    static MongoDBHandler instance = null;

    private MongoClient client = null;
    private MongoDatabase database = null;

    private MongoDBHandler() {
    }

    public static MongoDBHandler get() {
        if (instance == null) {
            instance = new MongoDBHandler();
        }
        return instance;
    }

    public void setupConnection(String host, int port, String databaseName) {
        if (client != null || database != null)
            return;

        client = new MongoClient(host, port);
        database = client.getDatabase(databaseName);
    }

    public void terminateConnection() {
        if (client != null)
            return;

        database = null;
        client.close();
    }

    public MongoClient getClient() {
        return client;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
