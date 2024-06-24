/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.EstatusEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.IEstatusDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad
 * Estatus. Esta clase utiliza una conexión a la base de datos proporcionada por
 * un objeto IConexionBD.
 *
 * @author Usuario
 */
public class EstatusDAO implements IEstatusDAO {

    private IConexionBD conexionBD;

    public EstatusDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Consulta un estatus por su ID en la base de datos.
     *
     * @param id El ID del estatus a consultar.
     * @return El objeto EstatusEntidad correspondiente al ID proporcionado.
     * @throws PersistenciaException Si ocurre un error durante la consulta.
     */
    @Override
    public EstatusEntidad consultarEstatusPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EstatusEntidad estatus = null;

        try {
            estatus = entityManager.find(EstatusEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer estatus", e);
        } finally {
            entityManager.close();
        }

        return estatus;
    }

    /**
     * Obtiene una lista de todos los estatus almacenados en la base de datos.
     *
     * @return Una lista de objetos EstatusEntidad que representan todos los
     * estatus.
     * @throws PersistenciaException Si ocurre un error durante la obtención de
     * la lista de estatus.
     */
    @Override
    public List<EstatusEntidad> listaEstatus() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<EstatusEntidad> estatus = null;

        try {
            estatus = entityManager.createQuery("SELECT e FROM EstatusEntidad e", EstatusEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los estatus", e);
        } finally {
            entityManager.close();
        }

        return estatus;
    }

    /**
     * Agrega un nuevo estatus a la base de datos.
     *
     * @param estatus Objeto EstatusEntidad que representa el estatus a agregar.
     * @throws PersistenciaException Si ocurre un error durante la operación de
     * agregado.
     */
    @Override
    public void agregarEstatus(EstatusEntidad estatus) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(estatus);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al agregar estatus", e);
        } finally {
            entityManager.close();
        }
    }

}
