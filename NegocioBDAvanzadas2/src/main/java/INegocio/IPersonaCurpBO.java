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
public interface IPersonaCurpBO {
    public List<Persona> buscarPersonasPorCURP(String curp);
}
