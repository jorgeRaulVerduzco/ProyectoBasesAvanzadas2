/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IBuscarTramitesBO;
import Persistencia.TramiteDAO;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class BuscarTramitesBO implements IBuscarTramitesBO{
    TramiteDAO tramiteDAO;

    public BuscarTramitesBO() {
        tramiteDAO = new TramiteDAO();
    }
    
    
    @Override
    public List<Object[]> buscarTramites(String tipoTramite, Calendar fechaInicio, Calendar fechaFin, String nombre, String apellidoPaterno, String apellidoMaterno) {
return tramiteDAO.buscarTramites(tipoTramite, fechaInicio, fechaFin, nombre, apellidoPaterno, apellidoMaterno);    }
    
}
