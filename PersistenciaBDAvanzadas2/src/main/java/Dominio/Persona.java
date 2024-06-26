/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import Excepciones.EncriptadorTelefono;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author INEGI
 */
@Entity
@Table(name = "Persona")
public class Persona implements Serializable {

    //mapeo 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(name = "nombres", nullable = false, length = 75)
    private String nombres;

    @Column(name = "apellidoPaterno", nullable = false, length = 75)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false, length = 75)
    private String apellidoMaterno;

    @Column(name = "Curp", nullable = false, length = 18)
    private String curp;

    @Column(name = "rfc", nullable = false, length = 13)
    private String rfc;
    @Column(name = "telefono", nullable = false, length = 255)
    private String telefono;

    @Column(name = "FechaNacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    Calendar fechaNacimiento;

@Column(name = "Discapacidad", nullable = false)
private String discapacidad;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<Automovil> automoviles;

    @OneToMany(mappedBy = "persona", cascade = CascadeType.PERSIST)
    private List<Tramite> tramites;

    public Persona() {
    }

    public Persona(String nombres, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String telefono, Calendar fechaNacimiento, String discapacidad) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.automoviles = new ArrayList<>();
        this.tramites = new ArrayList<>();
        this.discapacidad = discapacidad;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }
     public String getTelefono() {
       try {
        return EncriptadorTelefono.desencriptar(telefono);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
    }

    public void setTelefono(String telefono) {
        try {
            this.telefono = EncriptadorTelefono.encriptar(telefono);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void agregarTramites(Tramite tramite) {
        this.tramites.add(tramite);
    }

    public void agregarAutoMovil(Automovil automovil) {
        this.automoviles.add(automovil);
    }

    public List<Tramite> getTramites() {
        return tramites;
    }

    public void setTramites(List<Tramite> tramites) {
        this.tramites = tramites;
    }

    public List<Automovil> getAutomoviles() {
        return automoviles;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public void setAutomoviles(List<Automovil> automoviles) {
        this.automoviles = automoviles;
    }

    @Override
    public String toString() {
        return "Persona{"
                + "idPersona=" + idPersona
                + ", nombres='" + nombres + '\''
                + ", apellidoPaterno='" + apellidoPaterno + '\''
                + ", apellidoMaterno='" + apellidoMaterno + '\''
                + ", curp='" + curp + '\''
                + ", rfc='" + rfc + '\''
                + ", telefono='" + telefono + '\''
                + ", fechaNacimiento=" + fechaNacimiento.getTime()
                + ", automoviles=" + automoviles
                + ", tramites=" + tramites
                + ", discapacidad=" + discapacidad
                + '}';
    }

}
