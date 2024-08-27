package jv.triersistemas.desafio_dados.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.triersistemas.desafio_dados.dto.DadoDTO;
import jv.triersistemas.desafio_dados.service.DadoService;

@RestController
@RequestMapping("/desafio")
public class DadoController {
	@Autowired
	private DadoService dadoService;
	
	@PostMapping
	public ResponseEntity<String> cadastraDados(@RequestBody DadoDTO dadoDTO) {
		return dadoService.cadastraDados(dadoDTO.qtd(), dadoDTO.aposta());
	}
}
