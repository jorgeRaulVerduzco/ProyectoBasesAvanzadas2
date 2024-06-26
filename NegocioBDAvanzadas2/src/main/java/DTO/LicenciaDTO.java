/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Dominio.TipoLicencia;
import java.util.Calendar;

/**
 *
 * @author INEGI
 */

// Atributos privados de la clase
public class LicenciaDTO extends TramiteDTO{
      private int añosVigencia;
 public LicenciaDTO() {
    }
  
 
// Constructor con parámetros para inicializar los atributos
 // Llama al constructor de la clase TramiteDTO pasando los parámetros necesarios
 
    public LicenciaDTO(int añosVigencia, float costo, Calendar fechaTramite, PersonaDTO persona, Calendar fechaVigencia) {
        super(costo, fechaTramite, persona, fechaVigencia);
        this.añosVigencia = añosVigencia;
    }

    // Getters y setters

    public int getAñosVigencia() {
        return añosVigencia;
    }

    public void setAñosVigencia(int añosVigencia) {
        this.añosVigencia = añosVigencia;
    }

 

    @Override
    public String toString() {
        return "LicenciaDto{" +
                "añosVigencia=" + añosVigencia +
                ", costo=" + getCosto() +
                ", fechaTramite=" + getFechaTramite().getTime() +
                ", fechaVigencia=" + getFechaVigencia().getTime() +
                '}';
    }
}
