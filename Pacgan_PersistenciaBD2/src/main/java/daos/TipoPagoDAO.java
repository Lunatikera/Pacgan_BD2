/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.TipoPagoEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.ITipoPagoDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Usuario
 */
public class TipoPagoDAO implements ITipoPagoDAO {
    private IConexionBD conexionBD;

    public TipoPagoDAO() {
        this.conexionBD = new ConexionBD();
    }

    // Crear un nuevo tipo de pago
    @Override
    public void agregarTipoPago(TipoPagoEntidad tipoPago) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(tipoPago);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al crear tipo de pago", e);
        } finally {
            entityManager.close();
        }
    }

    // Leer un tipo de pago por ID
    @Override
    public TipoPagoEntidad consultarTipoPagoPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        TipoPagoEntidad tipoPago = null;

        try {
            tipoPago = entityManager.find(TipoPagoEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer tipo de pago", e);
        } finally {
            entityManager.close();
        }

        return tipoPago;
    }

    // Leer todos los tipos de pago
    @Override
    public List<TipoPagoEntidad> listaTiposPago() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<TipoPagoEntidad> tiposPago = null;

        try {
            tiposPago = entityManager.createQuery("SELECT t FROM TipoPagoEntidad t", TipoPagoEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los tipos de pago", e);
        } finally {
            entityManager.close();
        }

        return tiposPago;
    }

    // Actualizar un tipo de pago
    @Override
    public void editarTipoPago(TipoPagoEntidad tipoPago) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(tipoPago);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al actualizar tipo de pago", e);
        } finally {
            entityManager.close();
        }
    }

    // Eliminar un tipo de pago
    @Override
    public void eliminarTipoPago(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            TipoPagoEntidad tipoPago = entityManager.find(TipoPagoEntidad.class, id);
            if (tipoPago!= null) {
                entityManager.remove(tipoPago);
            }
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar tipo de pago", e);
        } finally {
            entityManager.close();
        }
    }
}

