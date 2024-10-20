package de.hbrs.ia.code;

import com.mongodb.client.model.Updates;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;
import java.util.ArrayList;

public class SalesmanMongoImpl implements ManagePersonal {

    @Override
    public void createSalesMan(SalesMan record) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");

        // create empty socialPerformanceRecords
        ArrayList<SocialPerformanceRecord> socialPerformanceRecords = new ArrayList<>();

        // create new salesMan with empty socialPerformanceRecords
        Document query = record.toDocument();
        query.append("socialPerformanceRecords", socialPerformanceRecords);

        // insert new salesMan into database
        salesmenCollection.insertOne(query);
    }

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

    @Override
    public SalesMan readSalesMan(int sid) {
        return null;
    }

    @Override
    public List<SalesMan> readAllSalesMen() {
        return List.of();
    }

    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        return List.of();
    }
}
