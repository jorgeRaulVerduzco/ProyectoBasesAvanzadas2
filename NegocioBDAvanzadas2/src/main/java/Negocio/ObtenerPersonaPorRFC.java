/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dominio.Persona;
import INegocio.IObtenerPersonaPorRFC;
import Persistencia.PersonaDAO;

/**
 *
 * @author INEGI
 */
public class ObtenerPersonaPorRFC implements IObtenerPersonaPorRFC {

    PersonaDAO personaDAO;

    public ObtenerPersonaPorRFC() {
        personaDAO= new PersonaDAO();
        
    }

    @Override
    public Persona obtenerPersonaPorRFC(String rfc) {
        return personaDAO.obtenerPersonaPorRFC(rfc);
    }
}
