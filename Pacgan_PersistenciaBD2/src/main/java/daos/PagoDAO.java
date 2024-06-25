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
 * Clase que implementa las operaciones de acceso a datos para la entidad
 * PagoEntidad. Esta clase utiliza una conexión a la base de datos proporcionada
 * por un objeto IConexionBD. Proporciona métodos para agregar, consultar,
 * listar, editar y eliminar pagos.
 *
 * @autor Usuario
 */
public class PagoDAO implements IPagoDAO {

    private IConexionBD conexionBD;

    public PagoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Agrega un nuevo pago a la base de datos.
     *
     * @param pago El objeto PagoEntidad que se desea agregar.
     * @throws PersistenciaException Si ocurre un error durante la creación del
     * pago.
     */
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

    /**
     * Consulta un pago por su ID.
     *
     * @param id El ID del pago que se desea consultar.
     * @return El objeto PagoEntidad correspondiente al ID especificado.
     * @throws PersistenciaException Si ocurre un error durante la consulta del
     * pago.
     */
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

    /**
     * Lista los pagos de manera paginada.
     *
     * @param numeroPagina El número de la página que se desea obtener.
     * @param tamanoPagina El tamaño de la página (número de resultados por
     * página).
     * @return Una lista de objetos PagoEntidad correspondiente a la página
     * solicitada.
     * @throws PersistenciaException Si ocurre un error durante la consulta de
     * los pagos.
     */
    @Override
    public List<PagoEntidad> listaPagosPaginado(int limite, int numeroPagina) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> pagos = null;

        try {
            pagos = entityManager.createQuery("SELECT p FROM PagoEntidad p", PagoEntidad.class)
                    .setFirstResult((numeroPagina - 1) * limite)
                    .setMaxResults(limite)
                    .getResultList();
            System.out.println(pagos);

        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los beneficiarios", e);
        } finally {
            entityManager.close();
        }

        return pagos;
    }

    /**
     * Lista todos los pagos.
     *
     * @return Una lista de objetos PagoEntidad que representan todos los pagos.
     * @throws PersistenciaException Si ocurre un error durante la consulta de
     * los pagos.
     */
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

    /**
     * Actualiza un pago existente en la base de datos.
     *
     * @param pago El objeto PagoEntidad con la información actualizada.
     * @throws PersistenciaException Si ocurre un error durante la actualización
     * del pago.
     */
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

    /**
     * Elimina un pago de la base de datos por su ID.
     *
     * @param id El ID del pago que se desea eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación
     * del pago.
     */
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
