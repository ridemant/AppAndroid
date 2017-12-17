package com.aplicacion.americo.parking;

public class ModeloSucursal {
    private String titulo;
    private int CodSucursal;

    public ModeloSucursal(int CodSucursal, String titulo) {
        this.CodSucursal = CodSucursal;
        this.titulo = titulo;
    }

    public int getCodSucursal(){
        return this.CodSucursal;
    }
    public String getTitulo(){
        return this.titulo;
    }

}