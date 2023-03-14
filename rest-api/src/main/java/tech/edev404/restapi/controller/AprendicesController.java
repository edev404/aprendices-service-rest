package tech.edev404.restapi.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.edev404.restapi.model.dto.AprendizDTO;
import tech.edev404.restapi.model.entities.Aprendiz;
import tech.edev404.restapi.model.entities.enums.TipoDocumento;
import tech.edev404.restapi.model.entities.status.AprendizStatus;
import tech.edev404.restapi.model.mapper.GenericMapper;
import tech.edev404.restapi.service.IAprendicesService;


@RestController
@RequestMapping("api/v1/aprendices/")
public class AprendicesController {

    private IAprendicesService aprendicesService;
    private GenericMapper<AprendizDTO, Aprendiz> aprendizDTOtoAprendiz;
    private GenericMapper<Aprendiz, AprendizDTO> aprendizToAprendizDTO;

    @Autowired
    AprendicesController(IAprendicesService aprendicesService,
                        GenericMapper<AprendizDTO, Aprendiz> aprendizDTOtoAprendiz,
                        GenericMapper<Aprendiz, AprendizDTO> aprendizToAprendizDTO){
        
        this.aprendizDTOtoAprendiz = aprendizDTOtoAprendiz;
        this.aprendizToAprendizDTO = aprendizToAprendizDTO;
        this.aprendicesService = aprendicesService;

    }

    @GetMapping
    public ResponseEntity<List<AprendizDTO>> findAll(){
        List<AprendizDTO> aprendicesDTO = aprendicesService.findAll()
                                                            .stream()
                                                            .map(aprendizToAprendizDTO::map)
                                                            .toList();
        return ResponseEntity.ok().body(aprendicesDTO);
    }
    
    @GetMapping("paginate")
    public ResponseEntity<Page<AprendizDTO>> findAllPaginate(
                                    @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "7") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Aprendiz> aprendices = aprendicesService.findAllPaginate(pageable);
        List<AprendizDTO> aprendicesDTO = aprendices.stream().map(aprendizToAprendizDTO::map).toList();
        return ResponseEntity.ok().body(new PageImpl<>(aprendicesDTO));
    }

    @GetMapping("search")
    public ResponseEntity<Page<AprendizDTO>> findBySearch(
                                    @RequestBody Aprendiz aprendiz,
                                    @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "7") Integer size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Example<Aprendiz> example = Example.of(aprendiz);
        Page<Aprendiz> aprendices = aprendicesService.findAllByPaginateAndExample(pageable, example);
        if (aprendices.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<AprendizDTO> aprendicesDTO = aprendices.stream().map(aprendizToAprendizDTO::map).toList();
        return ResponseEntity.ok().body(new PageImpl<>(aprendicesDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<AprendizDTO> updateById(
                                    @PathVariable("id") String idAprendiz,
                                    @RequestBody AprendizDTO aprendizDetails
    ){
        Optional<Aprendiz> optional = aprendicesService.findById(idAprendiz);

        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Aprendiz aprendiz = optional.get();

        aprendiz.setTipoDocumento(TipoDocumento.valueOf(aprendizDetails.getTipoDocumento()));
        aprendiz.setFicha(aprendizDetails.getFicha());
        aprendiz.setPrograma(aprendizDetails.getPrograma());
        aprendiz.setCelular(aprendizDetails.getCelular());
        aprendiz.setCorreoElectronico(aprendizDetails.getCorreoElectronico());
        
        aprendicesService.save(aprendiz);

        return ResponseEntity.ok().body(aprendizToAprendizDTO.map(aprendiz));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String idAprendiz){

        Optional<Aprendiz> optional = aprendicesService.findById(idAprendiz);

        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        aprendicesService.delete(optional.get());
        return ResponseEntity.ok().body(true);

    }

    @PostMapping
    public ResponseEntity<HttpStatusCode> save(@RequestBody AprendizDTO aprendizDTO){

        Optional<Aprendiz> optional = aprendicesService.findById(aprendizDTO.getNumeroDocumento());

        if (optional.isPresent()) {
            return ResponseEntity.status(409).build();
        }

        Aprendiz aprendiz = aprendizDTOtoAprendiz.map(aprendizDTO);
        aprendicesService.save(aprendiz);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/enable/{id}")
    public ResponseEntity<Boolean> enableById(@PathVariable("id") String idAprendiz){

        Optional<Aprendiz> optional = aprendicesService.findById(idAprendiz);

        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        if (optional.get().getStatus().equals(AprendizStatus.HABILITADO)) {
            return ResponseEntity.badRequest().body(false);
        }

        Aprendiz aprendiz = optional.get();
        aprendiz.setStatus(AprendizStatus.HABILITADO);
        aprendicesService.save(aprendiz);
        return ResponseEntity.ok().body(true);

    }

    @PatchMapping("/disable/{id}")
    public ResponseEntity<Boolean> disableById(@PathVariable("id") String idAprendiz){

        Optional<Aprendiz> optional = aprendicesService.findById(idAprendiz);

        if(!optional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        
        if (optional.get().getStatus().equals(AprendizStatus.INHABILITADO)) {
            return ResponseEntity.badRequest().body(false);
        }

        Aprendiz aprendiz = optional.get();
        aprendiz.setStatus(AprendizStatus.INHABILITADO);
        aprendicesService.save(aprendiz);
        return ResponseEntity.ok().body(true);

    }

}
