/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package INegocio;

import DTO.LicenciaDTO;
import DTO.PersonaDTO;
import Dominio.Persona;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IAgregarLicenciaBO {

    public void AgregarLicencia(LicenciaDTO licenciaDTO, String rfcPersona);

    public void agregarPersona(PersonaDTO personaDTO);

    public void incersionMasiva();

    public List<Persona> ListaPersonas();

public Persona obtenerPersonaPorRFC(String rfc);

}
