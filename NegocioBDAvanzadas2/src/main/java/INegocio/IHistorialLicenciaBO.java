/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INegocio;

import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IHistorialLicenciaBO {
 public List<Object[]> obtenerHistorialLicenciasPorPersona(Long idPersona);
         }
