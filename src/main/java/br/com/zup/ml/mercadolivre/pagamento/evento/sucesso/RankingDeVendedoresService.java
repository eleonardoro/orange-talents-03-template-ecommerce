package br.com.zup.ml.mercadolivre.pagamento.evento.sucesso;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.zup.ml.mercadolivre.compra.Compra;

@Service
public class RankingDeVendedoresService implements EventoDeSucessoNoPagamento {

	@Override
	public void processa(Compra compra) {

		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getComprador().getId());

		restTemplate.postForEntity("http://localhost:8080/ranking-de-vendedores", request, String.class);
	}

}