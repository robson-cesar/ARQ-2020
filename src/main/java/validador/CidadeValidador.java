package validador;

import model.Cidade;

public class CidadeValidador {

	private String mensagem = null;
	
	public boolean isNaoValido(Cidade cidade) {
		mensagem = null;
		
		if (isEmpty(cidade.getNome())) {
			mensagem = "Cidade não informada.";
			return true;
		}
		
		if (cidade.getNome().length() > 50) {
			mensagem = "Cidade inválida.";
			return true;
		}
		
		if (cidade.getUf() == null) { 
			mensagem = "UF não informada.";
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
