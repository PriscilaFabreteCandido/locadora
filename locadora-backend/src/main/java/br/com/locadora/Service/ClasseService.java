package br.com.locadora.Service;

import br.com.locadora.DTO.ClasseDTO;
import br.com.locadora.Model.Classe;
import br.com.locadora.Repository.ClasseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClasseService  {
    
    private final ClasseRepository classeRepository;

    public Classe create(ClasseDTO classeDTO){
        Classe classe = new Classe();
        
        classe.setNome(classeDTO.getNome());
        classe.setPrazoDevolucao(classeDTO.getPrazoDevolucao());
        classe.setValor(classeDTO.getValor());

        return classeRepository.save(classe);
    }

    public Classe update(ClasseDTO classeDTO, Long id){
        Classe classeEncontrada = findById(id);

        classeEncontrada.setNome((classeDTO.getNome()));
        classeEncontrada.setValor(classeDTO.getValor());
        classeEncontrada.setPrazoDevolucao(classeDTO.getPrazoDevolucao());

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
