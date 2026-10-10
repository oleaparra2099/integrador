package com.anahuac.diplomado.integrador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartillaVacunacion {

    private Map<String, LocalDate> vacunas = new HashMap<>();

    public void registrar(String vacuna, LocalDate fecha) {
        vacunas.put(vacuna, fecha);
    }

    public boolean tieneVacuna(String vacuna) {
        return vacunas.containsKey(vacuna);
    }

    public void mostrar() {

        if (vacunas.isEmpty()) {
            System.out.println("    (cartilla vacía)");
            return;
        }

        for (Map.Entry<String, LocalDate> entrada : vacunas.entrySet()) {
            System.out.println(
                    "    💉 " + entrada.getKey()
                    + " -> " + entrada.getValue()
            );
        }
    }

    public void guardar(Path archivo) throws IOException {

        List<String> lineas = new ArrayList<>();

        for (Map.Entry<String, LocalDate> entrada : vacunas.entrySet()) {
            lineas.add(
                    entrada.getKey() + "," + entrada.getValue()
            );
        }

        Files.write(archivo, lineas);
    }

    public void cargar(Path archivo) throws IOException {

        if (!Files.exists(archivo)) {
            return;
        }

        for (String linea : Files.readAllLines(archivo)) {

            String[] partes = linea.split(",");

            String vacuna = partes[0];
            LocalDate fecha = LocalDate.parse(partes[1]);

            vacunas.put(vacuna, fecha);
        }
    }
}