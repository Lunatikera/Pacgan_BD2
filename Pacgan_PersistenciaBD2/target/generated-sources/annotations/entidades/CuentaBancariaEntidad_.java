package entidades;

import entidades.BeneficiarioEntidad;
import entidades.PagoEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-24T22:12:48", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(CuentaBancariaEntidad.class)
public class CuentaBancariaEntidad_ { 

    public static volatile SingularAttribute<CuentaBancariaEntidad, String> nombreBanco;
    public static volatile ListAttribute<CuentaBancariaEntidad, PagoEntidad> cuentaBancariaPagos;
    public static volatile SingularAttribute<CuentaBancariaEntidad, Long> id_cuentaBancaria;
    public static volatile SingularAttribute<CuentaBancariaEntidad, String> numeroCuenta;
    public static volatile SingularAttribute<CuentaBancariaEntidad, BeneficiarioEntidad> beneficiarioCuenta;
    public static volatile SingularAttribute<CuentaBancariaEntidad, Boolean> estaEliminada;
    public static volatile SingularAttribute<CuentaBancariaEntidad, String> clabe;

}