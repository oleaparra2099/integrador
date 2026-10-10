package com.anahuac.diplomado.integrador;

import java.util.List;

public class Gato extends Mascota {

    private boolean interior;

    public Gato(String nombre, int edad, String dueno, boolean interior) {
        super(nombre, edad, dueno);
        this.interior = interior;
    }

    @Override
    public String hacerSonido() {
        return "¡Miau!";
    }

    @Override
    public String getTipo() {
        return "GATO";
    }

    @Override
    public String getDetalle() {
        return interior ? "interior" : "exterior";
    }

    @Override
    public List<String> vacunasRecomendadas() {
        return List.of(
                "Rabia",
                "Triple felina",
                "Leucemia felina"
        );
    }
}