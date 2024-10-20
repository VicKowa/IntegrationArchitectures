package de.hbrs.ia.model;

import java.util.Objects;

public class SocialPerformanceRecord {
    private SpecifiedRecord leadershipCompetence;
    private SpecifiedRecord opennessToEmployee;
    private SpecifiedRecord socialbehaviorToEmployee;
    private SpecifiedRecord attitudeToClients;
    private SpecifiedRecord communicationSkills;
    private SpecifiedRecord integrityToCompany;

    // Getters and Setters
    public SpecifiedRecord getLeadershipCompetence() {
        return leadershipCompetence;
    }

    public void setLeadershipCompetence(SpecifiedRecord leadershipCompetence) {
        this.leadershipCompetence = leadershipCompetence;
    }

    public SpecifiedRecord getOpennessToEmployee() {
        return opennessToEmployee;
    }

    public void setOpennessToEmployee(SpecifiedRecord opennessToEmployee) {
        this.opennessToEmployee = opennessToEmployee;
    }

    public SpecifiedRecord getSocialbehaviorToEmployee() {
        return socialbehaviorToEmployee;
    }

    public void setSocialbehaviorToEmployee(SpecifiedRecord socialbehaviorToEmployee) {
        this.socialbehaviorToEmployee = socialbehaviorToEmployee;
    }

    public SpecifiedRecord getAttitudeToClients() {
        return attitudeToClients;
    }

    public void setAttitudeToClients(SpecifiedRecord attitudeToClients) {
        this.attitudeToClients = attitudeToClients;
    }

    public SpecifiedRecord getCommunicationSkills() {
        return communicationSkills;
    }

    public void setCommunicationSkills(SpecifiedRecord communicationSkills) {
        this.communicationSkills = communicationSkills;
    }

    public SpecifiedRecord getIntegrityToCompany() {
        return integrityToCompany;
    }

    public void setIntegrityToCompany(SpecifiedRecord integrityToCompany) {
        this.integrityToCompany = integrityToCompany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialPerformanceRecord that = (SocialPerformanceRecord) o;
        return Objects.equals(leadershipCompetence, that.leadershipCompetence) && Objects.equals(opennessToEmployee, that.opennessToEmployee) && Objects.equals(socialbehaviorToEmployee, that.socialbehaviorToEmployee) && Objects.equals(attitudeToClients, that.attitudeToClients) && Objects.equals(communicationSkills, that.communicationSkills) && Objects.equals(integrityToCompany, that.integrityToCompany);
    }
}
