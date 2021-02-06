/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alianza.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gustavo Adolfo Mestra Garay <tavomestra22@gmail.com>
 */
public abstract class DateUtil {

    /**
     * LocalDate a String en formato yyyy-MM-dd
     *
     * @param fecha
     * @return
     */
    public static String localDateToString(LocalDateTime fecha) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fecha.format(formatter);
    }

    /**
     * LocalDate a String en formato HH:mm:ss.
     *
     * @param hora
     * @return
     */
    public static String localTimeToString(LocalTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return hora.format(formatter);
    }

    /**
     * Hora a String en formato 12 horas.
     *
     * @param fecha
     * @return
     */
    public static String localDateToStringFechaHora(LocalTime fecha) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return fecha.format(formatter);
    }

    /**
     * String hora a LocalTime.
     *
     * @param fecha
     * @return
     */
    public static LocalTime stringToLocalTime(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(fecha, formatter);
    }

    /**
     * Hora string 24 h a hora string 12 h.
     *
     * @param x
     * @return
     */
    public static String convertTime(String x) {
        return localDateToStringFechaHora(stringToLocalTime(x));
    }

    /**
     * String fecha a LocalDate.
     *
     * @param fecha
     * @return
     */
    public static LocalDate stringToLocalDate(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fecha, formatter);
    }

    public static String localDateToString(LocalDate fecha) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fecha.format(formatter);
    }

}
