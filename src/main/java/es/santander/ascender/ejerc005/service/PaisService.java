package es.santander.ascender.ejerc005.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santander.ascender.ejerc005.model.Pais;
import es.santander.ascender.ejerc005.repository.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    public Pais create (Pais pais){
        if (pais.getId() != null) {
            throw new CrudSecurityException("Han tratado de modificar un registro pais utilizando la creación",
                                                 CRUDOperation.CREATE, 
                                                 pais.getId());
        }
        return repository.save(pais);
    }

    public Pais read(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Pais> read(){
        return repository.findAll();
    }

    public Pais update(Pais pais){
        if (pais.getId() == null) {
            throw new CrudSecurityException("Han tratado de crear un registro pais utilizando la modificación",
                                                 CRUDOperation.UPDATE, 
                                                 pais.getId());
        }
        return repository.save(pais);
    }

    public void delete(Long id){
        repository.deleteById(id);
        return;
    }
}
