package de.hbrs.ia.code;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBHandler {

    static MongoDBHandler instance = null;

    private MongoClient client = null;
    private MongoDatabase database = null;

    private MongoDBHandler() {
    }

    /**
     * Get the MongoDBHandler instance (singleton)
     *
     * @return the MongoDBHandler instance
     * */
    public static MongoDBHandler get() {
        if (instance == null) {
            instance = new MongoDBHandler();
        }
        return instance;
    }

    /**
     * Set up a connection to the MongoDB database
     *
     * @param host the host of the database
     * @param port the port of the database
     * @param databaseName the name of the database
     * */
    public void setupConnection(String host, int port, String databaseName) {
        if (client != null || database != null)
            return;

        client = new MongoClient(host, port);
        database = client.getDatabase(databaseName);
    }

    /**
     * Terminate the connection to the MongoDB database
     * */
    public void terminateConnection() {
        if (client == null)
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
