/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Licencia;
import Dominio.Persona;
import IPersistencia.ILicenciaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */
public class LicenciaDAO implements ILicenciaDAO {

    private EntityManagerFactory emf;

    public LicenciaDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }

    @Override
    public void agregarLicencia(Licencia licencia) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(licencia);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
  public List<Licencia> obtenerLicenciasPorPersonas(List<Persona> personas) {
    EntityManager em = emf.createEntityManager();
    List<Licencia> licencias = new ArrayList<>();

    try {
        em.getTransaction().begin();
        TypedQuery<Licencia> query = em.createQuery("SELECT l FROM Licencia l WHERE l.persona IN :personas", Licencia.class);
        query.setParameter("personas", personas);
        licencias = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return licencias;
}
}
