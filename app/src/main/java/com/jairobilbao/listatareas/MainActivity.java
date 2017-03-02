package com.jairobilbao.listatareas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TareaDataSource datasource = new TareaDataSource(this);
    }

   public void startInsertarTarea(View v){
       Intent intent = new Intent (this,InsertarActivity.class);
       startActivity(intent);
   }
    public void startVerTareas(View v){
        Intent intent = new Intent (this,verActivity.class);
        startActivity(intent);
    }
}
