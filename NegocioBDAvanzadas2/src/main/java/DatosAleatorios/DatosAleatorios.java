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
// Arreglos de nombres y apellidos predefinidos para generar datos aleatorios

    private static final String[] NOMBRES = {
        "Jorge", "Raul", "Servando", "Fedra", "Juan", "Maria", "Carlos", "Laura", "Pedro", "Ana", "Jose", "Luis", "Sofia", "Diego",
        "Fernando", "Paula", "Roberto", "Lucia", "Miguel", "Elena", "Alejandro", "Julia", "Raul", "Isabel",
        "Gabriel", "Daniela", "Manuel", "Carmen", "Antonio", "Rosa", "Francisco", "Natalia", "Javier", "Adriana"
    };
    private static final String[] APELLIDOS = {"Verduzco", "Mora", "Garcia", "Hernandez", "Lopez", "Martinez", "Gonzalez", "Perez", "Rodriguez", "Sanchez", "Ramirez", "Torres",
        "Fernandez", "Gomez", "Diaz", "Ruiz", "Alvarez", "Jimenez", "Moreno", "Munoz", "Romero", "Alonso",
        "Navarro", "Serrano", "Castillo", "Delgado", "Vazquez", "Ramos", "Blanco", "Nunez", "Cruz", "Iglesias", "Ortega", "Molina", "Suarez", "Gimenez", "Martínez", "Santos", "Cabrera", "Vega", "Aguilar", "Medina"};
    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // Instancia de la clase Random para generar números aleatorios
    private static final Random RANDOM = new Random();

    /**
     * Genera un nombre aleatorio seleccionado de forma aleatoria del arreglo de
     * nombres predefinidos.
     *
     * @return Un nombre aleatorio.
     */
    public static String generarNombreAleatorio() {
        return NOMBRES[RANDOM.nextInt(NOMBRES.length)];
    }

    /**
     * Genera un apellido aleatorio seleccionado de forma aleatoria del arreglo
     * de apellidos predefinidos.
     *
     * @return Un apellido aleatorio.
     */
    public static String generarApellidoAleatorio() {
        return APELLIDOS[RANDOM.nextInt(APELLIDOS.length)];
    }

    /**
     * Genera una cadena de 18 caracteres aleatorios que representan un CURP
     * (Clave Única de Registro de Población).
     *
     * @return Un CURP aleatorio.
     */
    public static String generarCurpAleatorio() {
        StringBuilder curp = new StringBuilder();
        for (int i = 0; i < 18; i++) {
            curp.append(LETRAS.charAt(RANDOM.nextInt(LETRAS.length())));
        }
        return curp.toString();
    }

    /**
     * Genera una cadena de 13 caracteres aleatorios que representan un RFC
     * (Registro Federal de Contribuyentes).
     *
     * @return Un RFC aleatorio.
     */
    public static String generarRfcAleatorio() {
        StringBuilder rfc = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            rfc.append(LETRAS.charAt(RANDOM.nextInt(LETRAS.length())));
        }
        return rfc.toString();
    }

    /**
     * Genera una cadena de 10 dígitos aleatorios que representan un número de
     * teléfono.
     *
     * @return Un número de teléfono aleatorio.
     */
    public static String generarTelefonoAleatorio() {
        StringBuilder telefono = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            telefono.append(RANDOM.nextInt(10));
        }
        return telefono.toString();
    }

    /**
     * Genera una fecha de nacimiento aleatoria en un rango de años específico
     * (1950-2019).
     *
     * @return Una fecha de nacimiento aleatoria.
     */
    public static Calendar generarFechaNacimientoAleatoria() {
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.set(Calendar.YEAR, 1950 + RANDOM.nextInt(70));
        fechaNacimiento.set(Calendar.MONTH, RANDOM.nextInt(12));
        fechaNacimiento.set(Calendar.DAY_OF_MONTH, RANDOM.nextInt(28) + 1);
        return fechaNacimiento;
    }

    /**
     * Genera una cadena que indica si una persona tiene o no discapacidad de
     * forma aleatoria.
     *
     * @return "Sí" si la persona tiene discapacidad, "No" si no tiene
     * discapacidad.
     */
    public static String generarDiscapacidadAleatoria() {
        return (RANDOM.nextInt(2) == 0) ? "Sí" : "No";
    }

    /**
     * Genera una cadena de 6 caracteres aleatorios seguidos de un guion y 3
     * dígitos aleatorios, simulando una placa de vehículo.
     *
     * @return Una placa aleatoria.
     */
    public static String generarPlacaAleatoria() {
        StringBuilder placa = new StringBuilder();
// Genera 3 letras aleatorias para la primera parte de la placa
        for (int i = 0; i < 3; i++) {
            placa.append(LETRAS.charAt(RANDOM.nextInt(LETRAS.length())));
        }
// Añade un guion entre la primera y la segunda parte de la placa
        placa.append("-");
        // Genera 3 dígitos aleatorios para la segunda parte de la placa
        for (int i = 0; i < 3; i++) {
            placa.append(RANDOM.nextInt(10));
        }

        return placa.toString();
    }
}
