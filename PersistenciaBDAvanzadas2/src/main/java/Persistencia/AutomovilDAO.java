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
    
    /**
     * Método para agregar un nuevo automóvil a la base de datos.
     *
     * @param automovil El automóvil que se va a agregar.
     */
    @Override
    public void AgregarAutomovil(Automovil automovil) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(automovil);
            transaction.commit();
        } catch (Exception e) {
             // Manejo de excepciones y reversión de la transacción en caso de error
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    /**
     * Método para buscar un automóvil por su número de serie.
     *
     * @param numeroSerie El número de serie del automóvil que se desea buscar.
     * @return El automóvil correspondiente al número de serie especificado, o null si no se encuentra ninguno.
     */
    
    @Override
    public Automovil buscarAutomovilPorNumeroSerie(String numeroSerie) {
        EntityManager em = emf.createEntityManager();
        Automovil automovil = null;

        try {
            em.getTransaction().begin();
            // Consulta para seleccionar el automóvil por su número de serie
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

     /**
     * Método para buscar automóviles por su número de serie.
     *
     * @param numeroSerie El número de serie del automóvil que se desea buscar.
     * @return Una lista de automóviles que tienen el número de serie especificado.
     */
    @Override
    public List<Automovil> buscarAutomovilesPorNumeroSerie(String numeroSerie) {
        EntityManager em = emf.createEntityManager();
        List<Automovil> automoviles = null;

        try {
            em.getTransaction().begin();
            // Consulta para seleccionar los automóviles por su número de serie
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
 /**
     * Método para buscar automóviles por marca.
     *
     * @param marca La marca de los automóviles que se desean buscar.
     * @return Una lista de automóviles que tienen la marca especificada.
     */
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
    
    /**
     * Método para buscar automóviles por línea.
     *
     * @param linea La línea de los automóviles que se desean buscar.
     * @return Una lista de automóviles que tienen la línea especificada.
     */
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

    /**
     * Método para buscar automóviles por color.
     *
     * @param color El color de los automóviles que se desean buscar.
     * @return Una lista de automóviles que tienen el color especificado.
     */
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
    
    /**
     * Método para buscar automóviles por persona.
     *
     * @param persona La persona a la que pertenecen los automóviles que se desean buscar.
     * @return Una lista de automóviles que pertenecen a la persona especificada.
     * @throws RuntimeException Si ocurre un error durante la búsqueda de automóviles por persona.
     */

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
    
    
    @Override
    public boolean existeNumeroSerie(String numeroSerie) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            // Consulta para contar la cantidad de automóviles con el número de serie especificado
            String jpql = "SELECT COUNT(a) FROM Automovil a WHERE a.numeroSerie = :numeroSerie";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("numeroSerie", numeroSerie);
            Long count = query.getSingleResult();
            em.getTransaction().commit();

            // Si la cantidad es mayor que cero, significa que el número de serie ya existe
            return count > 0;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            // En caso de error, se puede manejar de diferentes formas, como lanzar una excepción o devolver false
            return false;
        } finally {
            em.close();
        }
    }
}
