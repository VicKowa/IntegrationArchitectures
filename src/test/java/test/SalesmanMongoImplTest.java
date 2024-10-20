package test;

import de.hbrs.ia.code.MongoDBHandler;
import de.hbrs.ia.code.SalesmanMongoImpl;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;
import de.hbrs.ia.model.SpecifiedRecord;

import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @AfterEach
    void cleanUp() {
        MongoDBHandler.get().getDatabase().drop();
    }

    @Test
    void testCreateSalesMan() {
    }

    @Test
    void testAddSocialPerformanceRecord() {
        salesmanMongo.createSalesMan(salesMan);
        salesmanMongo.createSalesMan(salesMan2);
        salesmanMongo.createSalesMan(salesMan3);
        assertEquals(3, salesmanMongo.readAllSalesMen().size());
        assertEquals(0, salesmanMongo.readSocialPerformanceRecord(salesMan3).size());

        salesmanMongo.addSocialPerformanceRecord(socialPerformanceRecord, salesMan);
        assertEquals(1, salesmanMongo.readSocialPerformanceRecord(salesMan).size());

        salesmanMongo.addSocialPerformanceRecord(socialPerformanceRecord2, salesMan);
        assertEquals(2, salesmanMongo.readSocialPerformanceRecord(salesMan).size());

        salesmanMongo.addSocialPerformanceRecord(socialPerformanceRecord, salesMan2);
        assertEquals(1, salesmanMongo.readSocialPerformanceRecord(salesMan2).size());

        // check if the socialPerformanceRecord object is stored correctly
        assertEquals(socialPerformanceRecord, salesmanMongo.readSocialPerformanceRecord(salesMan).get(0));
        assertEquals(socialPerformanceRecord2, salesmanMongo.readSocialPerformanceRecord(salesMan).get(1));
        assertEquals(socialPerformanceRecord, salesmanMongo.readSocialPerformanceRecord(salesMan2).get(0));

    }

    @Test
    void testReadSalesMan() {

    }

    @Test
    void testReadAllSalesMen() {

    }

    @Test
    void testReadSocialPerformanceRecord() {

    }

    @Test
    void testRemoveSalesMan() {
        assertEquals(0, salesmanMongo.readAllSalesMen().size());

        salesmanMongo.createSalesMan(salesMan);
        assertEquals(1, salesmanMongo.readAllSalesMen().size());
        salesmanMongo.createSalesMan(salesMan2);
        assertEquals(2, salesmanMongo.readAllSalesMen().size());

        salesmanMongo.removeSalesMan(salesMan);
        assertEquals(1, salesmanMongo.readAllSalesMen().size());
        assertNull(salesmanMongo.readSalesMan(salesMan.getId()));

        salesmanMongo.removeSalesMan(salesMan2);
        assertEquals(0, salesmanMongo.readAllSalesMen().size());
        assertNull(salesmanMongo.readSalesMan(salesMan2.getId()));
    }

    @Test
    void testRemoveSocialPerformanceRecord() {

    }
}