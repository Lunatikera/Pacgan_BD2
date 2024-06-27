/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.Pago_EstatusEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.IPago_EstatusDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad Pago_Estatus. Esta clase utiliza una conexión a la base de datos proporcionada por un objeto IConexionBD.
 *
 * @author triny
 */
public class Pago_EstatusDAO implements IPago_EstatusDAO {

    private IConexionBD conexionBD;

    public Pago_EstatusDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Obtiene el estado más reciente del pago especificado por su ID.
     *
     * @param pagoId El ID del pago del cual se desea obtener el estado más reciente.
     * @return El objeto Pago_EstatusEntidad que representa el estado más reciente del pago. Si no se encuentra ningún estado para el pago especificado, retorna null.
     * @throws PersistenciaException Si ocurre un error durante la obtención del estado del pago.
     */
    @Override
    public Pago_EstatusEntidad obtenerEstadoDelPago(Long pagoId) throws PersistenciaException {
        EntityManager entityManager = null;

        try {
            entityManager = conexionBD.obtenerEntityManager();

            // Consulta para obtener el último estado del pago por fechaHora descendente
            String jpql = "SELECT pe FROM Pago_EstatusEntidad pe "
                    + "WHERE pe.pagoEstatus.id_pago= :pagoId "
                    + "ORDER BY pe.fechaHora DESC";

            TypedQuery<Pago_EstatusEntidad> query = entityManager.createQuery(jpql, Pago_EstatusEntidad.class);
            query.setParameter("pagoId", pagoId);
            query.setMaxResults(1); // Limitamos a un resultado para obtener el más reciente

            return query.getSingleResult();
        } catch (NoResultException ex) {
            // En caso de que no haya resultados (pagoId no encontrado)
            return null;
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener el estado más reciente del pago", ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Obtiene el historial de estados del pago especificado por su ID.
     *
     * @param pagoId El ID del pago del cual se desea obtener el historial de estados.
     * @return Una lista de objetos Pago_EstatusEntidad que representan el historial de estados del pago.
     * @throws PersistenciaException Si ocurre un error durante la obtención del historial de estados del pago.
     */
    @Override
    public List<Pago_EstatusEntidad> obtenerHistorialDeEstados(Long pagoId) throws PersistenciaException {
        // Aquí implementarías la lógica para obtener el historial de estados del pago
        EntityManager entityManager = null;
        try {
            entityManager = conexionBD.obtenerEntityManager();

            String jpql = "SELECT pe FROM Pago_EstatusEntidad pe WHERE pe.pagoEstatus.id = :pagoId";
            return entityManager.createQuery(jpql, Pago_EstatusEntidad.class)
                    .setParameter("pagoId", pagoId)
                    .getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Error al obtener el historial de estados del pago", ex);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void agregarPagoEstatus(Pago_EstatusEntidad pagoEstatus) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println(pagoEstatus);
        try {
            entityTransaction.begin();
            entityManager.persist(pagoEstatus);
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
}
