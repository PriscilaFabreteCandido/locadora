package br.com.locadora.Service;

import br.com.locadora.Model.Ator;
import br.com.locadora.Repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public Ator inserir(Ator ator){
        if(ator.getNome() != null || ator.getNome().isEmpty()){
            throw new IllegalArgumentException("O nome do ator não pode estar vazio.");
        }

        return atorRepository.save(ator);
    }

    public Ator atualizar(Ator atorParametro, Long id){
        Ator atorEncontrado = findById(id);

        if(atorEncontrado != null){
            atorEncontrado.setNome((atorParametro.getNome()));
        }

        return atorRepository.save(atorEncontrado);
    }

    public void excluir(Long id){
        atorRepository.deleteById(id);
    }

    public Ator findById(Long id){
        return atorRepository.findById(id).orElse(null);
    }

    public List<Ator> findAll(){
        return atorRepository.findAll();
    }

}
