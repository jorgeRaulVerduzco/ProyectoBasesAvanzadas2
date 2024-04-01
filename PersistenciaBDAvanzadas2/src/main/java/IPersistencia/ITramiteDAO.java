/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Tramite;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface ITramiteDAO {
public List<Object[]> buscarTramitesPorTipo(String tipoTramite);
public List<Object[]> buscarTramitesPorPeriodo(Calendar fechaInicio, Calendar fechaFin);
public List<Object[]> buscarTramitesPorNombresSimilares(String nombre);
}