package entidades;

import entidades.AbonoEntidad;
import entidades.BeneficiarioEntidad;
import entidades.CuentaBancariaEntidad;
import entidades.Pago_EstatusEntidad;
import entidades.TipoPagoEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-24T00:13:28", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(PagoEntidad.class)
public class PagoEntidad_ { 

    public static volatile SingularAttribute<PagoEntidad, CuentaBancariaEntidad> cuentaBancaria;
    public static volatile SingularAttribute<PagoEntidad, Double> monto;
    public static volatile ListAttribute<PagoEntidad, Pago_EstatusEntidad> pagoEstatus;
    public static volatile SingularAttribute<PagoEntidad, byte[]> comprobante;
    public static volatile SingularAttribute<PagoEntidad, BeneficiarioEntidad> beneficiarioPago;
    public static volatile SingularAttribute<PagoEntidad, TipoPagoEntidad> tipoPago;
    public static volatile ListAttribute<PagoEntidad, AbonoEntidad> pagoAbonos;
    public static volatile SingularAttribute<PagoEntidad, Long> id_pago;

}