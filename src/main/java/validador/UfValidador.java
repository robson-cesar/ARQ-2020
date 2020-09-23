package validador;

import model.Uf;

public class UfValidador {

	private String mensagem = null;

	public boolean isNaoValido(Uf uf) {
		mensagem = null;

		if (isEmpty(uf.getNome())) {
			mensagem = "Uf não informada.";
			return true;
		}

		if (uf.getNome().length() > 20) {
			mensagem = "Uf inválida.";
			return true;
		}
		
		if (isEmpty(uf.getSigla())) {
			mensagem = "Sigla não informada.";
			return true;
		}

		if (uf.getSigla().length() > 2) {
			mensagem = "Sigla inválida.";
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
