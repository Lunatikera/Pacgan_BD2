/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.PagoEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.IPagoDAO;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.ArrayList;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Usuario
 */
public class PagoDAO implements IPagoDAO {

    private IConexionBD conexionBD;

    public PagoDAO() {
        this.conexionBD = new ConexionBD();
    }

    // Crear un nuevo pago
    @Override
    public void agregarPago(PagoEntidad pago) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(pago);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al crear pago", e);
        } finally {
            entityManager.close();
        }
    }

    // Leer un pago por ID
    @Override
    public PagoEntidad consultarPagoPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        PagoEntidad pago = null;

        try {
            pago = entityManager.find(PagoEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer pago", e);
        } finally {
            entityManager.close();
        }

        return pago;
    }

    // Leer todos los pagos
    @Override
    public List<PagoEntidad> listaPagos() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> pagos = null;

        try {
            pagos = entityManager.createQuery("SELECT p FROM PagoEntidad p", PagoEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los pagos", e);
        } finally {
            entityManager.close();
        }

        return pagos;
    }

    // Actualizar un pago
    @Override
    public void editarPago(PagoEntidad pago) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(pago);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al actualizar pago", e);
        } finally {
            entityManager.close();
        }
    }

    // Eliminar un pago
    @Override
    public void eliminarPago(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            PagoEntidad pago = entityManager.find(PagoEntidad.class, id);
            if (pago != null) {
                entityManager.remove(pago);
            }
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar pago", e);
        } finally {
            entityManager.close();
        }
    }

    @PersistenceContext
    private EntityManager em;

    public List<PagoEntidad> buscarPagosFiltrados(LocalDate fechaInicio, LocalDate fechaFin, 
                                                  Long tipoId, Boolean abonosTerminados, 
                                                  Long estatusId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PagoEntidad> cq = cb.createQuery(PagoEntidad.class);
        Root<PagoEntidad> pago = cq.from(PagoEntidad.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if (fechaInicio != null && fechaFin != null) {
            predicates.add(cb.between(pago.get("fechaHora").as(LocalDate.class), fechaInicio, fechaFin));
        }

        if (tipoId != null) {
            predicates.add(cb.equal(pago.get("tipoPago").get("id"), tipoId));
        }

        if (abonosTerminados != null) {
            // Implementa la lógica para abonos terminados
        }

        if (estatusId != null) {
            predicates.add(cb.equal(pago.get("pagoEstatus").get("estatus").get("id"), estatusId));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
