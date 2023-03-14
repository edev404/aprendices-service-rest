package tech.edev404.restapi.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.edev404.restapi.model.entities.Aprendiz;

@Repository
public interface AprendicesRepository extends JpaRepository<Aprendiz, String>{
    
}
