/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatosAleatorios;

import DTO.PersonaDTO;

/**
 *
 * @author INEGI
 */

// Esta es una clase que parece ser una clase utilitaria o de contenedor para almacenar una persona seleccionada.

public class PersonaSeleccionada {
    private static PersonaDTO personaSeleccionada;

    public static PersonaDTO getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public static void setPersonaSeleccionada(PersonaDTO persona) {
        personaSeleccionada = persona;
    }
}
