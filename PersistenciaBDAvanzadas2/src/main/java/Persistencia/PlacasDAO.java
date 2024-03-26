/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Automovil;
import Dominio.Placa;
import IPersistencia.IPlacasDAO;
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
}
