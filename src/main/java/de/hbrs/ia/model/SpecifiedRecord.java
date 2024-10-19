package de.hbrs.ia.model;

/**
 * Represents a record of a specified bonus sheet such as
 * Leadership Competence, Openness to Employees, etc.
 * This class is used by the class SocialPerformanceRecord.
 */
public class SpecifiedRecord {
    private int targetValue;
    private int actualValue;
    private int bonus;

    public SpecifiedRecord(int targetValue, int actualValue, int bonus) {
        this.targetValue = targetValue;
        this.actualValue = actualValue;
        this.bonus = bonus;
    }

    public int getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(int targetValue) {
        this.targetValue = targetValue;
    }

    public int getActualValue() {
        return actualValue;
    }

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
