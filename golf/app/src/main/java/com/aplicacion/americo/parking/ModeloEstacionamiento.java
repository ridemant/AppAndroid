package com.aplicacion.americo.parking;


public class ModeloEstacionamiento {
    private Integer ID;
    private String nombre;
    private String precio;
    private Integer cantidad;
    private Boolean check;



    public ModeloEstacionamiento(Integer ID, String nombre, String precio, Integer cantidad, Boolean check) {

        this.ID = ID;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.check = check;
    }


    public Integer getID(){
        return this.ID;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getPrecio(){return this.precio; }

    public Integer getCantidad(){return this.cantidad; }
    public Boolean getChecked(){return this.check; }

    public void setCantidad(Integer cantidad){ this.cantidad = cantidad; }
    public void setChecked(Boolean check){ this.check = check; }
}
