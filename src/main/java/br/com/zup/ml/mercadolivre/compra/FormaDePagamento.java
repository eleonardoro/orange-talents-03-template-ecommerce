package br.com.zup.ml.mercadolivre.compra;

public enum FormaDePagamento {

	PAGSEGURO("paypal.com?buyerId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}", "..."),
	PAYPAL("pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}", "...");

	private String urlDeEnvio;
	private String urlRetornoAppPosPagamento;

	private FormaDePagamento(String urlDeEnvio, String urlRetornoAppPosPagamento) {
		this.urlDeEnvio = urlDeEnvio;
		this.urlRetornoAppPosPagamento = urlRetornoAppPosPagamento;
	}

	public String montaUrlDeEnvio(String idCompra) {
		return this.urlDeEnvio.replace("{idGeradoDaCompra}", idCompra.toString()).replace("{urlRetornoAppPosPagamento}",
				this.urlRetornoAppPosPagamento);
	}

}