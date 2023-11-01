package br.com.locadora.Service;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.DTO.TituloDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Model.Classe;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Repository.TituloRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TituloService {
    
    private final TituloRepository tituloRepository;
    private final AtorService atorService;

    public Titulo create(TituloDTO tituloDTO){
        Titulo titulo = new Titulo();
        BeanUtils.copyProperties(tituloDTO, titulo);

        Diretor diretor = new Diretor();
        BeanUtils.copyProperties(tituloDTO.getDiretor(), diretor);
        titulo.setDiretor(diretor);

        Classe classe = new Classe();
        BeanUtils.copyProperties(tituloDTO.getClasse(), classe);
        titulo.setClasse(classe);

        List<Ator> listaAtores = new ArrayList<>();
        for (AtorDTO atorDTO : tituloDTO.getListaAtores()) {
            Ator ator = new Ator();
            BeanUtils.copyProperties(atorDTO, ator);
            listaAtores.add(ator);
        }
        titulo.setListaAtores(listaAtores);

        return tituloRepository.save(titulo);
    }

    public Titulo update(TituloDTO tituloDTO, Long id) {
        Titulo tituloEncontrado = findById(id);

        BeanUtils.copyProperties(tituloDTO, tituloEncontrado, "id_titulo");

        if (tituloDTO.getDiretor() != null) {
            Diretor diretor = new Diretor();
            BeanUtils.copyProperties(tituloDTO.getDiretor(), diretor);
            tituloEncontrado.setDiretor(diretor);
        }

        if (tituloDTO.getClasse() != null) {
            Classe classe = new Classe();
            BeanUtils.copyProperties(tituloDTO.getClasse(), classe);
            tituloEncontrado.setClasse(classe);
        }

        if (tituloDTO.getListaAtores() != null) {
            List<Ator> listaAtores = new ArrayList<>();
            for (AtorDTO atorDTO : tituloDTO.getListaAtores()) {
                Ator ator = new Ator();
                BeanUtils.copyProperties(atorDTO, ator);
                listaAtores.add(ator);
            }
            tituloEncontrado.setListaAtores(listaAtores);
        }

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
