package de.hbrs.ia.code;

import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SocialPerformanceRecord;

import java.util.List;

/**
 * Code lines are commented for suppressing compile errors.
 * Are there any CRUD-operations missing?
 */
public interface ManagePersonal {
    void createSalesMan(SalesMan record);

    void addSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan);

    SalesMan readSalesMan(int sid);

    List<SalesMan> readAllSalesMen();

    List<SocialPerformanceRecord> readSocialPerformanceRecord(SalesMan salesMan);

    void removeSalesMan(SalesMan record);

    void removeSocialPerformanceRecord(SocialPerformanceRecord record, SalesMan salesMan);
}
