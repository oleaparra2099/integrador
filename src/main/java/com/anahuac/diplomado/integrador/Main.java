package com.anahuac.diplomado.integrador;

import java.io.IOException;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("🐾 INICIANDO SISTEMA VETERINARIO 'PATITAS FELICES' 🐾\n");

        Veterinaria clinica = new Veterinaria("Patitas Felices");
        Veterinario doctora = new Veterinario("Sofía Ramírez");

        clinica.registrarPaciente(new Perro("Firulais", 3, "Ana", "Labrador"));
        clinica.registrarPaciente(new Gato("Michi", 2, "Luis", true));
        clinica.registrarPaciente(new Perro("Rex", 5, "Marta", "Pastor Alemán"));

        clinica.mostrarPacientes();

        System.out.println("\n--- Jornada de vacunación ---");

        doctora.aplicarVacuna(clinica.buscar("Firulais"), "Rabia");
        doctora.aplicarVacuna(clinica.buscar("Firulais"), "Parvovirus");
        doctora.aplicarVacuna(clinica.buscar("Michi"), "Triple felina");

        // Moquillo no es una vacuna recomendada para gatos
        doctora.aplicarVacuna(clinica.buscar("Michi"), "Moquillo");

        System.out.println("\n--- Polimorfismo ---");

        for (Mascota m : clinica.getPacientes()) {
            if (m != null) {
                System.out.println(
                        m.getNombre() + " dice " + m.hacerSonido()
                );
            }
        }

        System.out.println("\n--- Cartillas ---");

        for (Mascota m : clinica.getPacientes()) {

            if (m != null) {

                System.out.println(
                        m.getNombre()
                        + " | pendientes: "
                        + m.vacunasPendientes()
                );

                if (m.getCartilla() != null) {
                    m.getCartilla().mostrar();
                }
            }
        }

        // Guarda todos los datos de la veterinaria
        Path carpeta = Path.of("datos_veterinaria");
        clinica.guardarTodo(carpeta);

        System.out.println(
                "\n💾 Datos guardados en la carpeta: " + carpeta
        );

        // Recarga los datos guardados
        Veterinaria recargada =
                new Veterinaria("Patitas Felices (recargada)");

        recargada.cargarTodo(carpeta);

        System.out.println();

        recargada.mostrarPacientes();

        Mascota firulaisRecargado =
                recargada.buscar("Firulais");

        if (firulaisRecargado != null
                && firulaisRecargado.getCartilla() != null) {

            System.out.println(
                    "Cartilla de Firulais recuperada desde el archivo:"
            );

            firulaisRecargado.getCartilla().mostrar();

        } else {

            System.out.println(
                    "⚠️ Firulais no fue recuperado "
                    + "(revisa guardarTodo/cargarTodo en Veterinaria)."
            );
        }
    }
}