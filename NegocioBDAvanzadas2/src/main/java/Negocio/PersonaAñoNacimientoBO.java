/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dominio.Persona;
import INegocio.IPersonaAñoNacimientoBO;
import Persistencia.PersonaDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class PersonaAñoNacimientoBO implements IPersonaAñoNacimientoBO {

    PersonaDAO personaDAO;

    public PersonaAñoNacimientoBO() {

        personaDAO = new PersonaDAO();
    }

    @Override
    public List<Persona> buscarPersonasPorAñoNacimiento(int añoNacimiento) {
        return personaDAO.buscarPersonasPorAñoNacimiento(añoNacimiento);

    }

}
