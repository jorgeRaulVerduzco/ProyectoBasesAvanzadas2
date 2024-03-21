/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Prueba;

import Dominio.Persona;
import Excepciones.PersistenciaException;
import Persistencia.PersonaDAO;
import java.util.Calendar;

/**
 *
 * @author INEGI
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PersonaDAO DAO = new PersonaDAO();
        Persona persona = new Persona();
        persona.setNombres("Juan");
        persona.setApellidoPaterno("Pérez");
        persona.setApellidoMaterno("González");
        persona.setCurp("XXXX12345");
        persona.setRfc("PEJG12345");
        persona.setTelefono("555123");
        Calendar fechaNacimiento = Calendar.getInstance();
        fechaNacimiento.set(1990, Calendar.JANUARY, 1); // Por ejemplo, 1 de enero de 1990
        persona.setFechaNacimiento(fechaNacimiento);

        try {
            DAO.agregarPersona(persona);
        } catch (PersistenciaException e) {
            System.out.println("Error al agregar la persona: " + e.getMessage());
        }
        
        System.out.println("A ver si no explita");
    }

}
