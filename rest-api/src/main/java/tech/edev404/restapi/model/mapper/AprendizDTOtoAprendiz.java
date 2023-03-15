package tech.edev404.restapi.model.mapper;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tech.edev404.restapi.model.dto.AprendizDTO;
import tech.edev404.restapi.model.entities.Aprendiz;
import tech.edev404.restapi.model.entities.enums.TipoDocumento;

@Service
@Primary
public class AprendizDTOtoAprendiz implements GenericMapper<AprendizDTO, Aprendiz>{

    @Override
    public Aprendiz map(AprendizDTO dto) {
        Aprendiz pojo = new Aprendiz();
        pojo.setId(dto.getNumeroDocumento());
        pojo.setFicha(dto.getFicha());
        pojo.setPrograma(dto.getPrograma());
        pojo.setCorreoElectronico(dto.getCorreoElectronico());
        pojo.setTipoDocumento(TipoDocumento.valueOf(dto.getTipoDocumento()));
        return pojo;
    }
    
}
