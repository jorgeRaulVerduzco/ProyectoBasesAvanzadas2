/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import INegocio.IActualizarDiscapacidadPorRFCBO;
import Persistencia.PersonaDAO;

/**
 *
 * @author INEGI
 */
public class ActualizarDiscapacidadPorRFCBO implements IActualizarDiscapacidadPorRFCBO {

    PersonaDAO personaDAO;

    public ActualizarDiscapacidadPorRFCBO() {
        personaDAO = new PersonaDAO();

    }

    public void actualizarDiscapacidadPorRFC(String rfc) {

        personaDAO.actualizarDiscapacidadPorRFC(rfc);
    }
}
