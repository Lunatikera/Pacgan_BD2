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
 *
 * @author Usuario
 */
public class EstatusDAO implements IEstatusDAO {

    private IConexionBD conexionBD;

     public EstatusDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }


    // Leer un estatus por ID
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

    // Leer todos los estatus
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

}
