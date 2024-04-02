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

/**
 * Esta clase implementa la interfaz IPersonaDao y proporciona métodos para interactuar con la base de datos relacionados con personas.
 */
public class PersonaDAO implements IPersonaDao {

    private EntityManagerFactory emf;
/**
     * Constructor de la clase que inicializa la factoría de EntityManager utilizando la unidad de persistencia "ConexionPU".
     */
    public PersonaDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }
/**
     * Método para agregar una nueva persona a la base de datos.
     *
     * @param persona La persona que se va a agregar.
     * @throws PersistenciaException Si ocurre un error durante la persistencia de la persona.
     */
    @Override
    public void agregarPersona(Persona persona) throws PersistenciaException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
// Persiste la persona en la base de datos
        try {
            em.persist(persona);
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
     * Método para obtener una lista de todas las personas almacenadas en la base de datos.
     *
     * @return Una lista de todas las personas almacenadas.
     */
    
    
    @Override
    public List<Persona> ListaPersonas() {
        EntityManager em = emf.createEntityManager();
        List<Persona> personas = null;
        try {
             // Consulta para seleccionar todas las personas
            TypedQuery<Persona> query = em.createQuery("SELECT p FROM Persona p", Persona.class);
            personas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return personas;
    }
    
    
    /**
     * Método para obtener una persona por su RFC.
     *
     * @param rfc El RFC de la persona que se desea obtener.
     * @return La persona correspondiente al RFC especificado, o null si no se encuentra ninguna.
     */

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
/**
     * Método para buscar personas por su nombre.
     *
     * @param nombre El nombre (o parte del nombre) de la persona que se desea buscar.
     * @return Una lista de personas cuyos nombres coinciden (parcialmente) con el nombre especificado.
     */
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
/**
     * Método para buscar personas por su año de nacimiento.
     *
     * @param añoNacimiento El año de nacimiento de las personas que se desean buscar.
     * @return Una lista de personas nacidas en el año especificado.
     */
    @Override
public List<Persona> buscarPersonasPorAñoNacimiento(int añoNacimiento) {
    EntityManager em = emf.createEntityManager();
    List<Persona> personas = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT p FROM Persona p WHERE FUNCTION('YEAR', p.fechaNacimiento) = :añoNacimiento";
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
