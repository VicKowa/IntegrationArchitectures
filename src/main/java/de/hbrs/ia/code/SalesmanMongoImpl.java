package de.hbrs.ia.code;

import com.mongodb.client.FindIterable;
import de.hbrs.ia.model.SpecifiedRecord;
import org.bson.BsonArray;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import com.mongodb.MongoClient;
import com.mongodb.client.model.Updates;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

import static com.mongodb.client.model.Filters.eq;

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
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> collection = database.getCollection("salesmen");

        Document salesman = collection.find(eq("sid", sid)).first();
        return documentToSalesMan(salesman);
    }

    /**
     * helper method to convert a document to a SalesMan object
     *
     * @param document
     * @return
     */
    private SalesMan documentToSalesMan(Document document) {
        String firstname = document.getString("firstname");
        String lastname = document.getString("lastname");
        Integer sid = document.getInteger("sid");

        return new SalesMan(firstname, lastname, sid);
    }

    @Override
    public List<SalesMan> readAllSalesMen() {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> collection = database.getCollection("salesmen");

        List<SalesMan> result = new LinkedList<>();
        //get all salesmen
        FindIterable<Document> salesmen = collection.find();
        // for each salesman convert the document to a SalesMan object
        salesmen.forEach(document -> {
            result.add(documentToSalesMan(document));
        });
        return result;
    }

    @Override
    public List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> collection = database.getCollection("salesmen");

        List<SocialPerformanceRecord> result = new LinkedList<>();
        Document salesman = collection.find(eq("sid", salesMan.getId())).first();
        BsonArray records = salesman.get("socialPerformanceRecords", BsonArray.class);
        records.forEach(record -> {
            List<SocialPerformanceRecord> socialPerformanceRecords = new LinkedList<>();
            socialPerformanceRecords.add(documentToSocialPerformanceRecord(record.asDocument()));
        });
        return result;
    }

    /**
     * transform one SocrialPerformanceRecord as BsonDocument to a SocialPerformanceRecord object
     *
     * @param document One SocialPerformanceRecord as BsonDocument
     * @return
     */
    private SocialPerformanceRecord documentToSocialPerformanceRecord(BsonDocument document) {
        String department = document.getString("department").getValue();
        float totalBonus = (float) document.get("totalBonus").asDouble().getValue();
        String year = document.getString("year").getValue();
        Date date = new Date(year);
        SpecifiedRecord leadershipCompetence = documentToSpecifiedRecord(document.getDocument("leadershipCompetence"));
        SpecifiedRecord opennessToEmployee = documentToSpecifiedRecord(document.getDocument("opennessToEmployee"));
        SpecifiedRecord socialbehaviorToEmployee = documentToSpecifiedRecord(document.getDocument("socialbehaviorToEmployee"));
        SpecifiedRecord communicationSkills = documentToSpecifiedRecord(document.getDocument("communicationSkills"));
        SpecifiedRecord attitudeToClients = documentToSpecifiedRecord(document.getDocument("attitudeToClients"));
        SpecifiedRecord integrityToCompany = documentToSpecifiedRecord(document.getDocument("integrityToCompany"));

        return new SocialPerformanceRecord(department, totalBonus, date, leadershipCompetence, opennessToEmployee, socialbehaviorToEmployee, attitudeToClients, communicationSkills, integrityToCompany);

    }

    /**
     * transform one SocrialPerformanceRecord as BsonDocument to a SocialPerformanceRecord object
     *
     * @param document One SocialPerformanceRecord as BsonDocument
     * @return
     */
    private SpecifiedRecord documentToSpecifiedRecord(BsonDocument document) {
        int targetValue = document.get("targetValue").asInt32().getValue();
        int actualValue = document.get("actualValue").asInt32().getValue();
        int bonus = document.get("bonus").asInt32().getValue();
        return new SpecifiedRecord(targetValue, actualValue, bonus);
    }

    @Override
    public void removeSalesMan(SalesMan record) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");
        salesmenCollection.deleteOne(eq("sid", record.getId()));
    }

    @Override
    public void removeSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan) {
        MongoDatabase database = MongoDBHandler.get().getDatabase();
        MongoCollection<Document> salesmenCollection = database.getCollection("salesmen");

        Document salesMenDoc = salesmenCollection.find(eq("sid", salesMan.getId())).first();

        if (salesMenDoc == null)
            return;

        List<SocialPerformanceRecord> socialPerformanceRecords = (List<SocialPerformanceRecord>) salesMenDoc.get("socialPerformanceRecords");

        if (socialPerformanceRecords == null)
            return;

        socialPerformanceRecords.remove(record);

        salesmenCollection.updateOne(
                eq("sid", salesMan.getId()),
                new Document("$set", new Document("socialPerformanceRecords", socialPerformanceRecords))
        );
    }
}
