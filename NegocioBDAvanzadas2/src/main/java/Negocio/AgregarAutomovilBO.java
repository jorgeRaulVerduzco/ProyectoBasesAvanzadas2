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

    public AgregarAutomovilBO() {
        automovilDAO = new AutomovilDAO();
        personaDAO = new PersonaDAO();

    }

    @Override
    public void AgregarAutomovil(AutomovilDTO automovilDTO) {
        if (automovilDTO.getNumeroSerie() == null || automovilDTO.getMarca() == null || automovilDTO.getLinea() == null) {
            JOptionPane.showMessageDialog(null, "Error: Los campos obligatorios (número de serie, marca y línea) no pueden ser nulos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Automovil automovil = new Automovil();
        automovil.setNumeroSerie(automovilDTO.getNumeroSerie());
        automovil.setMarca(automovilDTO.getMarca());
        automovil.setLinea(automovilDTO.getLinea());
        automovil.setColor(automovilDTO.getColor());
        automovil.setModelo(automovilDTO.getModelo());

        PersonaDTO personaDTO = automovilDTO.getPersona();
        if (personaDTO != null) {
            Persona personaExistente = personaDAO.obtenerPersonaPorRFC(personaDTO.getRfc());
            if (personaExistente != null) {
                // Usar la persona existente
                automovil.setPersona(personaExistente);
            } else {
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
            JOptionPane.showMessageDialog(null, "Error: No se proporcionó la información de la persona asociada al automóvil.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            automovilDAO.AgregarAutomovil(automovil);
            JOptionPane.showMessageDialog(null, "El automóvil se ha registrado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al registrar el automóvil.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

 
    
    @Override
   public List<Automovil> buscarAutomovilesPorRFC(String rfc) {
        try {
            return automovilDAO.buscarAutomovilesPorRFC(rfc);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar automóviles por RFC de persona", e);
        }
    }
}
