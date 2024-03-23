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

/**
 *
 * @author INEGI
 */
public class AgregarPersonaBO implements IAgregarPersonaBO {
  private final PersonaDAO personaDAO;

    public AgregarPersonaBO() {
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public void agregarPersona(PersonaDTO personaDTO) {
        Persona persona = new Persona();

        persona.setNombres(personaDTO.getNombres());
        persona.setApellidoPaterno(personaDTO.getApellidoPaterno());
        persona.setApellidoMaterno(personaDTO.getApellidoMaterno());
        persona.setCurp(personaDTO.getCurp());
        persona.setRfc(personaDTO.getRfc());
        persona.setTelefono(personaDTO.getTelefono());
        persona.setFechaNacimiento(personaDTO.getFechaNacimiento());

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

        return new PersonaDTO(nombre, apellidoPaterno, apellidoMaterno, curp, rfc, telefono, fechaNacimiento);
    }
}
