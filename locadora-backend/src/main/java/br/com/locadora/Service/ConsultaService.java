package br.com.locadora.Service;

import br.com.locadora.Model.Titulo;
import br.com.locadora.Repository.TituloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final TituloRepository tituloRepository;

    public List<Titulo> consultaCombinada(String nomeAtor, String nomeTitulo, String categoria) {
        return tituloRepository.findByAtorCategoriaNome(nomeAtor, nomeTitulo, categoria);
    }

}
