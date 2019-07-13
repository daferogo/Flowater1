package com.example.flowater1;

public class Prediccion {
    private float consumo;
    private String fecha;


    public Prediccion() {
        //Es obligatorio incluir constructor por defecto
    }

    public Prediccion(String fecha, float consumo)
    {
        this.fecha = fecha;
        this.consumo = consumo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getConsumo() {
        return consumo;
    }

    public void setConsumo(float consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return "Prediccion{" +
                "fecha='" + fecha + '\'' +
                ", consumo='" + consumo+'}';
    }
}

