package br.com.oak.webly.core.mensagem;

public enum MensagemSucesso {
	
	OPERACAO_COM_SUCESSO("MSG_S001"),
	USUARIO_CADASTRADO_COM_SUCESSO("MSG_S002"),
	MENSAGEM_ENVIADA_COM_SUCESSO("MSG_S997");
	
	private String codigo;

	private MensagemSucesso(String codigo){
		this.setCodigo(codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}