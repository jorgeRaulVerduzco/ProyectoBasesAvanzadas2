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
@Entity
@Table(name = "Placas")
@DiscriminatorValue(value = "Placas")
@PrimaryKeyJoinColumn(name = "idPlacas")
public class Placa extends Tramite implements Serializable {

    @Id
    @Column(name = "idPlaca")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "digitosPlaca", nullable = false)
    private String digitosPlaca;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idAutomovil")
    private Automovil automovil;

    public Placa() {
    }

    public Placa(Long id, String digitosPlaca, String estado, Automovil automovil, float costo, Calendar fechaTramite, Persona persona, Calendar fechaVigencia) {
        super(costo, fechaTramite, persona, fechaVigencia);
        this.id = id;
        this.digitosPlaca = digitosPlaca;
        this.estado = estado;
        this.automovil = automovil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDigitosPlaca() {
        return digitosPlaca;
    }

    public void setDigitosPlaca(String digitosPlaca) {
        this.digitosPlaca = digitosPlaca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Automovil getAutomovil() {
        return automovil;
    }

    public void setAutomovil(Automovil automovil) {
        this.automovil = automovil;
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

    @Override
    public void setFechaTramite(Calendar fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    @Override
    public String toString() {
        return "Placa{"
                + "id=" + id
                + ", digitosPlaca='" + digitosPlaca + '\''
                + ", estado='" + estado + '\''
                + ", automovil=" + automovil
                + ", costo=" + costo
                + ", fechaTramite=" + fechaTramite.getTime()
                + ", fechaVigencia=" + fechaVigencia.getTime()
                + '}';
    }

}
