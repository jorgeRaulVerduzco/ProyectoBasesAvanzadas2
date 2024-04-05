/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTO.AutomovilDTO;
import DTO.PersonaDTO;
import Dominio.Automovil;
import Dominio.Persona;
import Excepciones.PersistenciaException;
import INegocio.IAgregarAutomovilBO;
import Persistencia.AutomovilDAO;
import Persistencia.PersonaDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author INEGI
 */
public class AgregarAutomovilBO implements IAgregarAutomovilBO {

    AutomovilDAO automovilDAO;
    PersonaDAO personaDAO;

    /**
     * Constructor de la clase AgregarAutomovilBO. Inicializa las instancias de
     * AutomovilDAO y PersonaDAO para interactuar con la capa de persistencia.
     */
    public AgregarAutomovilBO() {
        automovilDAO = new AutomovilDAO();
        personaDAO = new PersonaDAO();

    }

    /**
     * Agrega un nuevo automóvil al sistema utilizando la información
     * proporcionada en un objeto AutomovilDTO. Verifica que los campos
     * obligatorios (número de serie, marca y línea) no sean nulos. Si se
     * proporciona la información de una persona asociada al automóvil, se
     * verifica si esa persona ya existe en la base de datos. Si la persona
     * existe, se asocia al automóvil. Si no existe, se crea una nueva persona y
     * se asocia al automóvil.
     *
     * @param automovilDTO Objeto AutomovilDTO que contiene la información del
     * automóvil a agregar.
     */
    @Override
    public void AgregarAutomovil(AutomovilDTO automovilDTO) {
        // Verificar si los campos obligatorios no son nulos
        if (automovilDTO.getNumeroSerie() == null || automovilDTO.getMarca() == null || automovilDTO.getLinea() == null) {
            JOptionPane.showMessageDialog(null, "Error: Los campos obligatorios (número de serie, marca y línea) no pueden ser nulos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Verificar si el número de serie ya existe en la base de datos
        String numeroSerie = automovilDTO.getNumeroSerie();
        if (automovilDAO.existeNumeroSerie(numeroSerie)) {
            JOptionPane.showMessageDialog(null, "Error: El número de serie ya está registrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un nuevo objeto Automovil y asignarle los valores del DTO
        Automovil automovil = new Automovil();
        automovil.setNumeroSerie(automovilDTO.getNumeroSerie());
        automovil.setMarca(automovilDTO.getMarca());
        automovil.setLinea(automovilDTO.getLinea());
        automovil.setColor(automovilDTO.getColor());
        automovil.setModelo(automovilDTO.getModelo());
// Verificar si se proporcionó la información de la persona asociada al automóvil
        PersonaDTO personaDTO = automovilDTO.getPersona();
        if (personaDTO != null) {
            Persona personaExistente = personaDAO.obtenerPersonaPorRFC(personaDTO.getRfc());
            if (personaExistente != null) {
                // Usar la persona existente
                automovil.setPersona(personaExistente);
            } else {
                // Si la persona no existe, crear una nueva persona y asociarla al automóvil
                Persona nuevaPersona = new Persona();
                nuevaPersona.setNombres(personaDTO.getNombres());
                nuevaPersona.setApellidoPaterno(personaDTO.getApellidoPaterno());
                nuevaPersona.setApellidoMaterno(personaDTO.getApellidoMaterno());
                nuevaPersona.setCurp(personaDTO.getCurp());
                nuevaPersona.setRfc(personaDTO.getRfc());
                nuevaPersona.setTelefono(personaDTO.getTelefono());
                nuevaPersona.setFechaNacimiento(personaDTO.getFechaNacimiento());
                nuevaPersona.setDiscapacidad(personaDTO.getDiscapacidad());

                automovil.setPersona(nuevaPersona);
            }
        } else {
            // Si no se proporcionó la información de la persona, mostrar un mensaje de error y salir del método
            JOptionPane.showMessageDialog(null, "Error: No se proporcionó la información de la persona asociada al automóvil.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Intentar agregar el automóvil a la base de datos
        try {
            automovilDAO.AgregarAutomovil(automovil);
            JOptionPane.showMessageDialog(null, "El automóvil se ha registrado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            // En caso de error, imprimir la traza de la excepción y mostrar un mensaje de error
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el automóvil.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Busca todos los automóviles asociados a una persona en la base de datos
     * utilizando su RFC.
     *
     * @param rfc RFC de la persona asociada a los automóviles a buscar.
     * @return Lista de automóviles asociados a la persona con el RFC
     * especificado.
     * @throws RuntimeException Si ocurre un error durante la búsqueda de los
     * automóviles.
     */
    @Override
    public List<Automovil> buscarAutomovilesPorRFC(String rfc) {
        try {
            return automovilDAO.buscarAutomovilesPorRFC(rfc);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar automóviles por RFC de persona", e);
        }
    }
}
