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
 * Clase que implementa las operaciones de acceso a datos para la entidad TipoPagoEntidad.
 * Esta clase utiliza una conexión a la base de datos proporcionada por un objeto IConexionBD.
 * Proporciona métodos para agregar, consultar, listar, editar y eliminar tipos de pago.
 * @autor Usuario
 */
public class TipoPagoDAO implements ITipoPagoDAO {
    private IConexionBD conexionBD;

   public TipoPagoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }
 /**
     * Agrega un nuevo tipo de pago a la base de datos.
     * @param tipoPago El objeto TipoPagoEntidad que se desea agregar.
     * @throws PersistenciaException Si ocurre un error durante la creación del tipo de pago.
     */    @Override
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

  /**
     * Consulta un tipo de pago por su ID.
     * @param id El ID del tipo de pago que se desea consultar.
     * @return El objeto TipoPagoEntidad correspondiente al ID especificado.
     * @throws PersistenciaException Si ocurre un error durante la consulta del tipo de pago.
     */    @Override
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

/**
     * Lista todos los tipos de pago.
     * @return Una lista de objetos TipoPagoEntidad que representan todos los tipos de pago.
     * @throws PersistenciaException Si ocurre un error durante la consulta de los tipos de pago.
     */    @Override
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

 /**
     * Actualiza un tipo de pago existente en la base de datos.
     * @param tipoPago El objeto TipoPagoEntidad con la información actualizada.
     * @throws PersistenciaException Si ocurre un error durante la actualización del tipo de pago.
     */    @Override
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

    /**
     * Elimina un tipo de pago de la base de datos por su ID.
     * @param id El ID del tipo de pago que se desea eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación del tipo de pago.
     */    @Override
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

