/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Automovil;
import Dominio.Persona;
import Dominio.Placa;
import IPersistencia.IPlacasDAO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */
public class PlacasDAO implements IPlacasDAO{

    private EntityManagerFactory emf;

    public PlacasDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
       public void agregarPlacas(Automovil automovil, Placa placa) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            desactivarPlacasActivas(automovil);

            automovil.agregarPlacas(placa);
            placa.setAutomovil(automovil);

            entityManager.persist(automovil);
            entityManager.persist(placa);

            entityManager.flush();
            entityManager.refresh(automovil);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al agregar placa", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void desactivarPlacasActivas(Automovil automovil) {
        EntityManager entityManager = emf.createEntityManager(); 
        entityManager.getTransaction().begin();
        try {

            TypedQuery<Placa> query = entityManager.createQuery(
                    "SELECT p FROM Placa p WHERE p.automovil = :automovil AND p.estado = :estadoActivo", Placa.class)
                    .setParameter("automovil", automovil)
                    .setParameter("estadoActivo", "ACTIVA");

            List<Placa> placasActivas = query.getResultList();

            for (Placa placa : placasActivas) {
                placa.setEstado("INACTIVA");
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
@Override
    public List<Placa> obtenerHistorialDePlacasPorPersona(List<Persona> personas) {
    List<Placa> historialPlacas = new ArrayList<>();
    EntityManager entityManager = emf.createEntityManager();

    try {
        entityManager.getTransaction().begin();
        TypedQuery<Placa> query = entityManager.createQuery("SELECT p FROM Placa p WHERE p.persona IN :personas", Placa.class);
        query.setParameter("personas", personas);
        historialPlacas = query.getResultList();
        entityManager.getTransaction().commit();
    } catch (Exception e) {
        entityManager.getTransaction().rollback();
        throw new RuntimeException("Error al obtener el historial de placas de las personas", e);
    } finally {
        entityManager.close();
    }

    return historialPlacas;
}
}
