package com.anahuac.diplomado.integrador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Veterinaria {

    private String nombre;
    private List<Mascota> pacientes = new ArrayList<>();

    public Veterinaria(String nombre) {
        this.nombre = nombre;
    }

    public void registrarPaciente(Mascota m) {
        pacientes.add(m);
    }

    public Mascota buscar(String nombreMascota) {

        for (Mascota m : pacientes) {

            if (m.getNombre().equalsIgnoreCase(nombreMascota)) {
                return m;
            }
        }

        return null;
    }

    public List<Mascota> getPacientes() {
        return pacientes;
    }

    public void mostrarPacientes() {

        System.out.println("📋 Pacientes de " + nombre + ":");

        for (Mascota m : pacientes) {
            System.out.println("  • " + m);
        }
    }

    public void guardarTodo(Path carpeta) throws IOException {

        Files.createDirectories(carpeta);

        List<String> lineas = new ArrayList<>();

        for (Mascota m : pacientes) {

            String linea =
                    m.getTipo() + ";"
                    + m.getNombre() + ";"
                    + m.getEdad() + ";"
                    + m.getDueno() + ";"
                    + m.getDetalle();

            lineas.add(linea);

            Path archivoCartilla =
                    carpeta.resolve(
                            "cartilla_" + m.getNombre() + ".txt"
                    );

            m.getCartilla().guardar(archivoCartilla);
        }

        Files.write(
                carpeta.resolve("pacientes.csv"),
                lineas
        );
    }

    public void cargarTodo(Path carpeta) throws IOException {

        Path archivo =
                carpeta.resolve("pacientes.csv");

        if (!Files.exists(archivo)) {
            return;
        }

        pacientes.clear();

        for (String linea : Files.readAllLines(archivo)) {

            String[] p = linea.split(";");

            String tipo = p[0];
            String nombreMascota = p[1];
            int edad = Integer.parseInt(p[2]);
            String dueno = p[3];
            String detalle = p[4];

            Mascota m;

            if (tipo.equals("PERRO")) {

                m = new Perro(
                        nombreMascota,
                        edad,
                        dueno,
                        detalle
                );

            } else {

                boolean interior =
                        detalle.equals("interior");

                m = new Gato(
                        nombreMascota,
                        edad,
                        dueno,
                        interior
                );
            }

            Path archivoCartilla =
                    carpeta.resolve(
                            "cartilla_" + nombreMascota + ".txt"
                    );

            m.getCartilla().cargar(archivoCartilla);

            pacientes.add(m);
        }
    }
}