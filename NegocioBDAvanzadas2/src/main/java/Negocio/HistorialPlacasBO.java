/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IHistorialPlacasBO;
import Persistencia.PlacasDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class HistorialPlacasBO implements IHistorialPlacasBO {

    PlacasDAO placasDAO;

    public HistorialPlacasBO() {
       this.placasDAO = new PlacasDAO();
    }

    @Override
    public List<Object[]> obtenerHistorialPlacasPorPersona(Long idPersona) {

        return placasDAO.obtenerHistorialPlacasPorPersona(idPersona);
    }

}
