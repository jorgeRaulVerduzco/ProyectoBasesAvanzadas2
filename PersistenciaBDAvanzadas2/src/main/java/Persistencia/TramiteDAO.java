/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Dominio.Licencia;
import Dominio.Persona;
import Dominio.Placa;
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
    public List<Object[]> buscarTramites(String tipoTramite, Calendar fechaInicio, Calendar fechaFin, String nombre, String apellidoPaterno, String apellidoMaterno) {
        EntityManager em = emf.createEntityManager();
        List<Object[]> tramites = null;

        try {
            em.getTransaction().begin();
            String jpql = "SELECT t.fechaTramite, "
                    + "CASE TYPE(t) "
                    + "WHEN Placa THEN 'Placas' "
                    + "WHEN Licencia THEN 'Licencia' "
                    + "ELSE 'Otro' END, "
                    + "p.nombres, p.apellidoPaterno, p.apellidoMaterno, t.costo "
                    + "FROM Tramite t JOIN t.persona p "
                    + "WHERE (:tipo IS NULL OR TYPE(t) = :tipo) "
                    + "AND (:fechaInicio IS NULL OR :fechaFin IS NULL OR t.fechaTramite BETWEEN :fechaInicio AND :fechaFin) "
                    + "AND (:nombre IS NULL OR p.nombres LIKE :nombre) "
                    + "AND (:apellidoPaterno IS NULL OR p.apellidoPaterno LIKE :apellidoPaterno) "
                    + "AND (:apellidoMaterno IS NULL OR p.apellidoMaterno LIKE :apellidoMaterno)";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("tipo", tipoTramite.equals("Placas") ? Placa.class
                    : tipoTramite.equals("Licencia") ? Licencia.class : null);
            query.setParameter("fechaInicio", fechaInicio, TemporalType.DATE);
            query.setParameter("fechaFin", fechaFin, TemporalType.DATE);
            query.setParameter("nombre", "%" + nombre + "%");
            query.setParameter("apellidoPaterno", "%" + apellidoPaterno + "%");
            query.setParameter("apellidoMaterno", "%" + apellidoMaterno + "%");
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
