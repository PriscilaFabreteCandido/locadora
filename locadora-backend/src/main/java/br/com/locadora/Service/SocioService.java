package br.com.locadora.Service;

import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.DTO.SocioDTO;
import br.com.locadora.Model.Ator;
import br.com.locadora.Model.Dependente;
import br.com.locadora.Model.Socio;
import br.com.locadora.Repository.SocioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocioService {

	private final SocioRepository socioRepository;

	public Socio create(SocioDTO socioDTO){
		Socio novoSocio = new Socio();
		System.out.println("CPF SOCIO" + socioDTO.getCPF());
		BeanUtils.copyProperties(socioDTO, novoSocio);

		return socioRepository.save(novoSocio);
	}

	public Socio update(SocioDTO socioDTO, Long id){
		Socio socioEncontrado = findById(id);
		BeanUtils.copyProperties(socioDTO, socioEncontrado, "numInscricao");


		if(!socioEncontrado.isEsta_ativo()){
			for(Dependente dependente : socioEncontrado.getDependentes()){
				dependente.setEsta_ativo(false);
			}
		}

		return socioRepository.save(socioEncontrado);
	}

	public Socio ativarOrDesativarSocio(SocioDTO socioDTO, Long id){
		Socio socioEncontrado = findById(id);

		if(socioEncontrado !=  null){
			socioEncontrado.setEsta_ativo(socioDTO.isEsta_ativo());
			for(Dependente dependente : socioEncontrado.getDependentes()){
				socioEncontrado.setEsta_ativo(socioDTO.isEsta_ativo());
			}
		}

		return socioRepository.save(socioEncontrado);
	}
	public void delete(Long id){
		Socio socioEncontrado = findById(id);

		socioRepository.delete(socioEncontrado);
	}

	public Socio findById(Long id){
		return socioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Socio não encontrado."));
	}

	public List<Socio> findAll(){
		return socioRepository.findAll();
	}
}
