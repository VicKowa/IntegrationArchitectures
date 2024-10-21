package de.hbrs.ia.code;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;

import org.bson.Document;

import java.util.List;
import java.util.ArrayList;

public class SalesmanMongoImpl implements ManagePersonal {

    /**
     * Creates a new SalesMan record in the database.
     *
     * @param record SalesMan object to be created
     */
    @Override
    public void createSalesMan(SalesMan record) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");

        // check if SalesMan with sid already exists
        Document filter = new Document("sid", record.getId());
        Document salesman = salesmenCollection.find(filter).first();

        if (salesman != null) {
            System.out.println("Salesman with sid " + record.getId() + " already exists");
            return;
        }

        // create empty socialPerformanceRecords
        ArrayList<SocialPerformanceRecord> socialPerformanceRecords = new ArrayList<>();

        // create new salesMan with empty socialPerformanceRecords
        Document query = record.toDocument();
        query.append("socialPerformanceRecords", socialPerformanceRecords);

        // insert new salesMan into database
        salesmenCollection.insertOne(query);
    }

    /**
     * Adds a new SocialPerformanceRecord to a SalesMan record in the database.
     *
     * @param record SocialPerformanceRecord object to be added
     * @param salesMan SalesMan object to which the record is added
     */
    @Override
    public void addSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");

        // find correct salesMan by sid
        Document filter = new Document("sid", salesMan.getId());

        // update socialPerformanceRecords field with new record
        salesmenCollection.updateOne(
                filter,
                Updates.addToSet("socialPerformanceRecords", record.toDocument())
        );
    }

    /**
     * Reads a SalesMan record from the database.
     *
     * @param sid ID of the SalesMan record to be read
     * @return SalesMan object read from the database
     */
    @Override
    public SalesMan readSalesMan(int sid) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> collection = database.getCollection("salesmen");

        Document filter = new Document("sid", sid);

        // find salesman by sid
        Document salesman = collection.find(filter).first();

        if (salesman == null)
            return null;

        return documentToSalesMan(salesman);
    }

    /**
     * Converts a Document object to a SalesMan object.
     *
     * @param document Document object to be converted
     * @return SalesMan object converted from the Document object
     */
    private SalesMan documentToSalesMan(Document document) {
        String firstname = document.getString("firstname");
        String lastname = document.getString("lastname");
        Integer sid = document.getInteger("sid");

        return new SalesMan(firstname, lastname, sid);
    }

    /**
     * Reads all SalesMan records from the database.
     *
     * @return List of SalesMan objects read from the database
     */
    @Override
    public List<SalesMan> readAllSalesMen() {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> collection = database.getCollection("salesmen");

        List<SalesMan> result = new ArrayList<>();

        // get all salesmen
        FindIterable<Document> salesmen = collection.find();

        // for each salesman convert the document to a SalesMan object
        salesmen.forEach(document -> {
            result.add(documentToSalesMan(document));
        });

        return result;
    }

    /**
     * Reads all SocialPerformanceRecord records from a SalesMan record in the database.
     *
     * @param salesMan SalesMan object from which the records are read
     * @return List of SocialPerformanceRecord objects read from the database
     */

    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> collection = database.getCollection("salesmen");

        Document filter = new Document("sid", salesMan.getId());

        List<SocialPerformanceRecord> result = new ArrayList<>();
        Document salesman = collection.find(filter).first();
        salesman.getList("socialPerformanceRecords", Document.class).forEach(record -> {
            result.add(SocialPerformanceRecord.documentToSocialPerformanceRecord(record));
        });
        return result;
    }

    /**
     * Removes a SalesMan record from the database.
     *
     * @param record SalesMan object to be removed
     */
    @Override
    public void removeSalesMan(SalesMan record) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");

        Document filter = new Document("sid", record.getId());

        salesmenCollection.deleteOne(filter);
    }

    /**
     * Removes a SocialPerformanceRecord record from a SalesMan record in the database.
     *
     * @param record SocialPerformanceRecord object to be removed
     * @param salesMan SalesMan object from which the record is removed
     */
    @Override
    public void removeSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");

        Document filter = new Document("sid", salesMan.getId());

        // remove record from socialPerformanceRecords field
        salesmenCollection.updateOne(
                filter,
                Updates.pull("socialPerformanceRecords", record.toDocument())
        );
    }
}
