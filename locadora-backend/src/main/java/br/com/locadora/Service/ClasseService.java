package br.com.locadora.Service;

import br.com.locadora.DTO.ClasseDTO;
import br.com.locadora.Model.Classe;
import br.com.locadora.Repository.ClasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClasseService  {

    @Autowired
    private ClasseRepository classeRepository;

    public Classe inserir(ClasseDTO classeDTO){
        if(classeDTO == null){
            throw new IllegalArgumentException("Classe vazia");
        }

        Classe classe = new Classe();
        classe.setNome(classeDTO.getNome());
        classe.setPrazoDevolucao(classeDTO.getPrazoDevolucao());
        classe.setValor(classeDTO.getValor());

        return classeRepository.save(classe);
    }

    public Classe atualizar(ClasseDTO classeParametro, Long id){
        Classe classeEncontrada = findById(id);

        if(classeEncontrada != null){
            classeEncontrada.setNome((classeParametro.getNome()));
            classeEncontrada.setPrazoDevolucao(classeParametro.getPrazoDevolucao());
            classeEncontrada.setValor(classeParametro.getValor());
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
