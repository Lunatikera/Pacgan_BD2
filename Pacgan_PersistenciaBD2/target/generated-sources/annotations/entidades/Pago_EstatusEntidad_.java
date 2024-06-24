package entidades;

import entidades.EstatusEntidad;
import entidades.PagoEntidad;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-24T03:29:55", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Pago_EstatusEntidad.class)
public class Pago_EstatusEntidad_ { 

    public static volatile SingularAttribute<Pago_EstatusEntidad, Long> id_pagoEstatus;
    public static volatile SingularAttribute<Pago_EstatusEntidad, EstatusEntidad> estatus;
    public static volatile SingularAttribute<Pago_EstatusEntidad, LocalDateTime> fechaHora;
    public static volatile SingularAttribute<Pago_EstatusEntidad, PagoEntidad> pagoEstatus;
    public static volatile SingularAttribute<Pago_EstatusEntidad, String> mensaje;

}