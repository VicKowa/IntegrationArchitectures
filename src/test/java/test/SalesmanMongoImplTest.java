package test;

import de.hbrs.ia.code.MongoDBHandler;
import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import de.hbrs.ia.model.SpecifiedRecord;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesmanMongoImplTest {

    static SalesmanMongoImpl salesmanMongo = null;

    // sample data for 5 SalesMan, 2 SocialPerformanceRecord, and 4 SpecifiedRecord
    SalesMan salesMan = new SalesMan("John", "Doe", 9090);
    SalesMan salesMan2 = new SalesMan("Jane", "Doe", 9091);
    SalesMan salesMan3 = new SalesMan("John", "Smith", 9092);
    SalesMan salesMan4 = new SalesMan("Jane", "Smith", 9093);
    SalesMan salesMan5 = new SalesMan("John", "Doe", 9094);

    SpecifiedRecord leadershipCompetence = new SpecifiedRecord(100, 90, 10);
    SpecifiedRecord opennessToEmployee = new SpecifiedRecord(100, 90, 10);
    SpecifiedRecord socialbehaviorToEmployee = new SpecifiedRecord(100, 90, 10);
    SpecifiedRecord attitudeToClients = new SpecifiedRecord(100, 90, 10);
    SpecifiedRecord communicationSkills = new SpecifiedRecord(100, 90, 10);
    SpecifiedRecord integrityToCompany = new SpecifiedRecord(100, 90, 10);

    SocialPerformanceRecord socialPerformanceRecord = new SocialPerformanceRecord(
            "dep_1",
            60,
            new Date(2025, 1, 12),
            leadershipCompetence,
            opennessToEmployee,
            socialbehaviorToEmployee,
            attitudeToClients,
            communicationSkills,
            integrityToCompany);

    SocialPerformanceRecord socialPerformanceRecord2 = new SocialPerformanceRecord(
            "dep_2",
            100,
            new Date(2024, 11, 21),
            leadershipCompetence,
            opennessToEmployee,
            socialbehaviorToEmployee,
            attitudeToClients,
            communicationSkills,
            integrityToCompany);


    @BeforeAll
    static void setup() {
        MongoDBHandler.get().setupConnection("localhost", 27017, "SalesmanMongoImplTest");
        salesmanMongo = new SalesmanMongoImpl();
    }

    @AfterAll
    static void terminate() {
        MongoDBHandler.get().terminateConnection();
    }

    @Test
    void testCreateSalesMan() {
    }

    @Test
    void testAddSocialPerformanceRecord() {
    }

    @Test
    void testReadSalesMan() {

    }

    @Test
    void testReadAllSalesMen() {
        assertTrue(salesmanMongo.readAllSalesMen().isEmpty());

        List<SalesMan> salesMen = Arrays.asList(salesMan, salesMan2, salesMan3, salesMan4, salesMan5);

        for (int i = 0; i < salesMen.size(); i++) {
            salesmanMongo.createSalesMan(salesMan);

            // check if the SalesMan is added
            assertEquals(i + 1, salesmanMongo.readAllSalesMen().size());
            assertEquals(salesMen.get(i), salesmanMongo.readAllSalesMen().get(i));
        }
    }

    @Test
    void testReadSocialPerformanceRecord() {

    }

    @Test
    void testRemoveSalesMan() {

    }

    @Test
    void testRemoveSocialPerformanceRecord() {
        // create a SalesMan and add two SocialPerformanceRecord
        salesmanMongo.createSalesMan(salesMan);
        salesmanMongo.addSocialPerformanceRecord(socialPerformanceRecord, salesMan);
        salesmanMongo.addSocialPerformanceRecord(socialPerformanceRecord2, salesMan);

        // check if the SocialPerformanceRecord are added
        assertEquals(2, salesmanMongo.readSocialPerformanceRecord(salesMan).size());
        assertEquals(socialPerformanceRecord, salesmanMongo.readSocialPerformanceRecord(salesMan).get(0));
        assertEquals(socialPerformanceRecord2, salesmanMongo.readSocialPerformanceRecord(salesMan).get(1));

        salesmanMongo.removeSocialPerformanceRecord(socialPerformanceRecord, salesMan);

        // check if the SocialPerformanceRecord is removed
        assertEquals(1, salesmanMongo.readSocialPerformanceRecord(salesMan).size());
        assertEquals(socialPerformanceRecord2, salesmanMongo.readSocialPerformanceRecord(salesMan).get(0));

        salesmanMongo.removeSocialPerformanceRecord(socialPerformanceRecord2, salesMan);

        assertTrue(salesmanMongo.readSocialPerformanceRecord(salesMan).isEmpty());
    }
}