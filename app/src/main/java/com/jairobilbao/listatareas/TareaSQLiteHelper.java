package com.jairobilbao.listatareas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Jairo on 09/02/2016.
 */
public class TareaSQLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "tarea.db";
    private static final int DB_VERSION = 1;
    public static final String TAREAS_TABLE = "TAREAS";
    public static final String COLUMN_NOMBRE="NOMBRE";
    public static final String COLUMN_IMPORTANCIA="IMPORTANCIA";

    public static String CREATE_TAREA =
            "CREATE TABLE "+ TAREAS_TABLE+" ( "+ BaseColumns._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NOMBRE+" TEXT,"+
                    COLUMN_IMPORTANCIA+" INTEGER);";

    //Meme Table Annotations functionality
    public TareaSQLiteHelper (Context context){
        super(context, DB_NAME, null, DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TAREA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {    }
}
