package com.jairobilbao.listatareas;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class verActivity extends ListActivity {
    TareaDataSource dataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);
        //se hace refresh para tener actualizado siempre el listView
        refresh();
        this.getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String nombre= (String) getListAdapter().getItem(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(verActivity.this)
                        .setMessage("Desea borrar la tarea?")
                        .setTitle("Por favor, confirme.")
                        .setNegativeButton(getString(android.R.string.no), null)
                        .setPositiveButton(getString(android.R.string.yes),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        TareaDataSource datasource = new TareaDataSource(verActivity.this);
                                        datasource.delete(nombre);
                                        refresh();
                                    }
                                });

                builder.create().show();
                return true;
            }
        });

    }

    private void refresh() {
        TareaDataSource dataSource = new TareaDataSource(this);
        ArrayList<String> tareas = new ArrayList<String>();
        tareas=dataSource.readTareas();
        for (String tarea: tareas){

            Log.d("TarasVer: ",tarea);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tareas);
        setListAdapter(adapter);
    }


}
