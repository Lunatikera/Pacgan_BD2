/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.PagoEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.IPagoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Usuario
 */
public class PagoDAO implements IPagoDAO {

    private IConexionBD conexionBD;

   public PagoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
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

    @Override
    public List<PagoEntidad> listaPagosPaginado(int numeroPagina, int tamanoPagina) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> pagos = null;

        try {
            pagos = entityManager.createQuery("SELECT p FROM PagoEntidad p", PagoEntidad.class)
                    .setFirstResult((numeroPagina - 1) * tamanoPagina)
                    .setMaxResults(tamanoPagina)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los beneficiarios", e);
        } finally {
            entityManager.close();
        }

        return pagos;
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
}
