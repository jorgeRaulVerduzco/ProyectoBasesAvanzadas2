/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTO.AutomovilDTO;
import Dominio.Automovil;
import Dominio.Persona;
import Excepciones.PersistenciaException;
import INegocio.IAgregarAutomovilBO;
import Persistencia.AutomovilDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author INEGI
 */
public class AgregarAutomovilBO implements IAgregarAutomovilBO{
AutomovilDAO automovilDAO;
    public AgregarAutomovilBO() {
 automovilDAO = new AutomovilDAO();

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

    // Convertir la PersonaDTO en Persona
    Persona persona = new Persona();
    persona.setNombres(automovilDTO.getPersona().getNombres());
    persona.setApellidoPaterno(automovilDTO.getPersona().getApellidoPaterno());
    persona.setApellidoMaterno(automovilDTO.getPersona().getApellidoMaterno());
    persona.setCurp(automovilDTO.getPersona().getCurp());
    persona.setRfc(automovilDTO.getPersona().getRfc());
    persona.setTelefono(automovilDTO.getPersona().getTelefono());
    persona.setFechaNacimiento(automovilDTO.getPersona().getFechaNacimiento());
    persona.setDiscapacidad(automovilDTO.getPersona().getDiscapacidad());

    automovil.setPersona(persona);

    

    try {
        automovilDAO.AgregarAutomovil(automovil);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

  
    
}
