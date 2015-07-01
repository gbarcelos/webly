package br.com.oak.webly.core.mensagem;

public enum MensagemConfirmacao {
	
	DESEJA_CONFIRMAR("MSG_A001");
	
	private String codigo;

	private MensagemConfirmacao(String codigo){
		this.setCodigo(codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
