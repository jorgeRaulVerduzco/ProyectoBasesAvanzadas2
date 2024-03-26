/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IPersistencia;

import Dominio.Automovil;
import java.util.List;

/**
 *
 * @author INEGI
 */
public interface IAutomovilDAO {

    public void AgregarAutomovil(Automovil automovil);

    public List<Automovil> buscarAutomovilesPorNumeroSerie(String numeroSerie);

    public List<Automovil> buscarAutomovilesPorMarca(String marca);

    public List<Automovil> buscarAutomovilesPorColor(String color);

    public List<Automovil> buscarAutomovilesPorLinea(String linea);
    
    public Automovil buscarAutomovilPorNumeroSerie(String numeroSerie);
}
