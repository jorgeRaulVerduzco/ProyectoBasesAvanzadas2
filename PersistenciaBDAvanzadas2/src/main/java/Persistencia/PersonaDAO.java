/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Persona;
import Excepciones.PersistenciaException;
import IPersistencia.IPersonaDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */
public class PersonaDAO implements IPersonaDao {

    private EntityManagerFactory emf;

    public PersonaDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void agregarPersona(Persona persona) throws PersistenciaException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(persona);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Persona> ListaPersonas() {
        EntityManager em = emf.createEntityManager();
        List<Persona> personas = null;
        try {
            TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
            personas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return personas;
    }

@Override
public Persona obtenerPersonaPorRFC(String rfc) {
    EntityManager em = emf.createEntityManager();
    Persona persona = null;
    try {
        TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p WHERE p.rfc = :rfc", Persona.class);
        query.setParameter("rfc", rfc);
        // Obtenemos el resultado de la consulta, si existe
        List<Persona> personas = query.getResultList();
        // Si la lista no está vacía, tomamos la primera persona encontrada
        if (!personas.isEmpty()) {
            persona = personas.get(0);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        em.close();
    }
    return persona;
}

    @Override
    public List<Persona> buscarPersonasPorNombre(String nombre) {
    EntityManager em = emf.createEntityManager();
    List<Persona> personas = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Persona p WHERE p.nombres LIKE CONCAT('%', :nombre, '%')";
        TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
        query.setParameter("nombre", nombre);
        personas = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return personas;
}

    @Override
    public List<Persona> buscarPersonasPorAñoNacimiento(int añoNacimiento) {
    EntityManager em = emf.createEntityManager();
    List<Persona> personas = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Persona p WHERE YEAR(p.fechaNacimiento) = :añoNacimiento";
        TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
        query.setParameter("añoNacimiento", añoNacimiento);
        personas = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return personas;
}

    @Override
    public List<Persona> buscarPersonasPorCURP(String curp) {
    EntityManager em = emf.createEntityManager();
    List<Persona> personas = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Persona p WHERE p.curp = :curp";
        TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
        query.setParameter("curp", curp);
        personas = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return personas;
}
}
