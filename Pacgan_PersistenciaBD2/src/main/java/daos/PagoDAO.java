/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import entidades.AbonoEntidad;
import entidades.PagoEntidad;
import entidades.Pago_EstatusEntidad;
import excepciones.PersistenciaException;
import interfaces.IConexionBD;
import interfaces.IPagoDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 * Clase que implementa las operaciones de acceso a datos para la entidad PagoEntidad. Esta clase utiliza una conexión a la base de datos proporcionada por un objeto IConexionBD. Proporciona métodos para agregar, consultar, listar, editar y eliminar pagos.
 *
 * @autor Usuario
 */
public class PagoDAO implements IPagoDAO {

    private IConexionBD conexionBD;

    public PagoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Agrega un nuevo pago a la base de datos.
     *
     * @param pago El objeto PagoEntidad que se desea agregar.
     * @throws PersistenciaException Si ocurre un error durante la creación del pago.
     */
    @Override
    public void agregarPago(PagoEntidad pago) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.persist(pago);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al crear pago", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Consulta un pago por su ID.
     *
     * @param id El ID del pago que se desea consultar.
     * @return El objeto PagoEntidad correspondiente al ID especificado.
     * @throws PersistenciaException Si ocurre un error durante la consulta del pago.
     */
    @Override
    public PagoEntidad consultarPagoPorID(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        PagoEntidad pago = null;

        try {
            pago = entityManager.find(PagoEntidad.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer pago", e);
        } finally {
            entityManager.close();
        }

        return pago;
    }

    /**
     * Lista los pagos de manera paginada.
     *
     * @param numeroPagina El número de la página que se desea obtener.
     * @param tamanoPagina El tamaño de la página (número de resultados por página).
     * @return Una lista de objetos PagoEntidad correspondiente a la página solicitada.
     * @throws PersistenciaException Si ocurre un error durante la consulta de los pagos.
     */
    @Override
    public List<PagoEntidad> listaPagosPaginado(int limite, int numeroPagina) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> pagos = null;

        try {
            pagos = entityManager.createQuery("SELECT p FROM PagoEntidad p", PagoEntidad.class)
                    .setFirstResult((numeroPagina - 1) * limite)
                    .setMaxResults(limite)
                    .getResultList();
            System.out.println(pagos);

        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los beneficiarios", e);
        } finally {
            entityManager.close();
        }

        return pagos;
    }

    /**
     * Lista todos los pagos.
     *
     * @return Una lista de objetos PagoEntidad que representan todos los pagos.
     * @throws PersistenciaException Si ocurre un error durante la consulta de los pagos.
     */
    @Override
    public List<PagoEntidad> listaPagos() throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> pagos = null;

        try {
            pagos = entityManager.createQuery("SELECT p FROM PagoEntidad p", PagoEntidad.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al leer todos los pagos", e);
        } finally {
            entityManager.close();
        }

        return pagos;
    }

