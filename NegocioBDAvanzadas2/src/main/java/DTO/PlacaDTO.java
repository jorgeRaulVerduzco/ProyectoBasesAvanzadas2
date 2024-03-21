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
public class PlacaDTO extends TramiteDTO {

    private Long id;
    private String digitosPlaca;
    private String estado;
    private AutomovilDTO automovil;

    public PlacaDTO(String digitosPlaca, String estado, AutomovilDTO automovil, float costo, Calendar fechaTramite, PersonaDTO persona, Calendar fechaVigencia) {
        super(costo, fechaTramite, persona, fechaVigencia);
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

    public AutomovilDTO getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilDTO automovil) {
        this.automovil = automovil;
    }

    @Override
    public String toString() {
        return "PlacaDto{"
                + "id=" + id
                + ", digitosPlaca='" + digitosPlaca + '\''
                + ", estado='" + estado + '\''
                + ", automovil=" + automovil
                + ", costo=" + getCosto()
                + ", fechaTramite=" + getFechaTramite().getTime()
                + ", fechaVigencia=" + getFechaVigencia().getTime()
                + '}';
    }
}
