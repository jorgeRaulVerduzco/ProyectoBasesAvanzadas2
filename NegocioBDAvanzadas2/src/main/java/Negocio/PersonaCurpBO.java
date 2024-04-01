/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dominio.Persona;
import INegocio.IPersonaCurpBO;
import Persistencia.PersonaDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class PersonaCurpBO implements IPersonaCurpBO {

    PersonaDAO personaDAO;

    public PersonaCurpBO() {

        personaDAO = new PersonaDAO();
    }

    @Override
    public List<Persona> buscarPersonasPorCURP(String curp) {
        return personaDAO.buscarPersonasPorCURP(curp);
    }

}
