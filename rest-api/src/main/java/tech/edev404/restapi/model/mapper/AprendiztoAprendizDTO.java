package tech.edev404.restapi.model.mapper;

import tech.edev404.restapi.model.dto.AprendizDTO;
import tech.edev404.restapi.model.entities.Aprendiz;

public class AprendiztoAprendizDTO implements GenericMapper<Aprendiz, AprendizDTO>{

    @Override
    public AprendizDTO map(Aprendiz pojo) {
        AprendizDTO dto = new AprendizDTO();
        dto.setNumeroDocumento(pojo.getId());
        dto.setFicha(pojo.getFicha());
        dto.setPrograma(pojo.getPrograma());
        dto.setCelular(pojo.getCelular());
        dto.setCorreoElectronico(pojo.getCorreoElectronico());
        dto.setTipoDocumento(pojo.getTipoDocumento().toString());
        return dto;
    }
    
}
