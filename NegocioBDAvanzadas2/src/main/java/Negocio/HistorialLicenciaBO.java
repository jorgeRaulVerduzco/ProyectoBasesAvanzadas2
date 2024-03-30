/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IHistorialLicenciaBO;
import Persistencia.LicenciaDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class HistorialLicenciaBO implements IHistorialLicenciaBO {

    LicenciaDAO licenciaDAO;

    public HistorialLicenciaBO() {
        licenciaDAO = new LicenciaDAO();
    }

    @Override
    public List<Object[]> obtenerHistorialLicenciasPorPersona(Long idPersona) {
        List<Object[]> historialLicencias = null;
        try {
            historialLicencias = licenciaDAO.obtenerHistorialLicenciasPorPersona(idPersona);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return historialLicencias;
    }
}
