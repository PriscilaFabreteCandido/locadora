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

		Socio socio = getSocioById(dependenteDTO.getSocio().getNumInscricao());

		BeanUtils.copyProperties(dependenteDTO, novoDependente);
		novoDependente.setEsta_ativo(true);

		if(maximoDependentesAtivos(socio)){
			throw new EntityNotFoundException("Socio já tem 3 dependentes ativos.");
		}

		novoDependente.setSocio(socio);
		
		return dependenteRepository.save(novoDependente);
	}
	
	public Dependente update(DependenteDTO dependenteDTO, Long id) {
		Dependente dependenteEncontrado = findById(id);

		Socio socio = socioRepository.findById(dependenteEncontrado.getSocio().getNumInscricao())
				.orElseThrow(() -> new EntityNotFoundException("Sócio não encontrado"));

		if(socioEstaAtivo(dependenteEncontrado, dependenteDTO)){
			throw new EntityNotFoundException("Dependente não pode ser ativado porque o sócio está desativado");
		}

		if(maximoDependentesAtivos(socio) && dependenteDTO.isEsta_ativo()){
			throw new EntityNotFoundException("Socio já tem 3 dependentes ativos.");
		}

		BeanUtils.copyProperties(dependenteDTO, dependenteEncontrado, "numInscricao");


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

	public Dependente ativarOrDesativar(DependenteDTO dependenteDTO, Long id) {
		Dependente dependenteEncontrado = findById(id);

		if(socioEstaAtivo(dependenteEncontrado, dependenteDTO)){
			throw new EntityNotFoundException("Dependente não pode ser ativado porque o sócio está desativado");
		}

		Socio socio = getSocioById(dependenteEncontrado.getSocio().getNumInscricao());

		if(maximoDependentesAtivos(socio) && dependenteDTO.isEsta_ativo()){
			throw new EntityNotFoundException("Socio já tem 3 dependentes ativos.");
		}

		if(dependenteDTO != null){
			dependenteEncontrado.setEsta_ativo(dependenteDTO.isEsta_ativo());
		}

		return  dependenteRepository.save(dependenteEncontrado);
	}

	//=========================================

	private Socio getSocioById(Long socioId) {
		return socioRepository.findById(socioId)
				.orElseThrow(() -> new EntityNotFoundException("Sócio não encontrado"));
	}

	private boolean socioEstaAtivo(Dependente dependenteEncontrado, DependenteDTO dependenteDTO){
		return !dependenteEncontrado.getSocio().isEsta_ativo() && dependenteDTO.isEsta_ativo();

	}

	private boolean maximoDependentesAtivos(Socio socio) {
		int qtdeDependentesAtivos = 0;

		for (Dependente dependente : socio.getDependentes()) {
			if (dependente.isEsta_ativo()) {
				qtdeDependentesAtivos++;
			}
		}

		if(qtdeDependentesAtivos >= 3){
			return true;
		}
		return false;
	}



}