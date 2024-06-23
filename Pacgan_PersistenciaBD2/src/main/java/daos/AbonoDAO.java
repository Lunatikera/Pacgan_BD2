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
 *
 * @author Usuario
 */
public class AbonoDAO implements IAbonoDAO {

    private IConexionBD conexionBD;

    public AbonoDAO() {
        this.conexionBD = new ConexionBD();
    }

    // Crear un nuevo abono
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

    // Leer un abono por ID
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

    // Leer todos los abonos
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

    // Eliminar un abono
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
