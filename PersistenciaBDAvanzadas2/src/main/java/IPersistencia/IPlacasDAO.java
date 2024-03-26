/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Automovil;
import Dominio.Persona;
import Dominio.Placa;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IPlacasDAO {

    public void agregarPlacas(Automovil automovil, Placa placa);

    public void desactivarPlacasActivas(Automovil automovil);

    public List<Placa> obtenerTodasLasPlacas();

    public List<Placa> obtenerHistorialDePlacasPorAutomovil(List<Automovil> automoviles);

    public List<Placa> obtenerHistorialDePlacasPorPersona(List<Persona> personas);

}
