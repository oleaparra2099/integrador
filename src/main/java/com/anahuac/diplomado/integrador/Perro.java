package com.anahuac.diplomado.integrador;

import java.util.List;

public class Perro extends Mascota {

    private String raza;

    public Perro(String nombre, int edad, String dueno, String raza) {
        super(nombre, edad, dueno);
        this.raza = raza;
    }

    @Override
    public String hacerSonido() {
        return "¡Guau!";
    }

    @Override
    public String getTipo() {
        return "PERRO";
    }

    @Override
    public String getDetalle() {
        return raza;
    }

    @Override
    public List<String> vacunasRecomendadas() {
        return List.of(
                "Rabia",
                "Parvovirus",
                "Moquillo"
        );
    }
}