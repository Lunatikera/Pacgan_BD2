/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.CuentaBancariaEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.ICuentaBancariaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad
 * CuentaBancaria. Esta clase utiliza una conexión a la base de datos
 * proporcionada por un objeto IConexionBD.
 *
 * @author Usuario
 */
public class CuentaBancariaDAO implements ICuentaBancariaDAO {

    private IConexionBD conexionBD;

    public CuentaBancariaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Crea un nuevo registro de cuenta bancaria en la base de datos.
     *
     * @param cuentaBancaria Objeto CuentaBancariaEntidad que representa la
     * cuenta bancaria a crear.
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * persistencia.
     */
    @Override
    public void agregarCuentaBancaria(CuentaBancariaEntidad cuentaBancaria) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(cuentaBancaria);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al crear cuenta bancaria", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Consulta una cuenta bancaria por su ID en la base de datos.
     *
     * @param id El ID de la cuenta bancaria a consultar.
     * @return El objeto CuentaBancariaEntidad correspondiente al ID
     * proporcionado.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public CuentaBancariaEntidad consultarCuentaBancariaPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        CuentaBancariaEntidad cuentaBancaria = null;

        try {
            cuentaBancaria = entityManager.find(CuentaBancariaEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer cuenta bancaria", e);
        } finally {
            entityManager.close();
        }

        return cuentaBancaria;
    }

    /**
     * Obtiene una lista de todas las cuentas bancarias almacenadas en la base
     * de datos.
     *
     * @return Una lista de objetos CuentaBancariaEntidad que representan todas
     * las cuentas bancarias.
     * @throws PersistenciaException Si ocurre un error durante la obtención de
     * la lista de cuentas bancarias.
     */
    @Override
    public List<CuentaBancariaEntidad> listaCuentasBancarias() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<CuentaBancariaEntidad> cuentasBancarias = null;

        try {
            cuentasBancarias = entityManager.createQuery("SELECT c FROM CuentaBancariaEntidad c", CuentaBancariaEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todas las cuentas bancarias", e);
        } finally {
            entityManager.close();
        }

        return cuentasBancarias;
    }

    /**
     * Actualiza una cuenta bancaria en la base de datos.
     *
     * @param cuentaBancaria Objeto CuentaBancariaEntidad que representa la
     * cuenta bancaria a actualizar.
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * actualización.
     */
    @Override
    public void editarCuentaBancaria(CuentaBancariaEntidad cuentaBancaria) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(cuentaBancaria);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al actualizar cuenta bancaria", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Elimina una cuenta bancaria de la base de datos por su ID.
     *
     * @param id El ID de la cuenta bancaria a eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación
     * de la cuenta bancaria.
     */
    @Override
    public void eliminarCuentaBancaria(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            CuentaBancariaEntidad cuentaBancaria = entityManager.find(CuentaBancariaEntidad.class, id);
            if (cuentaBancaria != null) {
                entityManager.remove(cuentaBancaria);
            }
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar cuenta bancaria", e);
        } finally {
            entityManager.close();
        }
    }
}
