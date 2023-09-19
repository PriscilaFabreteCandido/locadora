package br.com.locadora.Service;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public Ator inserir(AtorDTO atorDTO){
        if(atorDTO.getNome() == null || atorDTO.getNome().isEmpty()){
            throw new IllegalArgumentException("O nome do ator não pode estar vazio.");
        }

        Ator ator = new Ator();
        ator.setNome(atorDTO.getNome());
        return atorRepository.save(ator);
    }

    public Ator atualizar(AtorDTO atorDTO, Long id){
        Ator atorEncontrado = findById(id);

        if(atorEncontrado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ator não encontrado com o ID especificado.");
        }

        atorEncontrado.setNome(atorDTO.getNome());

        return atorRepository.save(atorEncontrado);
    }

    public void excluir(Long id){
        Ator atorEncontrado = findById(id);

        if(atorEncontrado == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ator não encontrado com o ID especificado.");
        }

        atorRepository.deleteById(id);
    }

    public Ator findById(Long id){
        return atorRepository.findById(id).orElse(null);
    }

    public List<Ator> findAll(){
        return atorRepository.findAll();
    }

}
