/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Licencia;
import Dominio.Persona;
import Dominio.Placa;
import Dominio.Tramite;
import IPersistencia.ITramiteDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */


/**
 * Esta clase implementa la interfaz ITramiteDAO y proporciona métodos para buscar trámites en la base de datos.
 */
public class TramiteDAO implements ITramiteDAO {
 // Factoría de EntityManager para gestionar las entidades de persistencia
    private EntityManagerFactory emf;
/**
     * Constructor de la clase que inicializa la factoría de EntityManager utilizando la unidad de persistencia "ConexionPU".
     */
    public TramiteDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }
 /**
     * Método para buscar trámites en la base de datos basados en varios criterios de búsqueda.
     *
     * @param tipoTramite     El tipo de trámite a buscar (Placas, Licencia u Otro).
     * @param fechaInicio     La fecha de inicio del período de búsqueda.
     * @param fechaFin        La fecha de fin del período de búsqueda.
     * @param nombre          El nombre de la persona asociada al trámite (opcional).
     * @param apellidoPaterno El apellido paterno de la persona asociada al trámite (opcional).
     * @param apellidoMaterno El apellido materno de la persona asociada al trámite (opcional).
     * @return Una lista de arrays de objetos que representan los trámites encontrados.
     */
    @Override
    public List<Object[]> buscarTramites(String tipoTramite, Calendar fechaInicio, Calendar fechaFin, String nombre, String apellidoPaterno, String apellidoMaterno) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> tramites = null;

        try {
            em.getTransaction().begin();
             // Construcción de la consulta JPQL para buscar trámites
            String jpql = "SELECT t.fechaTramite, "
                    + "CASE TYPE(t) "
                    + "WHEN Placa THEN 'Placas' "
                    + "WHEN Licencia THEN 'Licencia' "
                    + "ELSE 'Otro' END, "
                    + "p.nombres, p.apellidoPaterno, p.apellidoMaterno, t.costo "
                    + "FROM Tramite t JOIN t.persona p "
                    + "WHERE (:tipo IS NULL OR TYPE(t) = :tipo) "
                    + "AND (:fechaInicio IS NULL OR :fechaFin IS NULL OR t.fechaTramite BETWEEN :fechaInicio AND :fechaFin) "
                    + "AND (:nombre IS NULL OR p.nombres LIKE :nombre) "
                    + "AND (:apellidoPaterno IS NULL OR p.apellidoPaterno LIKE :apellidoPaterno) "
                    + "AND (:apellidoMaterno IS NULL OR p.apellidoMaterno LIKE :apellidoMaterno)";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            // Establecimiento de parámetros para la consulta
            query.setParameter("tipo", tipoTramite.equals("Placas") ? Placa.class
                    : tipoTramite.equals("Licencia") ? Licencia.class : null);
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("nombre", "%" + nombre + "%");
            query.setParameter("apellidoPaterno", "%" + apellidoPaterno + "%");
            query.setParameter("apellidoMaterno", "%" + apellidoMaterno + "%");
             // Ejecución de la consulta y obtención de los resultados
            tramites = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            // Manejo de excepciones y reversión de la transacción en caso de error
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cierre del EntityManager
            em.close();
        }

        return tramites;
    }
}
