package tech.edev404.restapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.edev404.restapi.model.entities.Aprendiz;
import tech.edev404.restapi.repository.db.AprendicesRepository;
import tech.edev404.restapi.service.IAprendicesService;

@Service
@Primary
public class AprendicesService implements IAprendicesService{

    private AprendicesRepository aprendicesRepository;

    @Autowired
    public AprendicesService(AprendicesRepository aprendicesRepository){
        this.aprendicesRepository = aprendicesRepository;
    }

    @Override
    public Page<Aprendiz> findAllPaginate(Pageable pageable) {
        return aprendicesRepository.findAll(pageable);
    }

    @Override
    public List<Aprendiz> findAll() {
        return aprendicesRepository.findAll();
    }

    @Override
    public Page<Aprendiz> findAllByPaginateAndExample(Pageable pageable, Example<Aprendiz> example) {
        return aprendicesRepository.findAll(example, pageable);
    }

    @Override
    public Optional<Aprendiz> findById(String idAprendiz) {
        return aprendicesRepository.findById(idAprendiz);
    }

    @Override
    public void delete(Aprendiz aprendiz) {
        aprendicesRepository.delete(aprendiz);
    }

    @Override
    public void save(Aprendiz aprendiz) {
        aprendicesRepository.save(aprendiz);
    }
    
}
