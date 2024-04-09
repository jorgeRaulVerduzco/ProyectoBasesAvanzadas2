/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosAleatorios;

import DTO.AutomovilDTO;

/**
 *
 * @author INEGI
 */

// Esta es una clase Java llamada AutoSeleccionado, que parece ser una clase utilitaria o de contenedor para almacenar un automóvil seleccionado.

public class AutoSeleccionado {

    private static AutomovilDTO automovilSeleccionado;

    public static AutomovilDTO getAutomovilSeleccionado() {
        return automovilSeleccionado;
    }

    public static void setAutomovilSeleccionado(AutomovilDTO automovil) {
        automovilSeleccionado = automovil;
    }
}
