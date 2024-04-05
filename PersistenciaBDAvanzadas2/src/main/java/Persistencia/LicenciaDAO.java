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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author INEGI
 */
/**
 * Esta clase implementa la interfaz ILicenciaDAO y proporciona métodos para
 * interactuar con la base de datos relacionados con licencias.
 */
public class LicenciaDAO implements ILicenciaDAO {

    private EntityManagerFactory emf;

    /**
     * Constructor de la clase que inicializa la factoría de EntityManager
     * utilizando la unidad de persistencia "ConexionPU".
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
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Obtener la fecha de vigencia de la nueva licencia
            Calendar fechaVigenciaNuevaLicencia = Calendar.getInstance();

            // Consultar y actualizar las licencias anteriores de la persona
            TypedQuery<Licencia> query = entityManager.createQuery(
                    "SELECT l FROM Licencia l WHERE l.persona.idPersona = :idPersona", Licencia.class);
            query.setParameter("idPersona", licencia.getPersona().getIdPersona());
            List<Licencia> licenciasAnteriores = query.getResultList();

            if (!licenciasAnteriores.isEmpty()) {
                // Mostrar JOptionPane solo si hay licencias anteriores
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea vencer las licencias anteriores?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    for (Licencia licenciaAnterior : licenciasAnteriores) {
                        licenciaAnterior.setFechaVigencia(fechaVigenciaNuevaLicencia);
                        entityManager.merge(licenciaAnterior);
                    }
                    // Persistir la nueva licencia solo si se elige vencer las anteriores
                    entityManager.persist(licencia);

                }
            } else {
                // Si no hay licencias anteriores, persistir la nueva licencia directamente
                entityManager.persist(licencia);
                JOptionPane.showMessageDialog(null, "Se ha generado la licencia correctamente.", "Licencia Generada", JOptionPane.INFORMATION_MESSAGE);

            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al agregar licencia", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Método para obtener el historial de licencias de una persona específica.
     *
     * @param idPersona El ID de la persona de la que se desea obtener el
     * historial de licencias.
     * @return Una lista de arrays de objetos que representan el historial de
     * licencias de la persona especificada.
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
    
public boolean verificarLicenciaNoCaducadaPorRFC(String rfc) {
    EntityManager entityManager = emf.createEntityManager();

    try {
        Date fechaActual = new Date();

        TypedQuery<Long> query = entityManager.createQuery(
            "SELECT COUNT(l) FROM Licencia l "
            + "JOIN l.persona p "
            + "JOIN Tramite t ON l.id = t.id "
            + "WHERE p.rfc = :rfc AND t.fechaVigencia > :fechaActual", Long.class);
        query.setParameter("rfc", rfc);
        query.setParameter("fechaActual", fechaActual);

        Long count = query.getSingleResult();
        return count > 0;
    } finally {
        entityManager.close();
    }
}
}
