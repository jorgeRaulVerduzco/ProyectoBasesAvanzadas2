/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Automovil;
import Dominio.Persona;
import IPersistencia.IAutomovilDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(automovil);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
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

    public List<Automovil> buscarAutomovilesPorPersona(Persona persona) {
        List<Automovil> automoviles = new ArrayList<>();
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Automovil> query = entityManager.createQuery("SELECT a FROM Automovil a WHERE a.persona = :persona", Automovil.class);
            query.setParameter("persona", persona);
            automoviles = query.getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al buscar automóviles por persona", e);
        } finally {
            entityManager.close();
        }

        return automoviles;
    }

    public List<Automovil> buscarAutomovilesPorRFC(String rfc) {
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            TypedQuery<Automovil> query = entityManager.createQuery(
                    "SELECT a FROM Automovil a JOIN a.persona p WHERE p.rfc = :rfc",
                    Automovil.class
            );
            query.setParameter("rfc", rfc);
            List<Automovil> automoviles = query.getResultList();
            entityManager.getTransaction().commit();
            return automoviles;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error al buscar automóviles por RFC de persona", e);
        } finally {
            entityManager.close();
        }
    }
}
