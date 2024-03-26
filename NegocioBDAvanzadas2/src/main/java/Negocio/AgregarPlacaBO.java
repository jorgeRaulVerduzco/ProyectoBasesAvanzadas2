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
    public AgregarPlacaBO(){
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

    // Buscar el automóvil por número de serie
    Automovil automovil = automovilDAO.buscarAutomovilPorNumeroSerie(automovilDTO.getNumeroSerie());

    // Si no se encuentra el automóvil, mostrar un mensaje de error y salir del método
    if (automovil == null) {
        JOptionPane.showMessageDialog(null, "Error: No se encontró el automóvil con el número de serie proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

Placa placa = new Placa();
placa.setDigitosPlaca(DatosAleatorios.generarPlacaAleatoria()); // Generar placa aleatoria
placa.setEstado(placaDTO.getEstado());
placa.setCosto(placaDTO.getCosto()); // Establecer el costo
placa.setFechaTramite(placaDTO.getFechaTramite()); // Establecer la fecha de trámite
placa.setPersona(personaDAO.obtenerPersonaPorRFC(placaDTO.getPersona().getRfc())); 
placa.setFechaVigencia(placaDTO.getFechaVigencia()); // Establecer la fecha de vigencia

    // Asignar el automóvil a la placa
    placa.setAutomovil(automovil);

    try {
        placaDAO.agregarPlacas(automovil,placa);
    } catch (Exception e) {
        e.printStackTrace();
    }
    }

}
