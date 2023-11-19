package br.com.locadora.Service;

import br.com.locadora.DTO.DependenteDTO;
import br.com.locadora.Model.Dependente;
import br.com.locadora.Model.Socio;
import br.com.locadora.Repository.DependenteRepository;
import br.com.locadora.Repository.DependenteRepository;
import br.com.locadora.Repository.SocioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DependenteService {
	
	private final DependenteRepository dependenteRepository;
	private final SocioRepository socioRepository;
	
	public Dependente create(DependenteDTO dependenteDTO) {
		Dependente novoDependente = new Dependente();


		Socio socio = socioRepository.findById(dependenteDTO.getSocio().getNumInscricao())
						.orElseThrow(() -> new EntityNotFoundException("Sócio não encontrado"));

		BeanUtils.copyProperties(dependenteDTO, novoDependente);

		int qtdeDependentesAtivos = 0;
		for(Dependente dependente : socio.getDependentes()){

			if(dependente.isEsta_ativo()){
				qtdeDependentesAtivos++;
			}
		}

		if(qtdeDependentesAtivos >= 3){
			throw new EntityNotFoundException("Socio já tem 3 dependentes ativos.");
		}

		novoDependente.setSocio(socio);
		
		return dependenteRepository.save(novoDependente);
	}
	
	public Dependente update(DependenteDTO dependenteDTO, Long id) {
		Dependente dependenteEncontrado = findById(id);

		Socio socio = socioRepository.findById(dependenteDTO.getSocio().getNumInscricao())
				.orElseThrow(() -> new EntityNotFoundException("Sócio não encontrado"));


		if((!socio.isEsta_ativo()) && dependenteDTO.isEsta_ativo()){
			throw new EntityNotFoundException("Dependente não pode ser ativado pq o sócio ta desativado");
		}


		BeanUtils.copyProperties(dependenteDTO, dependenteEncontrado, "numInscricao");

		int qtdeDependentesAtivos = 0;
		for(Dependente dependente : socio.getDependentes()){

			if(dependente.isEsta_ativo()){
				qtdeDependentesAtivos++;
			}
		}

		if(qtdeDependentesAtivos >= 3){
			throw new EntityNotFoundException("Socio já tem 3 dependentes ativos.");
		}

		dependenteEncontrado.setSocio(socio);
		
		return dependenteRepository.save(dependenteEncontrado);
	}
	
	public void delete(Long id) {
		Dependente dependenteEncontrado = findById(id);
		
		dependenteRepository.delete(dependenteEncontrado);
	}
	
	public Dependente findById(Long id) {
		return dependenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dependente não encontrado."));
	}
	
	public List<Dependente> findAll() {
		return dependenteRepository.findAll();
	}
}