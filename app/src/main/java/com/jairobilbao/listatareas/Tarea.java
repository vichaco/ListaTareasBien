package com.jairobilbao.listatareas;

import java.io.Serializable;

/**
 * Created by Jairo on 09/02/2016.
 */
public class Tarea implements Serializable {
    private int id;
    private String nombre;
    private int importancia;

    public Tarea(int id, String nombre, int importancia) {
        this.id = id;
        this.nombre = nombre;
        this.importancia = importancia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImportancia() {
        return importancia;
    }

    public void setImportancia(int importancia) {
        this.importancia = importancia;
    }
}
