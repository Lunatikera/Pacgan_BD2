package entidades;

import entidades.CuentaBancariaEntidad;
import entidades.PagoEntidad;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-23T17:47:04", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(BeneficiarioEntidad.class)
public class BeneficiarioEntidad_ { 

    public static volatile SingularAttribute<BeneficiarioEntidad, String> apellidoPA;
    public static volatile SingularAttribute<BeneficiarioEntidad, Long> id_beneficiario;
    public static volatile ListAttribute<BeneficiarioEntidad, PagoEntidad> beneficiarioPago;
    public static volatile SingularAttribute<BeneficiarioEntidad, BigDecimal> saldo;
    public static volatile SingularAttribute<BeneficiarioEntidad, String> nombreUsuario;
    public static volatile ListAttribute<BeneficiarioEntidad, CuentaBancariaEntidad> beneficiarioCuenta;
    public static volatile SingularAttribute<BeneficiarioEntidad, String> apellidoMA;
    public static volatile SingularAttribute<BeneficiarioEntidad, String> claveContrato;
    public static volatile SingularAttribute<BeneficiarioEntidad, String> nombres;
    public static volatile SingularAttribute<BeneficiarioEntidad, String> contraseña;

}