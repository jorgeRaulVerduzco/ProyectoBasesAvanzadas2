/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Licencia;
import Dominio.Persona;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface ILicenciaDAO {

    public void agregarLicencia(Licencia licencia);
public List<Object[]> obtenerHistorialLicenciasPorPersona(Long idPersona);
}
