package br.com.zup.ml.mercadolivre.services;

import org.springframework.stereotype.Service;

import br.com.zup.ml.mercadolivre.produto.Produto;
import br.com.zup.ml.mercadolivre.produto.pergunta.PerguntaDoProduto;
import br.com.zup.ml.mercadolivre.usuario.Usuario;

@Service
public class EmailService {

	public void enviaEmailParaDonoDoProdutoParaNovaPergunta(Produto produto, Usuario usuarioDaPergunta, PerguntaDoProduto pergunta) {
		System.out.println("Olá " + produto.getUsuarioDono().getEmail() + "!"
				+ "\n"
				+ "\n"
				+ "O possível comprador " + usuarioDaPergunta.getEmail() + " acabou de fazer uma pergunta em um dos seus produtos."
				+ "\n"
				+ "\n"
				+ "Seguem mais informações:"
				+ "\n"
				+ "Produto: " + produto.getNome() + ";"
				+ "\nPergunta: " + pergunta.getTitulo()
				+ "\n\n"
				+ "Link rápido para a pergunta: www.www");
		
	}
}