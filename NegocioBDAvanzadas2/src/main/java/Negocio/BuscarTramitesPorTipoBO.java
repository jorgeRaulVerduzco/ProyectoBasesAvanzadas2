/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IBuscarTramitesPorTipoBO;
import Persistencia.TramiteDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class BuscarTramitesPorTipoBO implements IBuscarTramitesPorTipoBO {

    TramiteDAO tramiteDao;

    public BuscarTramitesPorTipoBO() {

        tramiteDao = new TramiteDAO();
    }

    @Override
    public List<Object[]> buscarTramitesPorTipo(String tipoTramite) {
        return tramiteDao.buscarTramitesPorTipo(tipoTramite);

    }

}
