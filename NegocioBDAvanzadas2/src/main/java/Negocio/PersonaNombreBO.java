/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dominio.Persona;
import INegocio.IPersonaNombreBO;
import Persistencia.PersonaDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class PersonaNombreBO implements IPersonaNombreBO {

    PersonaDAO personaDAO;

    public PersonaNombreBO() {
        this.personaDAO = new PersonaDAO();
    }

    @Override
    public List<Persona> buscarPersonasPorNombre(String nombre) {
        return personaDAO.buscarPersonasPorNombre(nombre);
    }

}
