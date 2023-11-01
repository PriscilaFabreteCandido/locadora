package br.com.locadora;

import br.com.locadora.Controller.AtorController;
import br.com.locadora.DTO.AtorDTO;
import br.com.locadora.DTO.TituloDTO;
import br.com.locadora.Service.AtorService;
import br.com.locadora.Service.TituloService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class LocadoraApplicationTests {

	@Autowired
	private AtorController atorController;
	@Autowired
	private AtorService atorService;
	@Autowired
	private TituloService tituloService;

	@Test
	void testando() {
		AtorDTO atorDTO = new AtorDTO();
		atorDTO.setNome("TESTE");
		atorService.create(atorDTO);
		System.out.printf("Ator inserido: %s\nID: %d\n\n", atorDTO.getNome(), atorDTO.getId_ator());

		TituloDTO tituloDTO = new TituloDTO();
		tituloDTO.setListaAtores(new ArrayList<>()); // Inicialize a lista
		tituloDTO.getListaAtores().add(atorDTO);
		tituloService.create(tituloDTO);


	}

}
