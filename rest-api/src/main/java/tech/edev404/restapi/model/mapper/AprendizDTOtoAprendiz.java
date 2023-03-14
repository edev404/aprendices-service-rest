package tech.edev404.restapi.model.mapper;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import tech.edev404.restapi.model.dto.AprendizDTO;
import tech.edev404.restapi.model.entities.Aprendiz;

@Service
@Primary
public class AprendizDTOtoAprendiz implements GenericMapper<AprendizDTO, Aprendiz>{

    @Override
    public Aprendiz map(AprendizDTO t) {
        return null;
    }
    
}
