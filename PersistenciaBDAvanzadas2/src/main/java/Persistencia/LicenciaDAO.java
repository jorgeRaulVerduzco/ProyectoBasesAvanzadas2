/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Licencia;
import Dominio.Persona;
import IPersistencia.ILicenciaDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */

/**
 * Esta clase implementa la interfaz ILicenciaDAO y proporciona métodos para interactuar con la base de datos relacionados con licencias.
 */
public class LicenciaDAO implements ILicenciaDAO {

    private EntityManagerFactory emf;
    /**
     * Constructor de la clase que inicializa la factoría de EntityManager utilizando la unidad de persistencia "ConexionPU".
     */
    public LicenciaDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    /**
     * Método para agregar una nueva licencia a la base de datos.
     *
     * @param licencia La licencia que se va a agregar.
     */
    @Override
    public void agregarLicencia(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(licencia);
            em.getTransaction().commit();
        } catch (Exception e) {
              // Manejo de excepciones y reversión de la transacción en caso de error
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    /**
     * Método para obtener el historial de licencias de una persona específica.
     *
     * @param idPersona El ID de la persona de la que se desea obtener el historial de licencias.
     * @return Una lista de arrays de objetos que representan el historial de licencias de la persona especificada.
     */
    
    
   @Override
public List<Object[]> obtenerHistorialLicenciasPorPersona(Long idPersona) {
    EntityManager entityManager = emf.createEntityManager();
 // Consulta para seleccionar el historial de licencias asociadas a la persona especificada
    TypedQuery<Object[]> query = entityManager.createQuery(
            "SELECT l.id, l.añosVigencia, t.costo, t.fechaTramite, t.fechaVigencia "
            + "FROM Licencia l "
            + "JOIN l.persona p "
            + "JOIN Tramite t ON l.id = t.id "
            + "WHERE p.idPersona = :idPersona", Object[].class);
    query.setParameter("idPersona", idPersona);

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<Object[]> resultList = query.getResultList();
    List<Object[]> formattedResultList = new ArrayList<>();
// Formatea los resultados de la consulta antes de devolverlos
    for (Object[] result : resultList) {
        Object[] formattedResult = new Object[result.length];
        for (int i = 0; i < result.length; i++) {
            if (result[i] instanceof GregorianCalendar) {
                Date date = ((GregorianCalendar) result[i]).getTime();
                formattedResult[i] = dateFormat.format(date);
            } else {
                formattedResult[i] = result[i];
            }
        }
        formattedResultList.add(formattedResult);
    }

    return formattedResultList;
}
}
