package com.example.szarathkumar.fincal;

public class databaseact {
    public static final String TABLE_NAME = "User";

    public static final String User_ID = "id";
    public static final String User_name = "";
    public static final String getUser_name = "note";
    public static final String User_name = "";
    public static final String u = "";
    public static final String User_name = "";
    public static final String User_name = "";
    public static final String getUser_name = "note";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String note;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + User_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + User_name + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public databaseact() {
    }

    public databaseact(int id, String note, String timestamp) {
        this.id = id;
        this.note = note;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
