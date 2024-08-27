package jv.triersistemas.desafio_dados.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jv.triersistemas.desafio_dados.service.DadoService;

@Service
public class DadoServiceImpl implements DadoService {

	@Override
	public ResponseEntity<String> cadastraDados(Integer qtd, Integer aposta) {
		Map<Integer, Integer> mapDados = geraDados(qtd);
		String body = geraBody(mapDados, aposta);
		return ResponseEntity.ok(body);
	}

	private String geraBody(Map<Integer, Integer> mapDados, Integer aposta) {
		StringBuilder sb = new StringBuilder();
		int cont = 0;
		int soma = 0;
		for (Integer numDado : mapDados.keySet()) {
			cont++;
			sb.append("Dado "+ cont +": Valor sorteado "+ mapDados.get(numDado));
			soma += mapDados.get(numDado);
			sb.append("\n");
		}
		sb.append("Soma: "+ soma);
		sb.append("\nPercentual: "+ (calculaPercentual(soma, aposta)));
		return sb.toString();
	}

	private Object calculaPercentual(int soma, Integer aposta) {
		double percentual = (soma/aposta)*100;
		return percentual;
	}

	private Map<Integer, Integer> geraDados(Integer qtd) {
		Map<Integer, Integer> mapDados = new HashMap<>();
		Random r = new Random();
		for (int i = 1; i <= qtd; i++) {
			mapDados.put(i, (r.nextInt(6)+1));
		}
		return mapDados;
	}

}
