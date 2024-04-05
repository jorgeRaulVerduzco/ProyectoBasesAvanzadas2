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
 * Esta clase implementa la interfaz IPersonaDao y proporciona métodos para
 * interactuar con la base de datos relacionados con personas.
 */
public class PersonaDAO implements IPersonaDao {

    private EntityManagerFactory emf;

    /**
     * Constructor de la clase que inicializa la factoría de EntityManager
     * utilizando la unidad de persistencia "ConexionPU".
     */
    public PersonaDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    /**
     * Método para agregar una nueva persona a la base de datos.
     *
     * @param persona La persona que se va a agregar.
     * @throws PersistenciaException Si ocurre un error durante la persistencia
     * de la persona.
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
     * Método para obtener una lista de todas las personas almacenadas en la
     * base de datos.
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
     * @return La persona correspondiente al RFC especificado, o null si no se
     * encuentra ninguna.
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

    @Override
    public List<Persona> buscarPersonas(String nombre, String apellidoPaterno, String apellidoMaterno, String CURP, Integer añoNacimiento) {
        EntityManager em = emf.createEntityManager();
        List<Persona> personas = null;

        try {
            em.getTransaction().begin();
            StringBuilder jpqlBuilder = new StringBuilder("SELECT p FROM Persona p WHERE 1 = 1");

            if (nombre != null && !nombre.isEmpty()) {
                jpqlBuilder.append(" AND p.nombres LIKE CONCAT('%', :nombre, '%')");
            }
            if (apellidoPaterno != null && !apellidoPaterno.isEmpty()) {
                jpqlBuilder.append(" AND p.apellidoPaterno LIKE CONCAT('%', :apellidoPaterno, '%')");
            }
            if (apellidoMaterno != null && !apellidoMaterno.isEmpty()) {
                jpqlBuilder.append(" AND p.apellidoMaterno LIKE CONCAT('%', :apellidoMaterno, '%')");
            }
            if (CURP != null && !CURP.isEmpty()) {
                jpqlBuilder.append(" AND p.curp LIKE CONCAT('%', :CURP, '%')");
            }
            if (añoNacimiento != null) {
                jpqlBuilder.append(" AND FUNCTION('YEAR', p.fechaNacimiento) = :añoNacimiento");
            }

            TypedQuery<Persona> query = em.createQuery(jpqlBuilder.toString(), Persona.class);

            // Establecer parámetros para cada condición
            if (nombre != null && !nombre.isEmpty()) {
                query.setParameter("nombre", nombre);
            }
            if (apellidoPaterno != null && !apellidoPaterno.isEmpty()) {
                query.setParameter("apellidoPaterno", apellidoPaterno);
            }
            if (apellidoMaterno != null && !apellidoMaterno.isEmpty()) {
                query.setParameter("apellidoMaterno", apellidoMaterno);
            }
            if (CURP != null && !CURP.isEmpty()) {
                query.setParameter("CURP", CURP);
            }
            if (añoNacimiento != null) {
                query.setParameter("añoNacimiento", añoNacimiento);
            }

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
