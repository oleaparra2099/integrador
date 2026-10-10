package com.anahuac.diplomado.integrador;

import java.time.LocalDate;

public class Veterinario {

    private String nombre;

    public Veterinario(String nombre) {
        this.nombre = nombre;
    }

    public void aplicarVacuna(Mascota mascota, String vacuna) {

        if (mascota == null) {
            System.out.println("⚠️ Mascota no válida");
            return;
        }

        if (!mascota.vacunasRecomendadas().contains(vacuna)) {
            System.out.println(
                    "⚠️  " + vacuna
                    + " no es una vacuna recomendada para "
                    + mascota.getNombre()
            );
            return;
        }

        mascota.vacunar(vacuna, LocalDate.now());

        System.out.println(
                "🩺 Dr(a). " + nombre
                + " aplicó " + vacuna
                + " a " + mascota.getNombre()
        );
    }

    public String getNombre() {
        return nombre;
    }
}