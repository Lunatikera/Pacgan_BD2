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
import javax.persistence.NoResultException;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad
 * Beneficiario. Esta clase utiliza una conexión a la base de datos
 * proporcionada por un objeto IConexionBD.
 *
 * @author Usuario
 */
public class BeneficiarioDAO implements IBeneficiarioDAO {

    private IConexionBD conexionBD;

    public BeneficiarioDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    public BeneficiarioDAO() {
        this.conexionBD = new ConexionBD();

    }

    /**
     * Crea un nuevo registro de beneficiario en la base de datos.
     *
     * @param beneficiario Objeto BeneficiarioEntidad que representa el
     * beneficiario a crear.
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * persistencia.
     */
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

    /**
     * Consulta un beneficiario por su ID en la base de datos.
     *
     * @param id El ID del beneficiario a consultar.
     * @return El objeto BeneficiarioEntidad correspondiente al ID
     * proporcionado.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
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

    /**
     * Obtiene una lista de todos los beneficiarios almacenados en la base de
     * datos.
     *
     * @return Una lista de objetos BeneficiarioEntidad que representan todos
     * los beneficiarios.
     * @throws PersistenciaException Si ocurre un error durante la obtención de
     * la lista de beneficiarios.
     */
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

    /**
     * Obtiene una lista paginada de beneficiarios almacenados en la base de
     * datos.
     *
     * @param pagina El número de página a obtener.
     * @param limite El tamaño de la página (número de elementos por
     * página).
     * @return Una lista de objetos BeneficiarioEntidad que representan los
     * beneficiarios de la página especificada.
     * @throws PersistenciaException Si ocurre un error durante la obtención de
     * la lista de beneficiarios paginada.
     */
    @Override
    public List<BeneficiarioEntidad> listaBeneficiariosPaginado(int pagina, int limite) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<BeneficiarioEntidad> beneficiarios = null;

        try {
            beneficiarios = entityManager.createQuery("SELECT b FROM BeneficiarioEntidad b", BeneficiarioEntidad.class)
                    .setFirstResult((pagina - 1) * limite)
                    .setMaxResults(limite)
                    .getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los beneficiarios", e);
        } finally {
            entityManager.close();
        }

        return beneficiarios;
    }

    /**
     * Actualiza un beneficiario en la base de datos.
     *
     * @param beneficiario Objeto BeneficiarioEntidad que representa el
     * beneficiario a actualizar.
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * actualización.
     */
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

    /**
     * Elimina un beneficiario de la base de datos por su ID.
     *
     * @param id El ID del beneficiario a eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación
     * del beneficiario.
     */
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

    /**
     * Consulta un beneficiario por su nombre de usuario en la base de datos.
     *
     * @param nombreUsuario El nombre de usuario del beneficiario a consultar.
     * @return El objeto BeneficiarioEntidad correspondiente al nombre de
     * usuario proporcionado.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public BeneficiarioEntidad consultarBeneficiarioPorNombreUsuario(String nombreUsuario) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        BeneficiarioEntidad beneficiario = null;

        try {
            beneficiario = entityManager.createQuery("SELECT b FROM BeneficiarioEntidad b WHERE b.nombreUsuario = :nombreUsuario", BeneficiarioEntidad.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .getSingleResult();
        } catch (NoResultException e) {
            // No se encontró ningún beneficiario con el nombre de usuario especificado
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer beneficiario por nombre de usuario", e);
        } finally {
            entityManager.close();
        }

        return beneficiario;
    }
}
