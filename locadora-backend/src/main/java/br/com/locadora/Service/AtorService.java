package br.com.locadora.Service;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Repository.AtorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AtorService {

    private final AtorRepository atorRepository;

    public Ator create(AtorDTO atorDTO){
        Ator novoAtor = new Ator();
        BeanUtils.copyProperties(atorDTO, novoAtor);

        return atorRepository.save(novoAtor);
    }

    public Ator update(AtorDTO atorDTO, Long id){
        Ator atorEncontrado = findById(id);
        BeanUtils.copyProperties(atorDTO, atorEncontrado, "id_ator");

        return atorRepository.save(atorEncontrado);
    }

    public void delete(Long id){
        Ator atorEncontrado = findById(id);

        atorRepository.delete(atorEncontrado);
    }

    public Ator findById(Long id){
        return atorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ator não encontrado."));
    }

    public List<Ator> findAll(){
        return atorRepository.findAll();
    }

}
