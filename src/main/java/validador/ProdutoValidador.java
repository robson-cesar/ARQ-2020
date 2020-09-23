package validador;

import model.Produto;

public class ProdutoValidador {

	private String mensagem = null;
	
	public boolean isNaoValido(Produto produto) {
		mensagem = null;
		
		if (isEmpty(produto.getNome())) {
			mensagem = "Produto não informado.";
			return true;
		}
		
		if (produto.getNome().length() > 70) {
			mensagem = "Produto inválido.";
			return true;
		}
		
		return false;
	}

	public String getMensagem() {
		return mensagem;
	}

	private boolean isEmpty(String str) { 
		return (str == null) || (str.length() == 0);
	}
	
}
