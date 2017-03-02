package com.jairobilbao.listatareas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertarActivity extends AppCompatActivity {
    EditText tareaLabel;
    EditText importanciaLabel;
    TareaDataSource datasource = new TareaDataSource(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        tareaLabel = (EditText) findViewById(R.id.tareaLabel);
        importanciaLabel = (EditText) findViewById(R.id.importanciaLabel);
    }

    public void insertarTarea(View v){
        if (tareaLabel.getText().toString().equals("")){
            Toast.makeText(this,"Se deben rellenar los dos campos",Toast.LENGTH_LONG).show();
        }else{
            //recuerda que el orden de estos campos ha de ser el mismo que en tarea, si no da error
            Tarea tarea = new Tarea(-1,tareaLabel.getText().toString(),Integer.parseInt(importanciaLabel.getText().toString()));
            datasource.create(tarea);

            tareaLabel.setText("");
            importanciaLabel.setText("");
        }
    }
}
