/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Persona;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IPersonaDao {
    
    public void agregarPersona(Persona persona)throws PersistenciaException;
    
    public List<Persona> ListaPersonas();
    
    public Persona obtenerPersonaPorRFC(String rfc);
    
    public List<Persona> buscarPersonasPorNombre(String nombre);
    
    public List<Persona> buscarPersonasPorAñoNacimiento(int añoNacimiento);
    
    public List<Persona> buscarPersonasPorCURP(String curp) ;
}
