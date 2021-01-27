package br.com.project.acessos;

public enum Permissao {
	
	ADMIN("ADMIN", "ADMINISTRADOR"),
	USER("USER", "USUÁRIO PADRÃO"),
	CADASTRO_ACESSAR("CADASTRO_ACESSAR", "CADASTRO - ACESSAR"),
	FINANCEIRO_ACESSAR("FINANCEIRO_ACESSAR", "FINANCEIRO - ACESSAR"),
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR", "MENSAGEM RECEBIDA - ACESSAR"),
	
	BAIRRO_ACESSAR("BAIRRO_ACESSAR", "BAIRRO - ACESSAR"),
	BAIRRO_NOVO("BAIRRO_NOVO", "BAIRRO - NOVO"),
	BAIRRO_EDITAR("BAIRRO_EDITAR", "BAIRRO - EDITAR"),
	BAIRRO_EXCLUIR("BAIRRO_EXCLUIR", "BAIRRO - EXCLUIR");
	
	private String valor = "";
	private String descricao = "";
	
	private Permissao() {
	}

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return getValor();
	}

}
