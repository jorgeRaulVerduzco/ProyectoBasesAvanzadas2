/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Tramite;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface ITramiteDAO {
    public List<Tramite> ReporteTramitePorPersona(Long id);
}
