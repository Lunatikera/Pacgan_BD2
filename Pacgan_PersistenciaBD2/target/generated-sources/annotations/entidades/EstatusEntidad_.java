package entidades;

import entidades.Pago_EstatusEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-24T00:13:28", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(EstatusEntidad.class)
public class EstatusEntidad_ { 

    public static volatile ListAttribute<EstatusEntidad, Pago_EstatusEntidad> estatus;
    public static volatile SingularAttribute<EstatusEntidad, String> nombre;
    public static volatile SingularAttribute<EstatusEntidad, Long> id_estatus;

}