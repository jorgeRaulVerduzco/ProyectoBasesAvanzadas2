/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosAleatorios;

import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author INEGI
 */
public class DatosAleatorios {

    private static final String[] NOMBRES = {"Juan", "Maria", "Carlos", "Laura", "Pedro", "Ana", "Jose", "Luis", "Sofia", "Diego"};
    private static final String[] APELLIDOS = {"Garcia", "Hernandez", "Lopez", "Martinez", "Gonzalez", "Perez", "Rodriguez", "Sanchez", "Ramirez", "Torres"};
    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    public static String generarNombreAleatorio() {
        return NOMBRES[RANDOM.nextInt(NOMBRES.length)];
    }

    public static String generarApellidoAleatorio() {
        return APELLIDOS[RANDOM.nextInt(APELLIDOS.length)];
    }

    public static String generarCurpAleatorio() {
        StringBuilder curp = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            curp.append(LETRAS.charAt(RANDOM.nextInt(LETRAS.length())));
        }
        return curp.toString();
    }

    public static String generarRfcAleatorio() {
        StringBuilder rfc = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            rfc.append(LETRAS.charAt(RANDOM.nextInt(LETRAS.length())));
        }
        return rfc.toString();
    }

    public static String generarTelefonoAleatorio() {
        StringBuilder telefono = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            telefono.append(RANDOM.nextInt(10));
        }
        return telefono.toString();
    }

    public static Calendar generarFechaNacimientoAleatoria() {
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.set(Calendar.YEAR, 1950 + RANDOM.nextInt(70));
        fechaNacimiento.set(Calendar.MONTH, RANDOM.nextInt(12));
        fechaNacimiento.set(Calendar.DAY_OF_MONTH, RANDOM.nextInt(28) + 1);
        return fechaNacimiento;
    }

    public static String generarDiscapacidadAleatoria() {
        return (RANDOM.nextInt(2) == 0) ? "Sí" : "No";
    }

    public static String generarPlacaAleatoria() {
        StringBuilder placa = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            placa.append(LETRAS.charAt(RANDOM.nextInt(LETRAS.length())));
        }

        placa.append("-");

        for (int i = 0; i < 3; i++) {
            placa.append(RANDOM.nextInt(10));
        }

        return placa.toString();
    }
}
