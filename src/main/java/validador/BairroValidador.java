package validador;

public class BairroValidador {

	public boolean naoPodeIncluir(String bairro) {
		
		if (bairro == null) {
			return true;
		}

		if (bairro.equals("''")) { 
			return true;
		}

		if (bairro.length()==0) { 
			return true;
		}

		return false;
	}

}
