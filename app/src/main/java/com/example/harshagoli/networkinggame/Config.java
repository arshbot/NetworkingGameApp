package com.example.harshagoli.networkinggame;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Config extends RealmObject {

    @Override
    public String toString() {
        return "Config{" +
                "isImported=" + isImported +
                '}';
    }

    @PrimaryKey
    private String ID;
    private boolean isImported;


    public boolean isImported() {
        return isImported;
    }

    public void setImported(boolean imported) {
        isImported = imported;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}