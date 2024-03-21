/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class PersonaDTO {

    private Long idPersona;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String curp;
    private String rfc;
    private String telefono;
    private Calendar fechaNacimiento;
    private List<AutomovilDTO> automoviles;
    private List<TramiteDTO> tramites;

    public PersonaDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String telefono, Calendar fechaNacimiento) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.automoviles = new ArrayList<>();
        this.tramites = new ArrayList<>();

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
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<AutomovilDTO> getAutomoviles() {
        return automoviles;
    }

    public void setAutomoviles(List<AutomovilDTO> automoviles) {
        this.automoviles = automoviles;
    }

    public List<TramiteDTO> getTramites() {
        return tramites;
    }

    public void setTramites(List<TramiteDTO> tramites) {
        this.tramites = tramites;
    }

    @Override
    public String toString() {
        return "PersonaDTO{" + "idPersona=" + idPersona + ", nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", curp=" + curp + ", rfc=" + rfc + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + ", automoviles=" + automoviles + ", tramites=" + tramites + '}';
    }

}
