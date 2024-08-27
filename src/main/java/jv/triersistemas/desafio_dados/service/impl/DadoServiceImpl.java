package jv.triersistemas.desafio_dados.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
		if (qtd < 1 || qtd > 4) {
			return ResponseEntity.badRequest().body("Quantidade de dados deve ser entre 1 e 4");
		}
		int apostaMax = 6 * qtd;
		if (aposta > apostaMax || aposta < qtd) {
			return ResponseEntity.badRequest().body("Digite um valor válido de aposta");
		}
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
			sb.append("Dado ").append(cont).append(": Valor sorteado ").append(mapDados.get(numDado));
			soma += mapDados.get(numDado);
			sb.append("\n");
		}
		sb.append("Soma: ").append(soma);
		sb.append("\nPercentual em relação ao sorteio: ").append(calculaPercentual(soma, aposta)).append("%");
		return sb.toString();
	}

	private BigDecimal calculaPercentual(int soma, Integer aposta) {

        return BigDecimal.valueOf(soma).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(aposta), 0, RoundingMode.DOWN);
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
