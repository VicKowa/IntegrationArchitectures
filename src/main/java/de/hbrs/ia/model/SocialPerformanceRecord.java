package de.hbrs.ia.model;

import org.bson.Document;

import java.util.Date;

public class SocialPerformanceRecord {
    private String department;
    private float totalBonus;
    private Date year;
    private SpecifiedRecord leadershipCompetence;
    private SpecifiedRecord opennessToEmployee;
    private SpecifiedRecord socialbehaviorToEmployee;
    private SpecifiedRecord attitudeToClients;
    private SpecifiedRecord communicationSkills;
    private SpecifiedRecord integrityToCompany;

    public SocialPerformanceRecord(String department,
                                   float totalBonus,
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

    public float getTotalBonus() {
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
}
