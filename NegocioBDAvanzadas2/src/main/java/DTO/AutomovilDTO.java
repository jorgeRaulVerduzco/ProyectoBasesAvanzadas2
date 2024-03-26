/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Dominio.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INEGI
 */
public class AutomovilDTO {
        private Long idAutomovil;
    private String numeroSerie;
    private String marca;
    private String linea;
    private String color;
    private String modelo;
    private PersonaDTO persona;
    private List<PlacaDTO> placas;

    public AutomovilDTO() {
    }

    
    
       public AutomovilDTO(String numeroSerie, String marca, String linea, String color, String modelo, PersonaDTO persona) {
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.linea = linea;
        this.color = color;
        this.modelo = modelo;
        this.persona = persona;

        this.placas = new ArrayList<>();
    }

    public Long getIdAutomovil() {
        return idAutomovil;
    }

    public void setIdAutomovil(Long idAutomovil) {
        this.idAutomovil = idAutomovil;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }

    public List<PlacaDTO> getPlacas() {
        return placas;
    }

    public void setPlacas(List<PlacaDTO> placas) {
        this.placas = placas;
    }

    
    public void agregarPlacas(PlacaDTO placa) {
        this.placas.add(placa);
    }
    
     @Override
    public String toString() {
        return "Automovil{" + "idAutomovil=" + idAutomovil + ", numeroSerie=" + numeroSerie + ", marca=" + marca + ", linea=" + linea + ", color=" + color + ", modelo=" + modelo + ", persona=" + persona + '}';
    }

}
