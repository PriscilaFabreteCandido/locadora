package br.com.locadora.Service;

import br.com.locadora.Model.Classe;
import br.com.locadora.Repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClasseService  {

    @Autowired
    private ClasseRepository classeRepository;

    public Classe inserir(Classe classe){
        return classeRepository.save(classe);
    }

    public Classe atualizar(Classe classeParametro, Long id){
        Classe classeEncontrada = findById(id);

        if(classeEncontrada != null){
            classeEncontrada.setNome((classeParametro.getNome()));
        }

        return classeRepository.save(classeEncontrada);
    }

    public void excluir(Long id){
        classeRepository.deleteById(id);
    }

    public Classe findById(Long id){
        return classeRepository.findById(id).orElse(null);
    }

    public List<Classe> findAll(){
        return classeRepository.findAll();
    }
}
