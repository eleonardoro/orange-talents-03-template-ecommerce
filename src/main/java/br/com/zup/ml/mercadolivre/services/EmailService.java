package br.com.zup.ml.mercadolivre.services;

import org.springframework.stereotype.Service;

import br.com.zup.ml.mercadolivre.compra.Compra;
import br.com.zup.ml.mercadolivre.pagamento.Pagamento;
import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.pergunta.PerguntaDoProduto;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Service
public class EmailService {

	public void enviaEmailParaDonoDoProdutoParaNovaPergunta(Produto produto, Usuario usuarioDaPergunta,
			PerguntaDoProduto pergunta) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá " + produto.getUsuarioDono().getEmail() + "!");
		sb.append("\n");
		sb.append("\n");
		sb.append("O possível comprador " + usuarioDaPergunta.getEmail()
				+ " acabou de fazer uma pergunta em um dos seus produtos.");
		sb.append("\n");
		sb.append("\n");
		sb.append("Seguem mais informações:");
		sb.append("\n");
		sb.append("Produto: " + produto.getNome() + ";");
		sb.append("\nPergunta: " + pergunta.getTitulo());
		sb.append("\n\n");
		sb.append("Link rápido para a pergunta: www.www");
		sb.append("\n\n");
		System.out.println(sb.toString());
	}

	public void enviaEmailParaDonoDoProdutoParaNovaCompra(Produto produto, Compra compra) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá " + produto.getUsuarioDono().getEmail() + "!");
		sb.append("\n");
		sb.append("\n");
		sb.append("O comprador " + compra.getComprador().getEmail()
				+ " acabou de fazer uma compra em um dos seus produtos.");
		sb.append("\n");
		sb.append("\n");
		sb.append("Seguem mais informações:");
		sb.append("\n");
		sb.append("Produto: " + produto.getId() + " - " + produto.getNome() + ";");
		sb.append("\nQuantidade: " + compra.getQuantidade());
		sb.append("\nValor: " + compra.getValorDaCompra());
		sb.append("\nForma De Pagamento: " + compra.getFormaDePagamento());
		sb.append("\nQuantidade: " + compra.getQuantidade());
		sb.append("\n\n");
		System.out.println(sb.toString());
	}

	public void enviaEmailParaDonoDoProdutoParaNovoPagamentoComErro(Compra compra, Pagamento pagamento) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá " + compra.getProduto().getVendedor().getEmail() + "!");
		sb.append("\n");
		sb.append("\n");
		sb.append("O comprador " + compra.getComprador().getEmail()
				+ " acabou de tentar fazer um pagamento para a compra que fez com você, porém deu erro.");
		sb.append("\n");
		sb.append("\n");
		sb.append("Seguem mais informações:");
		sb.append("\n");
		sb.append("Produto: " + compra.getProduto().getId() + " - " + compra.getProduto().getNome() + ";");
		sb.append("\nId do Pagamento: " + pagamento.getId());
		sb.append("\nData do Pagamento: " + pagamento.getDataCriacao());
		sb.append("\nGateway do Pagamento: " + pagamento.getGatewayDePagamento());
		sb.append("\n\n");
		System.out.println(sb.toString());
	}

	public void enviaEmailParaCompradorDoProdutoParaNovoPagamentoComSucesso(Compra compra, Pagamento pagamento) {
		StringBuilder sb = new StringBuilder();
		sb.append("Olá " + compra.getComprador().getEmail() + "!");
		sb.append("\n");
		sb.append("\n");
		sb.append("A compra " + compra.getId() + " acabou de ter o pagamento confirmado.");
		sb.append("\n");
		sb.append("\n");
		sb.append("Seguem mais informações:");
		sb.append("\n--- Compra ---");
		sb.append("\nCompra: " + compra.getId());
		sb.append("\nForma de Pagamento: " + compra.getFormaDePagamento());
		sb.append("\nProduto: " + compra.getProduto().getId() + " - " + compra.getProduto().getNome() + ";");
		sb.append("\nQuantidade: " + compra.getQuantidade());
		sb.append("\nValor: " + compra.getValorDaCompra());
		sb.append("\n--- Pagamento ---");
		sb.append("\nId do Pagamento: " + pagamento.getId());
		sb.append("\nData do Pagamento: " + pagamento.getDataCriacao());
		sb.append("\nGateway do Pagamento: " + pagamento.getGatewayDePagamento());
		sb.append("\n\n");
		System.out.println(sb.toString());
	}
}