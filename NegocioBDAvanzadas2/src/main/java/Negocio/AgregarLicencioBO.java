/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTO.LicenciaDTO;
import DTO.PersonaDTO;
import DatosAleatorios.DatosAleatorios;
import Dominio.Licencia;
import Dominio.Persona;
import Excepciones.PersistenciaException;
import INegocio.IAgregarLicenciaBO;
import Persistencia.LicenciaDAO;
import Persistencia.PersonaDAO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author INEGI
 */
public class AgregarLicencioBO implements IAgregarLicenciaBO {

    LicenciaDAO licenciaDAO;
    private final PersonaDAO personaDAO;

    public AgregarLicencioBO() {
        this.licenciaDAO = new LicenciaDAO();
        this.personaDAO = new PersonaDAO();
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

    @Override
    public void AgregarLicencia(LicenciaDTO licenciaDTO, String rfcPersona) {
       // Obtener la fecha de nacimiento de la persona
    Persona persona = personaDAO.obtenerPersonaPorRFC(rfcPersona);

    if (persona != null) {
        // Convertir la fecha de nacimiento de Calendar a LocalDate
        Calendar fechaNacimientoCalendar = persona.getFechaNacimiento();
        LocalDate fechaNacimiento = LocalDate.of(fechaNacimientoCalendar.get(Calendar.YEAR),
                                                  fechaNacimientoCalendar.get(Calendar.MONTH) + 1,
                                                  fechaNacimientoCalendar.get(Calendar.DAY_OF_MONTH));

        // Calcular la edad de la persona
        LocalDate fechaActual = LocalDate.now();
        long años = ChronoUnit.YEARS.between(fechaNacimiento, fechaActual);

        // Verificar si la persona es mayor o igual a 18 años
        if (años >= 18) {
            Licencia licencia = convertirLicenciaDTO(licenciaDTO);

            // Asignar la persona a la licencia
            licencia.setPersona(persona);

            // Llamar al método agregarLicencia de LicenciaDAO
            licenciaDAO.agregarLicencia(licencia); // Manejar la excepción, si es necesario
        } else {
            JOptionPane.showMessageDialog(null, "La persona debe ser mayor o igual a 18 años para obtener una licencia.",
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "No se encontró ninguna persona con el RFC proporcionado.",
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }
    }

    private Licencia convertirLicenciaDTO(LicenciaDTO licenciaDTO) {
        Licencia licencia = new Licencia();

        licencia.setAñosVigencia(licenciaDTO.getAñosVigencia());
        licencia.setCosto(licenciaDTO.getCosto());
        licencia.setFechaTramite(licenciaDTO.getFechaTramite());
        licencia.setFechaVigencia(licenciaDTO.getFechaVigencia());

        return licencia;
    }

    @Override
    public List<Persona> ListaPersonas() {
        return personaDAO.ListaPersonas();
    }

    @Override
    public Persona obtenerPersonaPorRFC(String rfc) {
        return personaDAO.obtenerPersonaPorRFC(rfc);
    }

    public float costo(int aniosVigencia, int discapacidad) {
        float costo = 0;
        switch (aniosVigencia) {
            case 1:
                costo = (discapacidad == 1) ? 200.0f : 600.0f;
                break;
            case 2:
                costo = (discapacidad == 1) ? 500.0f : 900.0f; // Se corrigió el valor 500f a 500.0f
                break;
            case 3:
                costo = (discapacidad == 1) ? 700.0f : 1100.0f;
                break;
        }
        return costo;
    }
}
