package validador;

public class BairroValidador {

	public boolean naoPodeIncluir(String str) {
		
		if (str == null) {
			return true;
		}

		if (str.equals("''")) { 
			return true;
		}

		if (str.length()==0) { 
			return true;
		}

		return false;
	}

}
