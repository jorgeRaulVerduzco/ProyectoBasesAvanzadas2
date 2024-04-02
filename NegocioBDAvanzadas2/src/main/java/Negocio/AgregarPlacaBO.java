/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTO.AutomovilDTO;
import DTO.PlacaDTO;
import DatosAleatorios.DatosAleatorios;
import Dominio.Automovil;
import Dominio.Placa;
import INegocio.IAgregarPlacaBO;
import Persistencia.AutomovilDAO;
import Persistencia.PersonaDAO;
import Persistencia.PlacasDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author INEGI
 */
public class AgregarPlacaBO implements IAgregarPlacaBO {

    PlacasDAO placaDAO;
    AutomovilDAO automovilDAO;
    PersonaDAO personaDAO;

    public AgregarPlacaBO() {
        placaDAO = new PlacasDAO();
        automovilDAO = new AutomovilDAO();
        personaDAO = new PersonaDAO();
    }

    @Override
    public void AgregarPlaca(AutomovilDTO automovilDTO, PlacaDTO placaDTO) {
       if (automovilDTO == null || placaDTO == null) {
        JOptionPane.showMessageDialog(null, "Error: El automóvil o la placa no pueden ser nulos.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Automovil automovil = automovilDAO.buscarAutomovilPorNumeroSerie(automovilDTO.getNumeroSerie());

    if (automovil == null) {
        JOptionPane.showMessageDialog(null, "Error: No se encontró el automóvil con el número de serie proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Placa placa = new Placa();
    placa.setDigitosPlaca(DatosAleatorios.generarPlacaAleatoria()); 
    placa.setEstado(placaDTO.getEstado());
    placa.setFechaTramite(placaDTO.getFechaTramite()); 
    placa.setPersona(personaDAO.obtenerPersonaPorRFC(placaDTO.getPersona().getRfc())); 
    placa.setFechaVigencia(placaDTO.getFechaVigencia()); 

    placa.setAutomovil(automovil);

    try {
        placaDAO.agregarPlacas(automovil, placa);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
}
