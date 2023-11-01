package br.com.locadora.Service;

import br.com.locadora.DTO.DiretorDTO;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Repository.DiretorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiretorService {
    
    private final DiretorRepository diretorRepository;

    public Diretor create(DiretorDTO diretorDTO){
        Diretor diretor = new Diretor();
        BeanUtils.copyProperties(diretorDTO, diretor);

        return diretorRepository.save(diretor);
    }

    public Diretor update(DiretorDTO diretorDTO, Long id){
        Diretor diretorEncontrado = findById(id);
        BeanUtils.copyProperties(diretorDTO, diretorEncontrado, "id_diretor");
        
        return diretorRepository.save(diretorEncontrado);
    }

    public void delete(Long id){
        Diretor diretorEncontrado = findById(id);

        diretorRepository.delete(diretorEncontrado);
    }

    public Diretor findById(Long id){
        return diretorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diretor não encontrado."));
    }

    public List<Diretor> findAll(){
        return diretorRepository.findAll();
    }

}
