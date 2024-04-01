/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IBuscarTramitesPorPeriodoBO;
import Persistencia.TramiteDAO;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class BuscarTramitesPorPeriodoBO implements IBuscarTramitesPorPeriodoBO{
TramiteDAO tramiteDao;

    public BuscarTramitesPorPeriodoBO() {
        tramiteDao= new TramiteDAO();
    }
    
    
    @Override
    public List<Object[]> buscarTramitesPorPeriodo(Calendar fechaInicio, Calendar fechaFin) {
return tramiteDao.buscarTramitesPorPeriodo(fechaInicio, fechaFin);

    }
    
}
