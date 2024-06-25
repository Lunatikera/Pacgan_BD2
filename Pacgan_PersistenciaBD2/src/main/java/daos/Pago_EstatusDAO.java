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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author triny
 */
public class Pago_EstatusDAO implements IPago_EstatusDAO {

    private IConexionBD conexionBD;

    public Pago_EstatusDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

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
}
