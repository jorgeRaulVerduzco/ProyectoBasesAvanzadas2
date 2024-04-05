/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dominio.Persona;
import INegocio.IObtenerPersonaDiscapacitadaBO;
import Persistencia.PersonaDAO;

/**
 *
 * @author INEGI
 */
public class obtenerPersonaDiscapacitadaBO implements IObtenerPersonaDiscapacitadaBO {

    PersonaDAO personaDAO;

    public obtenerPersonaDiscapacitadaBO() {
        personaDAO = new PersonaDAO();
    }
    @Override
    public Persona obtenerPersonaDiscpacitadaPorRFC(String rfc) {
 return   personaDAO.obtenerPersonaDiscpacitadaPorRFC(rfc);
}
}
