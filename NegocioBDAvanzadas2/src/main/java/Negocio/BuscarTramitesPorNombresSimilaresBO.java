/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IBuscarTramitesPorNombresSimilares;
import Persistencia.TramiteDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class BuscarTramitesPorNombresSimilaresBO implements IBuscarTramitesPorNombresSimilares{
TramiteDAO tramiteDao;

    public BuscarTramitesPorNombresSimilaresBO() {
        tramiteDao = new TramiteDAO();
    }

    @Override
    public List<Object[]> buscarTramitesPorNombresSimilares(String nombre) {
return tramiteDao.buscarTramitesPorNombresSimilares(nombre);    }
    
}
