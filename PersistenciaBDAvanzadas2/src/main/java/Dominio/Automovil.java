/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author INEGI
 */
@Entity
@Table(name = "automovil")
public class Automovil implements Serializable {
//prueba
//mapeo
    @Id
    @Column(name = "idAutomovil")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAutomovil;

    @Column(name = "numeroSerie", nullable = false)
    private String numeroSerie;

    @Column(name = "nombreMarca", nullable = false, length = 75)
    private String marca;

    @Column(name = "linea", nullable = false, length = 75)
    private String linea;

    @Column(name = "color", nullable = false, length = 75)
    private String color;

    @Column(name = "modelo", nullable = false, length = 75)
    private String modelo;

    @ManyToOne
    @JoinColumn(name = "idPersona")
    private Persona persona;

    @OneToMany(mappedBy = "automovil", cascade = CascadeType.ALL)
    private List<Placa> placas;

    public Automovil() {
    }

    public Automovil(String numeroSerie, String marca, String linea, String color, String modelo, Persona persona) {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void agregarPlacas(Placa placa) {
        this.placas.add(placa);
    }

    public List<Placa> getPlacas() {
        return placas;
    }

    public void setPlacas(List<Placa> placas) {
        this.placas = placas;
    }

    @Override
    public String toString() {
        return "Automovil{" + "idAutomovil=" + idAutomovil + ", numeroSerie=" + numeroSerie + ", marca=" + marca + ", linea=" + linea + ", color=" + color + ", modelo=" + modelo + ", persona=" + persona + '}';
    }

}
