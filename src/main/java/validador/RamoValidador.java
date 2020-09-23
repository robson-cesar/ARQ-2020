package validador;

import model.Ramo;

public class RamoValidador {

	private String mensagem = null;

	public boolean isNaoValido(Ramo ramo) {
		mensagem = null;

		if (isEmpty(ramo.getNome())) {
			mensagem = "Ramo não informado.";
			return true;
		}

		if (ramo.getNome().length() > 50) {
			mensagem = "Ramo inválido.";
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
