/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IverificarLicenciaNoCaducadaPorRFCBO;
import Persistencia.LicenciaDAO;

/**
 *
 * @author INEGI
 */
public class verificarLicenciaNoCaducadaPorRFCBO implements IverificarLicenciaNoCaducadaPorRFCBO {

    LicenciaDAO licenciaDAO;

    public verificarLicenciaNoCaducadaPorRFCBO() {
        licenciaDAO = new LicenciaDAO();
    }

    @Override
    public boolean verificarLicenciaNoCaducadaPorRFC(String rfc) {
        return licenciaDAO.verificarLicenciaNoCaducadaPorRFC(rfc);
    }
}
