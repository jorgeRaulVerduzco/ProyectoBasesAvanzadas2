/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author INEGI
 */
@Entity
@Table(name = "Tramite")
@DiscriminatorColumn(name = "tipoTramite", length = 50)
@Inheritance(strategy = InheritanceType.JOINED)
public class Tramite implements Serializable {

    @Id
    @Column(name = "idTramite")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "costo", nullable = false)
    float costo;

    @Column(name = "fechaTramite", nullable = true)
    @Temporal(TemporalType.DATE)
    Calendar fechaTramite;

    @ManyToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @Column(name = "FechaVigencia", nullable = false)
    @Temporal(TemporalType.DATE)
    Calendar fechaVigencia;

    public Tramite() {
    }

    public Tramite(float costo, Calendar fechaTramite, Persona persona, Calendar fechaVigencia) {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
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
        return "Tramite{" + "id=" + id + ", costo=" + costo + ", fechaTramite=" + fechaTramite + ", persona=" + persona + ", fechaVigencia=" + fechaVigencia + '}';
    }

}
