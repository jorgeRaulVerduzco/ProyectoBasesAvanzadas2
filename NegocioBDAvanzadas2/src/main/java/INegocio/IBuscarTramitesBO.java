/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INegocio;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IBuscarTramitesBO {
     public List<Object[]> buscarTramites(String tipoTramite, Calendar fechaInicio, Calendar fechaFin, String nombre, String apellidoPaterno, String apellidoMaterno);
}