    /**
     * Actualiza un pago existente en la base de datos.
     *
     * @param pago El objeto PagoEntidad con la información actualizada.
     * @throws PersistenciaException Si ocurre un error durante la actualización del pago.
     */
    @Override
    public void editarPago(PagoEntidad pago) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            entityManager.merge(pago);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al actualizar pago", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Elimina un pago de la base de datos por su ID.
     *
     * @param id El ID del pago que se desea eliminar.
     * @throws PersistenciaException Si ocurre un error durante la eliminación del pago.
     */
    @Override
    public void eliminarPago(Long id) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();
            PagoEntidad pago = entityManager.find(PagoEntidad.class, id);
            if (pago != null) {
                entityManager.remove(pago);
            }
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar pago", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PagoEntidad> listaPagoPaginadoPorBeneficiario(int limite, int numeroPagina, Long beneficiarioId) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> abonos = null;
        try {
            // Crear una consulta TypedQuery utilizando JPQL para obtener abonos por pagoId
            TypedQuery<PagoEntidad> query = entityManager.createQuery(
                    "SELECT p FROM PagoEntidad p WHERE p.beneficiarioPago.id_beneficiario = :beneficiarioId ORDER BY p.monto DESC",
                    PagoEntidad.class
            );
            query.setParameter("beneficiarioId", beneficiarioId);
            query.setFirstResult((numeroPagina - 1) * limite); // Establecer el offset
            query.setMaxResults(limite); // Establecer el límite de resultados

            // Ejecutar la consulta y retornar los resultados
            abonos = query.getResultList();
        } catch (Exception e) {
            // Manejar excepciones de persistencia y encapsularlas en PersistenciaException
            throw new PersistenciaException("Error al obtener los abonos paginados.", e);
        } finally {
            entityManager.close();
        }
        return abonos;
    }

    @Override
    public List<PagoEntidad> listaPagoPaginadoAdmin(int limite, int numeroPagina, String estatusFiltro) throws PersistenciaException {
        EntityManager entityManager = conexionBD.obtenerEntityManager();
        List<PagoEntidad> pagos = null;
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<PagoEntidad> cq = cb.createQuery(PagoEntidad.class);
            Root<PagoEntidad> pagoRoot = cq.from(PagoEntidad.class);

            // Join para acceder a la relación pagosEstatus
            Join<PagoEntidad, Pago_EstatusEntidad> pagosEstatusJoin = pagoRoot.join("pagoEstatus");

            // Subquery para obtener el máximo idPagoEstatus agrupado por id_pago
            CriteriaQuery<Long> subquery = cb.createQuery(Long.class);
            Root<Pago_EstatusEntidad> subqueryRoot = subquery.from(Pago_EstatusEntidad.class);
            subquery.select(cb.max(subqueryRoot.get("id_pagoEstatus")))
                    .groupBy(subqueryRoot.get("pagoEstatus").get("id_pago"));

            Query subqueryQuery = entityManager.createQuery(subquery);
            List<Long> maxIdPagoEstatusList = subqueryQuery.getResultList();

            // Predicado principal para filtrar por estatus_id = 1 o 5
            Predicate predicate = cb.and(
                    pagosEstatusJoin.get("id_pagoEstatus").in(maxIdPagoEstatusList)
            );
            if (estatusFiltro.equalsIgnoreCase("MODIFICADO")) {
                predicate = cb.and(predicate, cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 5L));

            } else if (estatusFiltro.equalsIgnoreCase("CREADO")) {
                predicate = cb.and(predicate, cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 1L));

            } else if (estatusFiltro.equalsIgnoreCase("AUTORIZADO")) {
                predicate = cb.and(predicate, cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 2L));

            } else if (estatusFiltro.equalsIgnoreCase("PAGADO")) {
                predicate = cb.and(predicate, cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 4L));

            } else if (estatusFiltro.equalsIgnoreCase("AMBOSPAGAR")) {
                predicate = cb.and(predicate, cb.or(
                        cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 2L),
                        cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 4L)
                ));

            } else if (estatusFiltro.equalsIgnoreCase("AMBOSAUTORIZAR")) {
                predicate = cb.and(predicate, cb.or(
                        cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 1L),
                        cb.equal(pagosEstatusJoin.get("estatus").get("id_estatus"), 5L)
                ));
            }
            cq.select(pagoRoot)
                    .distinct(true)
                    .where(predicate)
                    .orderBy(cb.desc(pagoRoot.get("id_pago"))); // Orden opcional según tus necesidades

            Query query = entityManager.createQuery(cq);
            query.setFirstResult((numeroPagina - 1) * limite);
            query.setMaxResults(limite);

            pagos = query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la lista de pagos", e);

        } finally {
            entityManager.close();

        }
        return pagos;

    }

    @Override
    public List<PagoEntidad> filtrarPagos(
            List<Long> tipoPagoIds,
            List<Long> estatusIds,
            Boolean abonosTerminados,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin) {

        EntityManager entityManager = conexionBD.obtenerEntityManager();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PagoEntidad> query = cb.createQuery(PagoEntidad.class);
        Root<PagoEntidad> pagoRoot = query.from(PagoEntidad.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filter by tipo pago
        if (tipoPagoIds != null && !tipoPagoIds.isEmpty()) {
            predicates.add(pagoRoot.get("tipoPago").get("id_tipoPago").in(tipoPagoIds));
        }

        // Filter by estatus
        if (estatusIds != null && !estatusIds.isEmpty()) {
            predicates.add(pagoRoot.join("pagoEstatus").get("id_estatus").in(estatusIds));
        }

        // Filter by abonos terminados
        if (abonosTerminados != null) {
            Join<PagoEntidad, AbonoEntidad> abonoJoin = pagoRoot.join("abonos");
            predicates.add(cb.equal(
                    cb.count(abonoJoin),
                    pagoRoot.get("tipoPago").get("numeroParcialidades")
            ));

        }

        if (fechaInicio != null && fechaFin != null) {
            predicates.add(cb.between(pagoRoot.get("fechaPago"), fechaInicio, fechaFin));
        }

        query.where(predicates.toArray(new Predicate[0]));

        TypedQuery<PagoEntidad> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList();

    }
}
