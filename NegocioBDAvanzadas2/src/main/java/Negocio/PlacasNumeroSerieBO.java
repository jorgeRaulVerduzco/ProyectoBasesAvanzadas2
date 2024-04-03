/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IPlacasNumeroSerieBO;
import Persistencia.PlacasDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class PlacasNumeroSerieBO implements IPlacasNumeroSerieBO {

    PlacasDAO placasDAO;

    public PlacasNumeroSerieBO() {
        placasDAO = new PlacasDAO();
    }

    @Override
    public List<Object[]> obtenerHistorialPlacasPorAutomovil(String numeroSerie) {
        return placasDAO.obtenerHistorialPlacasPorAutomovil(numeroSerie);
    }

}
