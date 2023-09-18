package br.com.locadora.Service;

import br.com.locadora.Model.Diretor;
import br.com.locadora.Repository.DiretorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    public Diretor inserir(Diretor diretor){
        return diretorRepository.save(diretor);
    }

    public Diretor atualizar(Diretor diretorParametro, Long id){
        Diretor diretorEncontrado = findById(id);

        if(diretorEncontrado != null){
            diretorEncontrado.setNome((diretorParametro.getNome()));
        }

        return diretorRepository.save(diretorEncontrado);
    }

    public void excluir(Long id){
        diretorRepository.deleteById(id);
    }

    public Diretor findById(Long id){
        return diretorRepository.findById(id).orElse(null);
    }

    public List<Diretor> findAll(){
        return diretorRepository.findAll();
    }

}
