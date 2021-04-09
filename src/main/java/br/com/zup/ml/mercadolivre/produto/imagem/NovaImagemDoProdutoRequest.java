package br.com.zup.ml.mercadolivre.produto.imagem;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class NovaImagemDoProdutoRequest {

	@Size(min = 1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public NovaImagemDoProdutoRequest(@Size(min = 1) @NotNull List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	public List<MultipartFile> getImagens() {
		return imagens;
	}
}