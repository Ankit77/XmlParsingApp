package com.example.ankit.xmlparsingapp.contentprovider;

/**
 * Created by Ankit on 5/29/2016.
 */
public class ContactModel {
    private String contactId;
    private String  name;
    private String number;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
