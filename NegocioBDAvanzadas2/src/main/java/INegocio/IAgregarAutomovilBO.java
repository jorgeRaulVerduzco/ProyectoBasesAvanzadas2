/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INegocio;

import DTO.AutomovilDTO;
import DTO.PersonaDTO;
import Dominio.Automovil;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IAgregarAutomovilBO {
    public void AgregarAutomovil(AutomovilDTO automovilDTO);
 public List<Automovil> buscarAutomovilesPorRFC(String rfc);
}
