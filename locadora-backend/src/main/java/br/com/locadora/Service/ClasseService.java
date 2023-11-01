package br.com.locadora.Service;

import br.com.locadora.DTO.ClasseDTO;
import br.com.locadora.Model.Classe;
import br.com.locadora.Repository.ClasseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClasseService  {
    
    private final ClasseRepository classeRepository;

    public Classe create(ClasseDTO classeDTO){
        Classe classe = new Classe();
        BeanUtils.copyProperties(classeDTO, classe);

        return classeRepository.save(classe);
    }

    public Classe update(ClasseDTO classeDTO, Long id){
        Classe classeEncontrada = findById(id);
        BeanUtils.copyProperties(classeDTO, classeEncontrada, "id_classe");

        return classeRepository.save(classeEncontrada);
    }

    public void delete(Long id){
        Classe classeEncontrada = findById(id);

        classeRepository.delete(classeEncontrada);
    }

    public Classe findById(Long id){
    return classeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Classe não encontrada."));
    }

    public List<Classe> findAll(){
        return classeRepository.findAll();
    }
}
