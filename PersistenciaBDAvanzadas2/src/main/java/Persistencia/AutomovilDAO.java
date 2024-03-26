/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Automovil;
import IPersistencia.IAutomovilDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */
public class AutomovilDAO implements IAutomovilDAO {

    private EntityManagerFactory emf;

    public AutomovilDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void AgregarAutomovil(Automovil automovil) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(automovil);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Automovil buscarAutomovilPorNumeroSerie(String numeroSerie) {
        EntityManager em = emf.createEntityManager();
        Automovil automovil = null;

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Automovil a WHERE a.numeroSerie = :numeroSerie";
            TypedQuery<Automovil> query = em.createQuery(jpql, Automovil.class);
            query.setParameter("numeroSerie", numeroSerie);
            List<Automovil> resultList = query.getResultList();
            if (!resultList.isEmpty()) {
                automovil = resultList.get(0);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return automovil;
    }

    @Override
    public List<Automovil> buscarAutomovilesPorNumeroSerie(String numeroSerie) {
        EntityManager em = emf.createEntityManager();
        List<Automovil> automoviles = null;

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Automovil a WHERE a.numeroSerie = :numeroSerie";
            TypedQuery<Automovil> query = em.createQuery(jpql, Automovil.class);
            query.setParameter("numeroSerie", numeroSerie);
            automoviles = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return automoviles;
    }

    @Override
    public List<Automovil> buscarAutomovilesPorMarca(String marca) {
        EntityManager em = emf.createEntityManager();
        List<Automovil> automoviles = null;

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Automovil a WHERE a.marca = :marca";
            TypedQuery<Automovil> query = em.createQuery(jpql, Automovil.class);
            query.setParameter("marca", marca);
            automoviles = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return automoviles;
    }

    @Override
    public List<Automovil> buscarAutomovilesPorLinea(String linea) {
        EntityManager em = emf.createEntityManager();
        List<Automovil> automoviles = null;

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Automovil a WHERE a.linea = :linea";
            TypedQuery<Automovil> query = em.createQuery(jpql, Automovil.class);
            query.setParameter("linea", linea);
            automoviles = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return automoviles;
    }

    @Override
    public List<Automovil> buscarAutomovilesPorColor(String color) {
        EntityManager em = emf.createEntityManager();
        List<Automovil> automoviles = null;

        try {
            em.getTransaction().begin();
            String jpql = "SELECT a FROM Automovil a WHERE a.color = :color";
            TypedQuery<Automovil> query = em.createQuery(jpql, Automovil.class);
            query.setParameter("color", color);
            automoviles = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        return automoviles;
    }
}
