/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INegocio;

import Dominio.Persona;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IBuscarPersonasBO {
        public List<Persona> buscarPersonas(String nombre, String apellidoPaterno, String apellidoMaterno, String CURP, Integer añoNacimiento);

}
