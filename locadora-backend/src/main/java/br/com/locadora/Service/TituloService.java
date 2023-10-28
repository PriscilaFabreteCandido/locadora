package br.com.locadora.Service;

import br.com.locadora.DTO.TituloDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Model.Classe;
import br.com.locadora.Model.Diretor;
import br.com.locadora.Model.Titulo;
import br.com.locadora.Repository.TituloRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        if(tituloDTO.getAtores() != null && tituloDTO.getAtores().size() > 0){

            List<Ator> atores = new ArrayList<>();

            tituloDTO.getAtores().forEach( atorDTO -> {
                Ator ator = new Ator();

                if(atorDTO.getId_ator() > 0){
                    ator.setId_ator(atorDTO.getId_ator());
                    atores.add(ator);
                }
            });
            System.out.println("AQUIIIIIII " + atores.size());
            titulo.setListaAtores(atores);
        }

        if(tituloDTO.getDiretor() != null){
            Diretor diretor = new Diretor();
            diretor.setId_diretor(tituloDTO.getDiretor().getId_diretor());
            titulo.setDiretor(diretor);
        }

        if(tituloDTO.getClasse() != null){
            Classe classe = new Classe();
            classe.setId_classe(tituloDTO.getClasse().getId_classe());
            titulo.setClasse(classe);
        }

        return tituloRepository.save(titulo);
    }

    public Titulo update(TituloDTO tituloDTO, Long id){
        Titulo tituloEncontrado = findById(id);

        tituloEncontrado.setNome((tituloDTO.getNome()));
        tituloEncontrado.setCategoria(tituloDTO.getCategoria());
        tituloEncontrado.setAno(tituloDTO.getAno());
        tituloEncontrado.setSinopse(tituloDTO.getSinopse());

        if(!tituloDTO.getAtores().isEmpty() && tituloDTO.getAtores().size() > 0){
            List<Ator> atores = new ArrayList<>();

            tituloDTO.getAtores().forEach( atorDTO -> {
                Ator ator = new Ator();
                ator.setId_ator(ator.getId_ator());
                atores.add(ator);
            });

            tituloEncontrado.setListaAtores(atores);
        }

        if(tituloDTO.getDiretor() != null){
            Diretor diretor = new Diretor();
            diretor.setId_diretor(tituloDTO.getDiretor().getId_diretor());
            tituloEncontrado.setDiretor(diretor);
        }

        if(tituloDTO.getClasse() != null){
            Classe classe = new Classe();
            classe.setId_classe(tituloDTO.getClasse().getId_classe());
            tituloEncontrado.setClasse(classe);
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
