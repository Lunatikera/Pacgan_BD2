package entidades;

import entidades.PagoEntidad;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-06-20T19:21:16", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(AbonoEntidad.class)
public class AbonoEntidad_ { 

    public static volatile SingularAttribute<AbonoEntidad, Double> monto;
    public static volatile SingularAttribute<AbonoEntidad, LocalDateTime> fechaHora;
    public static volatile SingularAttribute<AbonoEntidad, PagoEntidad> pagoAbono;
    public static volatile SingularAttribute<AbonoEntidad, Long> id_abono;

}