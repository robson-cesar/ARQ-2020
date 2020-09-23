package validador;

import model.Bairro;

public class BairroValidador {
	
	private String mensagem = null;

	public boolean isNaoValido(Bairro bairro) {
		mensagem = null;
		
		if (isEmpty(bairro.getNome())) {
			mensagem = "Bairro não informado.";
			return true;
		}
		
		if (bairro.getNome().length() > 50) {
			mensagem = "Bairro inválido.";
			return true;
		}
		
		if (bairro.getCidade() == null) { 
			mensagem = "Cidade não informada.";
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
