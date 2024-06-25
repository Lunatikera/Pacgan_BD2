/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.AbonoEntidad;
import excepciones.PersistenciaException;
import interfaces.IAbonoDAO;
import interfaces.IConexionBD;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad Abono.
 * Esta clase utiliza una conexión a la base de datos proporcionada por un
 * objeto IConexionBD.
 *
 * @author Usuario
 */
public class AbonoDAO implements IAbonoDAO {

    private IConexionBD conexionBD;

    /**
     * Constructor que recibe una instancia de IConexionBD para la conexión a la
     * base de datos.
     *
     * @param conexionBD Objeto que implementa la interfaz IConexionBD para la
     * conexión a la base de datos.
     */
    public AbonoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Crea un nuevo registro de abono en la base de datos.
     *
     * @param abono Objeto AbonoEntidad que representa el abono a crear.
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * persistencia.
     */
    @Override
    public void agregarAbono(AbonoEntidad abono) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(abono);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al crear abono", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Consulta un abono por su ID en la base de datos.
     *
     * @param id El ID del abono a consultar.
     * @return El objeto AbonoEntidad correspondiente al ID proporcionado.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public AbonoEntidad consultarAbonoPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        AbonoEntidad abono = null;

        try {
            abono = entityManager.find(AbonoEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer abono", e);
        } finally {
            entityManager.close();
        }

        return abono;
    }

    /**
     * Obtiene una lista de todos los abonos almacenados en la base de datos.
     *
     * @return Una lista de objetos AbonoEntidad que representan todos los
     * abonos.
     * @throws PersistenciaException Si ocurre un error durante la obtención de
     * la lista de abonos.
     */
    @Override
    public List<AbonoEntidad> listaAbonos() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<AbonoEntidad> abonos = null;

        try {
            abonos = entityManager.createQuery("SELECT a FROM AbonoEntidad a", AbonoEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los abonos", e);
        } finally {
            entityManager.close();
        }

        return abonos;
    }

    /**
     * Elimina un abono de la base de datos por su ID.
     *
     * @param id El ID del abono a eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación
     * del abono.
     */
    @Override
    public void eliminarAbono(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            AbonoEntidad abono = entityManager.find(AbonoEntidad.class, id);
            if (abono != null) {
                entityManager.remove(abono);
            }
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar abono", e);
        } finally {
            entityManager.close();
        }
    }
}
