package tech.edev404.restapi.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tech.edev404.restapi.model.entities.enums.TipoDocumento;
import tech.edev404.restapi.model.entities.status.AprendizStatus;

@Data
@Builder
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="aprendices",  indexes = @Index(name="aprendiz_unique", columnList = "id, ficha", unique = true))
public class Aprendiz {
    
    @Id
    private String id;
    private String ficha;
    private String programa;
    private String correoElectronico;
    private String celular;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @Enumerated(EnumType.STRING)
    private AprendizStatus status;
}
