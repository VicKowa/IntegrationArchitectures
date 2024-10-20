package de.hbrs.ia.model;

import java.util.Objects;
import org.bson.Document;

import java.util.Date;

public class SocialPerformanceRecord {
    private String department;
    private double totalBonus;
    private Date year;
    private SpecifiedRecord leadershipCompetence;
    private SpecifiedRecord opennessToEmployee;
    private SpecifiedRecord socialbehaviorToEmployee;
    private SpecifiedRecord attitudeToClients;
    private SpecifiedRecord communicationSkills;
    private SpecifiedRecord integrityToCompany;

    public SocialPerformanceRecord(String department,
                                   double totalBonus,
                                   Date year,
                                   SpecifiedRecord leadershipCompetence,
                                   SpecifiedRecord opennessToEmployee,
                                   SpecifiedRecord socialbehaviorToEmployee,
                                   SpecifiedRecord attitudeToClients,
                                   SpecifiedRecord communicationSkills,
                                   SpecifiedRecord integrityToCompany) {
        this.department = department;
        this.totalBonus = totalBonus;
        this.year = year;
        this.leadershipCompetence = leadershipCompetence;
        this.opennessToEmployee = opennessToEmployee;
        this.socialbehaviorToEmployee = socialbehaviorToEmployee;
        this.attitudeToClients = attitudeToClients;
        this.communicationSkills = communicationSkills;
        this.integrityToCompany = integrityToCompany;
    }


    // Getters and Setters
    public SpecifiedRecord getLeadershipCompetence() {
        return leadershipCompetence;
    }

    public SpecifiedRecord getOpennessToEmployee() {
        return opennessToEmployee;
    }

    public SpecifiedRecord getSocialbehaviorToEmployee() {
        return socialbehaviorToEmployee;
    }

    public SpecifiedRecord getAttitudeToClients() {
        return attitudeToClients;
    }

    public SpecifiedRecord getCommunicationSkills() {
        return communicationSkills;
    }

    public SpecifiedRecord getIntegrityToCompany() {
        return integrityToCompany;
    }

    public String getDepartment() {
        return department;
    }

    public double getTotalBonus() {
        return totalBonus;
    }

    public Date getYear() {
        return year;
    }

    public Document toDocument() {
        return new Document("department", department)
                .append("totalBonus", totalBonus)
                .append("year", year)
                .append("leadershipCompetence", leadershipCompetence.toDocument())
                .append("opennessToEmployee", opennessToEmployee.toDocument())
                .append("socialbehaviorToEmployee", socialbehaviorToEmployee.toDocument())
                .append("attitudeToClients", attitudeToClients.toDocument())
                .append("communicationSkills", communicationSkills.toDocument())
                .append("integrityToCompany", integrityToCompany.toDocument());

    }

    /**
     * transform one SocrialPerformanceRecord as BsonDocument to a SocialPerformanceRecord object
     *
     * @param document One SocialPerformanceRecord as BsonDocument
     * @return
     */
    public static SocialPerformanceRecord documentToSocialPerformanceRecord(Document document) {
        String department = document.getString("department");
        double totalBonus = document.getDouble("totalBonus");
        Date year = document.getDate("year");
        SpecifiedRecord leadershipCompetence = SpecifiedRecord.documentToSpecifiedRecord(document.get("leadershipCompetence", Document.class));
        SpecifiedRecord opennessToEmployee = SpecifiedRecord.documentToSpecifiedRecord(document.get("opennessToEmployee", Document.class));
        SpecifiedRecord socialbehaviorToEmployee = SpecifiedRecord.documentToSpecifiedRecord(document.get("socialbehaviorToEmployee", Document.class));
        SpecifiedRecord communicationSkills = SpecifiedRecord.documentToSpecifiedRecord(document.get("communicationSkills", Document.class));
        SpecifiedRecord attitudeToClients = SpecifiedRecord.documentToSpecifiedRecord(document.get("attitudeToClients", Document.class));
        SpecifiedRecord integrityToCompany = SpecifiedRecord.documentToSpecifiedRecord(document.get("integrityToCompany", Document.class));

        return new SocialPerformanceRecord(department, totalBonus, year, leadershipCompetence, opennessToEmployee, socialbehaviorToEmployee, attitudeToClients, communicationSkills, integrityToCompany);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialPerformanceRecord that = (SocialPerformanceRecord) o;
        return Objects.equals(leadershipCompetence, that.leadershipCompetence) && Objects.equals(opennessToEmployee, that.opennessToEmployee) && Objects.equals(socialbehaviorToEmployee, that.socialbehaviorToEmployee) && Objects.equals(attitudeToClients, that.attitudeToClients) && Objects.equals(communicationSkills, that.communicationSkills) && Objects.equals(integrityToCompany, that.integrityToCompany);
    }
}