/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Persona;
import Dominio.Tramite;
import IPersistencia.ITramiteDAO;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author INEGI
 */
public class TramiteDAO implements ITramiteDAO {

    private EntityManagerFactory emf;

    public TramiteDAO() {
        emf = Persistence.createEntityManagerFactory("ConexionPU");
    }
    @Override
    public List<Object[]> buscarTramitesPorTipo(String tipoTramite) {
    EntityManager em = emf.createEntityManager();
    List<Object[]> tramites = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT t.fechaTramite, t.tipoTramite, p.nombres, t.costo " +
                      "FROM Tramite t JOIN t.persona p " +
                      "WHERE t.tipoTramite = :tipo";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("tipo", tipoTramite);
        tramites = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return tramites;
}

    @Override
    public List<Object[]> buscarTramitesPorPeriodo(Calendar fechaInicio, Calendar fechaFin) {
    EntityManager em = emf.createEntityManager();
    List<Object[]> tramites = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT t.fechaTramite, t.tipoTramite, p.nombres, t.costo " +
                      "FROM Tramite t JOIN t.persona p " +
                      "WHERE t.fechaTramite BETWEEN :fechaInicio AND :fechaFin";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
        query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
        tramites = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return tramites;
}

    @Override
    public List<Object[]> buscarTramitesPorNombresSimilares(String nombre) {
    EntityManager em = emf.createEntityManager();
    List<Object[]> tramites = null;

    try {
        em.getTransaction().begin();
        String jpql = "SELECT t.fechaTramite, t.tipoTramite, p.nombres, t.costo " +
                      "FROM Tramite t JOIN t.persona p " +
                      "WHERE p.nombres LIKE :nombre";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("nombre", "%" + nombre + "%");
        tramites = query.getResultList();
        em.getTransaction().commit();
    } catch (Exception e) {
        em.getTransaction().rollback();
        e.printStackTrace();
    } finally {
        em.close();
    }

    return tramites;
}
}
