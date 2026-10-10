package com.anahuac.diplomado.integrador;

import java.time.LocalDate;
import java.util.List;

public interface Vacunable {

    void vacunar(String vacuna, LocalDate fecha);

    List<String> vacunasPendientes();
}