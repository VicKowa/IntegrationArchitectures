package de.hbrs.ia.model;

import org.bson.Document;

import java.util.Objects;

public class SalesMan {
    private String firstname;
    private String lastname;
    private Integer sid;

    public SalesMan(String firstname, String lastname, Integer sid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.sid = sid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getId() {
        return sid;
    }

    public void setId(Integer sid) {
        this.sid = sid;
    }

    public Document toDocument() {
        org.bson.Document document = new Document();
        document.append("firstname", this.firstname);
        document.append("lastname", this.lastname);
        document.append("sid", this.sid);
        return document;
    }

    /**
     * Check if two SalesMan objects are equal
     *
     * @param o Another SalesMan object
     * @return true if two SalesMan objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesMan salesMan = (SalesMan) o;
        return Objects.equals(firstname, salesMan.firstname) && Objects.equals(lastname, salesMan.lastname) && Objects.equals(sid, salesMan.sid);
    }
}
