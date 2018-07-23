package com.example.szarathkumar.fincal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class sqldatabase  extends SQLiteOpenHelper{
public static int databae_version =1;
public static final String databse_name = "fincal_db";


    public sqldatabase(Context context) {
        super(context, databse_name, null, databae_version);
    }

    public void onCreate(SQLiteDatabase db)
    {  db.execSQL(fincal.CREATE_TABLE);


    }

}
