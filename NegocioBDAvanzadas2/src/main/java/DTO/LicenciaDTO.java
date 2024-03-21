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
public class LicenciaDTO extends TramiteDTO{
      private int añosVigencia;
    private TipoLicencia tipoLicencia;

    public LicenciaDTO(int añosVigencia, TipoLicencia tipoLicencia, float costo, Calendar fechaTramite, PersonaDTO persona, Calendar fechaVigencia) {
        super(costo, fechaTramite, persona, fechaVigencia);
        this.añosVigencia = añosVigencia;
        this.tipoLicencia = tipoLicencia;
    }

    // Getters y setters

    public int getAñosVigencia() {
        return añosVigencia;
    }

    public void setAñosVigencia(int añosVigencia) {
        this.añosVigencia = añosVigencia;
    }

    public TipoLicencia getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(TipoLicencia tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    @Override
    public String toString() {
        return "LicenciaDto{" +
                "añosVigencia=" + añosVigencia +
                ", tipoLicencia=" + tipoLicencia +
                ", costo=" + getCosto() +
                ", fechaTramite=" + getFechaTramite().getTime() +
                ", fechaVigencia=" + getFechaVigencia().getTime() +
                '}';
    }
}
