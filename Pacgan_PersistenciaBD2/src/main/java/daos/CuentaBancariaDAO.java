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

    
    // Crear una nueva cuenta bancaria
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

    // Leer una cuenta bancaria por ID
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

    // Leer todas las cuentas bancarias
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

    // Actualizar una cuenta bancaria
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

    // Eliminar una cuenta bancaria
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

