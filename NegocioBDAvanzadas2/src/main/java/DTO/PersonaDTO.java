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

    // Atributos privados de la clase
    private Long idPersona; // Identificador único de la persona
    private String nombres;// Nombres de la persona
    private String apellidoPaterno;// Apellido paterno de la persona
    private String apellidoMaterno;// Apellido materno de la persona
    private String curp;// CURP  de la persona
    private String rfc;//RFC de la person
    private String telefono;
    private Calendar fechaNacimiento;
    private String discapacidad;// Información sobre discapacidad de la persona
    private List<AutomovilDTO> automoviles;// Lista de automóviles asociados a la persona
    private List<TramiteDTO> tramites;// Lista de trámites asociados a la persona
    

    public PersonaDTO() {
    }
// Constructor con parámetros para inicializar los atributos
    
    public PersonaDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String curp, String rfc, String telefono, Calendar fechaNacimiento,String discapacidad) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.discapacidad=discapacidad;
        this.automoviles = new ArrayList<>();
        this.tramites = new ArrayList<>();

    }

    // Métodos getter y setter para acceder y modificar los atributos privados
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
    // Método para agregar un trámite a la lista de trámites de la persona
  public void agregarTramites(TramiteDTO tramite) {
        this.tramites.add(tramite);
    }
 // Método para agregar un automóvil a la lista de automóviles de la persona
  
    public void agregarAutoMovil(AutomovilDTO automovil) {
        this.automoviles.add(automovil);
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
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
                +", discapacidad= "+ discapacidad
                + ", automoviles=" + automoviles
                + ", tramites=" + tramites
                + '}';
    }


}
