package tech.edev404.restapi.model.mapper;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tech.edev404.restapi.model.dto.AprendizDTO;
import tech.edev404.restapi.model.entities.Aprendiz;

@Primary
@Service
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
        dto.setStatus(pojo.getStatus().toString());
        return dto;
    }
    
}
