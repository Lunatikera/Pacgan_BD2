package entidades;

import entidades.PagoEntidad;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-27T07:11:36", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(TipoPagoEntidad.class)
public class TipoPagoEntidad_ { 

    public static volatile SingularAttribute<TipoPagoEntidad, String> nombreTipo;
    public static volatile SingularAttribute<TipoPagoEntidad, Integer> numeroParcialidades;
    public static volatile SingularAttribute<TipoPagoEntidad, Long> id_tipoPagos;
    public static volatile ListAttribute<TipoPagoEntidad, PagoEntidad> tipoPago;

}