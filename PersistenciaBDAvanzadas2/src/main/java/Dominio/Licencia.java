/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author INEGI
 */
//mapeo
@Entity
@Table(name = "Licencia")
@DiscriminatorValue(value = "licencia")
@PrimaryKeyJoinColumn(name = "idLicencia")
public class Licencia extends Tramite implements Serializable {

    @Column(name = "añosVigencia", nullable = false)
    private int añosVigencia;



    public Licencia() {

    }

    public Licencia(int añosVigencia,float costo, Calendar fechaTramite, Persona persona, Calendar fechaVigencia) {
        super(costo, fechaTramite, persona, fechaVigencia);
        this.añosVigencia = añosVigencia;
    }

    public int getAñosVigencia() {
        return añosVigencia;
    }

    public void setAñosVigencia(int añosVigencia) {
        this.añosVigencia = añosVigencia;
    }

  

    @Override
    public float getCosto() {
        return costo;
    }

    @Override
    public void setCosto(float costo) {
        this.costo = costo;
    }

    @Override
    public Calendar getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(Calendar fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    @Override
    public String toString() {
        return "Licencia{"
                + "añosVigencia=" + añosVigencia
                + ", costo=" + costo
                + ", fechaTramite=" + fechaTramite.getTime()
                + ", fechaVigencia=" + fechaVigencia.getTime()
                + '}';
    }
}
