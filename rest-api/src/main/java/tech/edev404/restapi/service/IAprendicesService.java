package tech.edev404.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tech.edev404.restapi.model.entities.Aprendiz;

public interface IAprendicesService {
    List<Aprendiz> findAll();
    Page<Aprendiz> findAllPaginate(Pageable pageable);
    Page<Aprendiz> findAllByPaginateAndExample(Pageable pageable, Example<Aprendiz> example);
    Optional<Aprendiz> findById(String idAprendiz);
    void delete(Aprendiz aprendiz);
    void save(Aprendiz aprendiz);
}
