package com.jairobilbao.listatareas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Jairo on 09/02/2016.
 */
public class TareaDataSource{

    private Context mContext;
    private TareaSQLiteHelper mTareaSqlLiteHelper;

    public TareaDataSource(Context context) {

        mContext = context;
        mTareaSqlLiteHelper = new TareaSQLiteHelper(context);
    }
    //para escribir y añadir lineas a la tabla
    public SQLiteDatabase openWritable(){

        return mTareaSqlLiteHelper.getWritableDatabase();
    }
    //para leer la tabla
    public SQLiteDatabase openReadable(){

        return mTareaSqlLiteHelper.getReadableDatabase();
    }
    private void close (SQLiteDatabase database){
        database.close();
    }

    public void delete (String nombreTarea){
        SQLiteDatabase database = openWritable();
        database.beginTransaction();

        //siempre el primer campo sera %s
        //%s=%s quiere decir que la consulta la hace a traves de un "nombre"
        //para identificar un numero es %d

        //recuerda poner las comillas y tal, estas escribiendo en SQL
        database.delete(TareaSQLiteHelper.TAREAS_TABLE, String.format("%s=%s", TareaSQLiteHelper.COLUMN_NOMBRE, "'"+nombreTarea+"'"),
                null);

        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }

    //metodo para leer las tareas
    //aparecera solo el nombre y va ordenado por importancia
    public ArrayList<String> readTareas(){
        SQLiteDatabase database = openReadable();
        //nombre de la tabla,columnas a devolver,
        Cursor cursor = database.query(TareaSQLiteHelper.TAREAS_TABLE,
                new String[]{TareaSQLiteHelper.COLUMN_NOMBRE},
                null,
                null,
                null,
                null,
                //ordenar por importancia***
                TareaSQLiteHelper.COLUMN_IMPORTANCIA + " ASC");
        ArrayList<String> tareas = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do{
                String tarea = getStringFromColumnName(cursor, TareaSQLiteHelper.COLUMN_NOMBRE);
                Log.d("Tareas: ", tarea);
                tareas.add(tarea);
            } while(cursor.moveToNext());
        }//***

        //este for es para verificar que la consulta se ha hecho bien, es prescindible
        for (String tarea: tareas){

            Log.d("TareasMetodo: ",tarea);
        }
        cursor.close();
        close(database);
        return tareas;
    }

    private int getIntFromColumnName (Cursor cursor, String columnName){
        int columnIndex=cursor.getColumnIndex(columnName);

        return cursor.getInt(columnIndex);
    }
    private String getStringFromColumnName (Cursor cursor, String columnName){
        int columnIndex=cursor.getColumnIndex(columnName);

        return cursor.getString(columnIndex);
    }
    public void create (Tarea tarea){
        SQLiteDatabase database = openWritable();
        database.beginTransaction();
        ContentValues tareaValues = new ContentValues();
        tareaValues.put(TareaSQLiteHelper.COLUMN_NOMBRE, tarea.getNombre());
        tareaValues.put(TareaSQLiteHelper.COLUMN_IMPORTANCIA, tarea.getImportancia());
        database.insert(TareaSQLiteHelper.TAREAS_TABLE, null, tareaValues);


        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);
    }
}
