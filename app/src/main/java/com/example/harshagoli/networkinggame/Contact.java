package com.example.harshagoli.networkinggame;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contact  extends RealmObject {

    @PrimaryKey
    private String ID;
    private String Name;
    private String PhoneNumber;
    private boolean isIgnored;

    @Override
    public String toString() {
        return "Info : (ID) : " + ID + " (Name) : " + Name + " (PhoneNumber) : " + PhoneNumber + " (IsIgnored) : " + isIgnored;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setIgnored(boolean ignored) {
        isIgnored = ignored;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public boolean isIgnored() {
        return isIgnored;
    }
}
