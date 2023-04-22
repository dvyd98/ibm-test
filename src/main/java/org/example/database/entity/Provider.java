package org.example.database.entity;

import java.util.Date;

public class Provider {

    private Long id;

    private String name;

    private Date registrationDate;

    private Long clientId;

    public Provider(Long id, String name, Date registrationDate, Long clientId) {
        this.id = id;
        this.name = name;
        this.registrationDate = registrationDate;
        this.clientId = clientId;
    }

    public String toString() {
        return String.format("%s, %s, %s, %s", id, name, registrationDate.toString(), clientId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
