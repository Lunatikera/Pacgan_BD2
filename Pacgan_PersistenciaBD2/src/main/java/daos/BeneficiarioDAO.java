/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.BeneficiarioEntidad;
import excepciones.PersistenciaException;
import interfaces.IBeneficiarioDAO;
import interfaces.IConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Usuario
 */
public class BeneficiarioDAO implements IBeneficiarioDAO {

    private IConexionBD conexionBD;

    public BeneficiarioDAO() {
        this.conexionBD = new ConexionBD();
    }

    // Crear un nuevo beneficiario
    @Override
    public void agregarBeneficiario(BeneficiarioEntidad beneficiario) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(beneficiario);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al crear beneficiario", e);
        } finally {
            entityManager.close();
        }
    }

    // Leer un beneficiario por ID
    @Override
    public BeneficiarioEntidad consultarBeneficiarioPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        BeneficiarioEntidad beneficiario = null;

        try {
            beneficiario = entityManager.find(BeneficiarioEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer beneficiario", e);
        } finally {
            entityManager.close();
        }

        return beneficiario;
    }

    // Leer todos los beneficiarios
    @Override
    public List<BeneficiarioEntidad> listaBeneficiarios() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<BeneficiarioEntidad> beneficiarios = null;

        try {
            beneficiarios = entityManager.createQuery("SELECT b FROM BeneficiarioEntidad b", BeneficiarioEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los beneficiarios", e);
        } finally {
            entityManager.close();
        }

        return beneficiarios;
    }

    @Override
    public List<BeneficiarioEntidad> listaBeneficiariosPaginado(int numeroPagina, int tamanoPagina) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<BeneficiarioEntidad> beneficiarios = null;

        try {
            beneficiarios = entityManager.createQuery("SELECT b FROM BeneficiarioEntidad b", BeneficiarioEntidad.class)
                    .setFirstResult((numeroPagina - 1) * tamanoPagina)
                    .setMaxResults(tamanoPagina)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los beneficiarios", e);
        } finally {
            entityManager.close();
        }

        return beneficiarios;
    }

    // Actualizar un beneficiario
    @Override
    public void editarBeneficiario(BeneficiarioEntidad beneficiario) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(beneficiario);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al actualizar beneficiario", e);
        } finally {
            entityManager.close();
        }
    }

    // Eliminar un beneficiario
    @Override
    public void eliminarBeneficiario(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            BeneficiarioEntidad beneficiario = entityManager.find(BeneficiarioEntidad.class, id);
            if (beneficiario != null) {
                entityManager.remove(beneficiario);
            }
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar beneficiario", e);
        } finally {
            entityManager.close();
        }
    }
}
