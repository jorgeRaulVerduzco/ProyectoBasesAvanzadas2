/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Dominio.Persona;
import INegocio.IBuscarPersonasBO;
import Persistencia.PersonaDAO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class buscarPersonasBO implements IBuscarPersonasBO{

    PersonaDAO personaDAO;

    public buscarPersonasBO() {

        personaDAO = new PersonaDAO();
    }

    @Override
    public List<Persona> buscarPersonas(String nombre, String apellidoPaterno, String apellidoMaterno, String CURP, Integer añoNacimiento) {
        return personaDAO.buscarPersonas(nombre, apellidoPaterno, apellidoMaterno, CURP, añoNacimiento);

    }
}
