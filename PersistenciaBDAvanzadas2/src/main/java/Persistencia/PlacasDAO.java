/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Automovil;
import Dominio.Persona;
import Dominio.Placa;
import Dominio.Tramite;
import IPersistencia.IPlacasDAO;
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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */
public class PlacasDAO implements IPlacasDAO {

    private EntityManagerFactory emf;

    public PlacasDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void agregarPlacas(Automovil automovil, Placa placa) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            Query query = entityManager.createQuery("SELECT COUNT(p) FROM Placa p WHERE p.automovil = :automovil");
            query.setParameter("automovil", automovil);
            long placasRegistradas = (long) query.getSingleResult();

            if (placasRegistradas > 0) {
                placa.setCosto(1000);
            } else {
                placa.setCosto(1500);
            }

            desactivarPlacasActivas(automovil);

            automovil.agregarPlacas(placa);
            placa.setAutomovil(automovil);

            entityManager.persist(placa);
            entityManager.flush();

            if (!entityManager.contains(automovil)) {
                automovil = entityManager.merge(automovil);
            }
            entityManager.refresh(automovil);

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al agregar placa", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Método para desactivar todas las placas activas asociadas a un automóvil
     * dado.
     *
     * @param automovil El automóvil del que se desactivarán las placas activas.
     */
    @Override
    public void desactivarPlacasActivas(Automovil automovil) {
       EntityManager entityManager = emf.createEntityManager();
    entityManager.getTransaction().begin();
    try {
        TypedQuery<Placa> query = entityManager.createQuery(
                "SELECT p FROM Placa p WHERE p.automovil = :automovil AND p.estado = :activo", Placa.class)
                .setParameter("automovil", automovil)
                .setParameter("activo", "activo");
        List<Placa> placasActivas = query.getResultList();
        for (Placa placa : placasActivas) {
            placa.setEstado("INACTIVA");
            Calendar fechaActual = Calendar.getInstance();
            placa.setFechaVigencia(fechaActual); 
            Tramite tramite = (Tramite) placa;
                tramite.setFechaVigencia(fechaActual);
                entityManager.merge(tramite);
            
            entityManager.merge(placa);
        }

        entityManager.getTransaction().commit();
    } catch (Exception e) {
        entityManager.getTransaction().rollback();
        throw new RuntimeException("Error al desactivar placas activas", e);
    } finally {
        entityManager.close();
    }
    }

    @Override
    public List<Placa> obtenerTodasLasPlacas() {
        List<Placa> placas = new ArrayList<>();
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Placa> query = entityManager.createQuery("SELECT p FROM Placa p", Placa.class);
            placas = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al obtener todas las placas", e);
        } finally {
            entityManager.close();
        }

        return placas;
    }

    /**
     * Método para obtener todas las placas almacenadas en la base de datos.
     *
     * @return Una lista de todas las placas almacenadas.
     */
    @Override
    public List<Placa> obtenerHistorialDePlacasPorAutomovil(List<Automovil> automoviles) {
        List<Placa> historialPlacas = new ArrayList<>();
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Placa> query = entityManager.createQuery("SELECT p FROM Placa p WHERE p.automovil IN :automoviles", Placa.class);
            query.setParameter("automoviles", automoviles);
            historialPlacas = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al obtener el historial de placas del automóvil", e);
        } finally {
            entityManager.close();
        }

        return historialPlacas;
    }

    /**
     * Método para obtener el historial de placas asociadas a una persona
     * específica.
     *
     * @param idPersona El ID de la persona de la que se desea obtener el
     * historial de placas.
     * @return Una lista de arrays de objetos que representan el historial de
     * placas de la persona especificada.
     */
    @Override
    public List<Object[]> obtenerHistorialPlacasPorPersona(Long idPersona) {
        EntityManager entityManager = emf.createEntityManager();
// Consulta para seleccionar el historial de placas asociadas a la persona especificada
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT p.id, p.digitosPlaca, p.estado, p.costo, p.fechaTramite, p.fechaVigencia "
                + "FROM Placa p "
                + "INNER JOIN p.persona pers "
                + "WHERE pers.idPersona = :idPersona", Object[].class);
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

    @Override
    public List<Object[]> obtenerHistorialPlacasPorAutomovil(String numeroSerie) {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Object[]> query = entityManager.createQuery(
                "SELECT p.id, p.digitosPlaca, p.estado, p.costo, p.fechaTramite , p.fechaVigencia "
                + "FROM Placa p "
                + "WHERE p.automovil.numeroSerie = :numeroSerie", Object[].class);
        query.setParameter("numeroSerie", numeroSerie);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Object[]> resultList = query.getResultList();
        List<Object[]> formattedResultList = new ArrayList<>();
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
