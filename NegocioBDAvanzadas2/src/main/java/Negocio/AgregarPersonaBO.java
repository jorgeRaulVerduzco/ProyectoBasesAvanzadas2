/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTO.PersonaDTO;
import DatosAleatorios.DatosAleatorios;
import Dominio.Persona;
import Excepciones.PersistenciaException;
import INegocio.IAgregarPersonaBO;
import Persistencia.PersonaDAO;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author INEGI
 */
public class AgregarPersonaBO implements IAgregarPersonaBO {
    PersonaDAO personaDAO;

    public AgregarPersonaBO() {
        
        personaDAO = new PersonaDAO();
    }
    
    
        @Override
    public void agregarPersona(PersonaDTO personaDTO) {
        if (personaDTO.getNombres() == null || personaDTO.getApellidoPaterno() == null || personaDTO.getRfc() == null) {
            JOptionPane.showMessageDialog(null, "Error: Los campos obligatorios (nombres, apellido paterno y RFC) no pueden ser nulos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Persona persona = new Persona();
        persona.setNombres(personaDTO.getNombres());
        persona.setApellidoPaterno(personaDTO.getApellidoPaterno());
        persona.setApellidoMaterno(personaDTO.getApellidoMaterno());
        persona.setCurp(personaDTO.getCurp());
        persona.setRfc(personaDTO.getRfc());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());
        persona.setDiscapacidad(personaDTO.getDiscapacidad());
        try {
            personaDAO.agregarPersona(persona);
        } catch (PersistenciaException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void incersionMasiva() {
        for (int i = 0; i < 20; i++) {
            PersonaDTO personaDTO = generarPersonaAleatoria();

            agregarPersona(personaDTO);
        }
    }

    private PersonaDTO generarPersonaAleatoria() {
        String nombre = DatosAleatorios.generarNombreAleatorio();
        String apellidoPaterno = DatosAleatorios.generarApellidoAleatorio();
        String apellidoMaterno = DatosAleatorios.generarApellidoAleatorio();
        String curp = DatosAleatorios.generarCurpAleatorio();
        String rfc = DatosAleatorios.generarRfcAleatorio();
        String telefono = DatosAleatorios.generarTelefonoAleatorio();
        Calendar fechaNacimiento = DatosAleatorios.generarFechaNacimientoAleatoria();
        String discapacidad = DatosAleatorios.generarDiscapacidadAleatoria();
        return new PersonaDTO(nombre, apellidoPaterno, apellidoMaterno, curp, rfc, telefono, fechaNacimiento, discapacidad);
    }

}
