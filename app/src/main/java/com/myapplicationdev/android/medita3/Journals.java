package com.myapplicationdev.android.medita3;

public class Journals {
    // fields
    private String date;
    private String journal;
    private int id;
    // constructors
    public Journals() {}
    public Journals(String date, String journal) {
        this.date = date;
        this.journal = journal;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // properties
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return this.date;
    }
    public void setJournal(String journal) {
        this.journal = journal;
    }
    public String getJournal() {
        return this.journal;
    }
}
