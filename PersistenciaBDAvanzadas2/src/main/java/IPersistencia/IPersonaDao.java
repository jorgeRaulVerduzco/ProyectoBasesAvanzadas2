/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Persona;
import Excepciones.PersistenciaException;

/**
 *
 * @author INEGI
 */
public interface IPersonaDao {
    
    public void agregarPersona(Persona persona)throws PersistenciaException;
    
}
