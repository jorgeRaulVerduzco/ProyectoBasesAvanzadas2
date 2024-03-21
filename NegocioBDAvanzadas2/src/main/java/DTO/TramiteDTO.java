/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Calendar;

/**
 *
 * @author INEGI
 */
public class TramiteDTO {
    private Long id;
    private float costo;
    private Calendar fechaTramite;
    private PersonaDTO persona;
    private Calendar fechaVigencia;

    public TramiteDTO(float costo, Calendar fechaTramite, PersonaDTO persona, Calendar fechaVigencia) {
        this.costo = costo;
        this.fechaTramite = fechaTramite;
        this.persona = persona;
        this.fechaVigencia = fechaVigencia;
    }

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public Calendar getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(Calendar fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public Calendar getFechaVigencia() {
        return fechaVigencia;
    }

    public void setFechaVigencia(Calendar fechaVigencia) {
        this.fechaVigencia = fechaVigencia;
    }

    @Override
    public String toString() {
        return "Tramite{" +
                "id=" + id +
                ", costo=" + costo +
                ", fechaTramite=" + fechaTramite +
                ", persona=" + persona +
                ", fechaVigencia=" + fechaVigencia +
                '}';
    }
}
