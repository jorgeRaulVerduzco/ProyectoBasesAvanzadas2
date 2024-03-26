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
public class AutoSeleccionado {

    private static AutomovilDTO automovilSeleccionado;

    public static AutomovilDTO getAutomovilSeleccionado() {
        return automovilSeleccionado;
    }

    public static void setAutomovilSeleccionado(AutomovilDTO automovil) {
        automovilSeleccionado = automovil;
    }
}
