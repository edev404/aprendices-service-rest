package tech.edev404.restapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AprendizDTO {
    private String tipoDocumento;
    private String numeroDocumento;
    private String ficha;
    private String programa;
    private String correoElectronico;
    private String celular;
    private String status;
}
