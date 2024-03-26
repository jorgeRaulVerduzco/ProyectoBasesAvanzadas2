/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INegocio;

import DTO.AutomovilDTO;
import DTO.PersonaDTO;
import DTO.PlacaDTO;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IAgregarPlacaBO {
    public void AgregarPlaca(AutomovilDTO automovilDTO,PlacaDTO placaDTO);
}
