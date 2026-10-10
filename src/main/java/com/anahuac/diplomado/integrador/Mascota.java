package com.anahuac.diplomado.integrador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Mascota implements Vacunable {

    private String nombre;
    private int edad;
    private String dueno;
    private CartillaVacunacion cartilla = new CartillaVacunacion();

    public Mascota(String nombre, int edad, String dueno) {
        this.nombre = nombre;
        this.edad = edad;
        this.dueno = dueno;
    }

    public abstract String hacerSonido();

    public abstract String getTipo();

    public abstract String getDetalle();

    public abstract List<String> vacunasRecomendadas();

    @Override
    public void vacunar(String vacuna, LocalDate fecha) {
        cartilla.registrar(vacuna, fecha);
    }

    @Override
    public List<String> vacunasPendientes() {

        List<String> pendientes = new ArrayList<>();

        for (String vacuna : vacunasRecomendadas()) {

            if (!cartilla.tieneVacuna(vacuna)) {
                pendientes.add(vacuna);
            }
        }

        return pendientes;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getDueno() {
        return dueno;
    }

    public CartillaVacunacion getCartilla() {
        return cartilla;
    }

    @Override
    public String toString() {
        return getTipo() + " " + nombre
                + " (" + edad + " años, dueño: "
                + dueno + ", " + getDetalle() + ")";
    }
}