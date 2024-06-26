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
import javax.persistence.TypedQuery;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad CuentaBancaria. Esta clase utiliza una conexión a la base de datos proporcionada por un objeto IConexionBD.
 *
 * @author Usuario
 */
public class CuentaBancariaDAO implements ICuentaBancariaDAO {

    private IConexionBD conexionBD;

    public CuentaBancariaDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public CuentaBancariaDAO() {
        this.conexionBD = new ConexionBD();
    }

    /**
     * Crea un nuevo registro de cuenta bancaria en la base de datos.
     *
     * @param cuentaBancaria Objeto CuentaBancariaEntidad que representa la cuenta bancaria a crear.
     * @throws PersistenciaException Si ocurre un error durante la operación de persistencia.
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
     * @return El objeto CuentaBancariaEntidad correspondiente al ID proporcionado.
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
     * Obtiene una lista de todas las cuentas bancarias almacenadas en la base de datos.
     *
     * @return Una lista de objetos CuentaBancariaEntidad que representan todas las cuentas bancarias.
     * @throws PersistenciaException Si ocurre un error durante la obtención de la lista de cuentas bancarias.
     */
    @Override
    public List<CuentaBancariaEntidad> listaCuentasBancarias() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<CuentaBancariaEntidad> cuentasBancarias = null;

        try {
            cuentasBancarias = entityManager.createQuery("SELECT c FROM CuentaBancariaEntidad c WHERE c.estaEliminada=false", CuentaBancariaEntidad.class).getResultList();
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
     * @param cuentaBancaria Objeto CuentaBancariaEntidad que representa la cuenta bancaria a actualizar.
     * @throws PersistenciaException Si ocurre un error durante la operación de actualización.
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
     * @throws PersistenciaException Si ocurre un error durante la eliminación de la cuenta bancaria.
     */
    @Override
    public void eliminarCuentaBancaria(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            CuentaBancariaEntidad cuentaBancaria = entityManager.find(CuentaBancariaEntidad.class, id);
            if (cuentaBancaria != null) {
                cuentaBancaria.setEstaEliminada(true); // Marcamos como eliminada
                entityManager.merge(cuentaBancaria); // Actualizamos la entidad en la base de datos
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

    @Override
    public List<CuentaBancariaEntidad> listaCuentasPorBeneficiario(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<CuentaBancariaEntidad> cuentasBancarias = null;

        try {
            String jpql = "SELECT c FROM CuentaBancariaEntidad c "
                    + "WHERE c.beneficiarioCuenta.id_beneficiario = :id and c.estaEliminada=false ";

            TypedQuery<CuentaBancariaEntidad> query = entityManager.createQuery(jpql, CuentaBancariaEntidad.class);
            query.setParameter("id", id);

            cuentasBancarias = query.getResultList(); // Asignar el resultado de la consulta a la lista

        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todas las cuentas bancarias", e);
        } finally {
            entityManager.close();
        }
        return cuentasBancarias;
    }

    @Override
    public List<CuentaBancariaEntidad> listaPaginadoCuentasPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<CuentaBancariaEntidad> cuentas = null;
        try {

            TypedQuery<CuentaBancariaEntidad> query = entityManager.createQuery(
                    "SELECT c FROM CuentaBancariaEntidad c WHERE c.beneficiarioCuenta.id_beneficiario = :beneficiarioId and c.estaEliminada=false",
                    CuentaBancariaEntidad.class
            );
            query.setParameter("beneficiarioId", beneficiarioId);
            query.setFirstResult((numeroPagina - 1) * limite); // Establecer el offset
            query.setMaxResults(limite); // Establecer el límite de resultados

            // Ejecutar la consulta y retornar los resultados
            cuentas = query.getResultList();
        } catch (Exception e) {
            // Manejar excepciones de persistencia y encapsularlas en PersistenciaException
            throw new PersistenciaException("Error al obtener las cuentas paginados.", e);
        } finally {
            entityManager.close();
        }
        return cuentas;
    }
}
