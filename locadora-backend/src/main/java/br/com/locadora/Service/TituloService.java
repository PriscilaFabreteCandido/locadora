package br.com.locadora.Service;

import br.com.locadora.DTO.DiretorDTO;
import br.com.locadora.DTO.TituloDTO;
import br.com.locadora.Model.Classe;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Repository.TituloRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TituloService {
    
    private final TituloRepository tituloRepository;

    public Titulo create(TituloDTO tituloDTO){
        Titulo titulo = new Titulo();
        
        titulo.setNome(tituloDTO.getNome());
        titulo.setAno(tituloDTO.getAno());
        titulo.setCategoria(tituloDTO.getCategoria());
        titulo.setSinopse(titulo.getSinopse());

        return tituloRepository.save(titulo);
    }

    public Titulo update(TituloDTO tituloDTO, Long id){
        Titulo tituloEncontrado = findById(id);

        tituloEncontrado.setNome((tituloDTO.getNome()));
        tituloEncontrado.setCategoria(tituloDTO.getCategoria());
        tituloEncontrado.setAno(tituloDTO.getAno());
        tituloEncontrado.setSinopse(tituloDTO.getSinopse());

        return tituloRepository.save(tituloEncontrado);
    }

    public void delete(Long id){
        Titulo tituloEncontrado = findById(id);

        tituloRepository.delete(tituloEncontrado);
    }

    public Titulo findById(Long id){
        return tituloRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Título não encontrado."));
    }

    public List<Titulo> findAll(){
        return tituloRepository.findAll();
    }

}
