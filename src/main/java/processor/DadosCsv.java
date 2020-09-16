package processor;

public class DadosCsv {
	
	private String campos[];
	
	public DadosCsv(String linha) {
		campos = linha.split(";");
	}
	
	private String tratarCelula(String str) {
		return str.replaceAll("\"", "").trim();
	}
	
	public String getRamo() {
		return tratarCelula(campos[0]);
	}
	
	public String getRazaoSocial() {
		return tratarCelula(campos[1]);
	}
	
	public String getLogradouro() {
		return tratarCelula(campos[2]);
	}
	
	public String getBairro() {
		return tratarCelula(campos[3]);
	}
	
	public String getCep() {
		return tratarCelula(campos[4]);
	}
	
	public String getCidade() {
		return tratarCelula(campos[5]);
	}
	
	public String getSiglaUf() {
		return tratarCelula(campos[6]);
	}
	
	public String getContato() {
		return tratarCelula(campos[7]);
	}
	
	public Integer getFuncionario () {
		try {
			return Integer.parseInt(tratarCelula(campos[8]));
		} catch(Exception e) {
			return null;	
		}
	}
		
	public String getTelefone() {
		return tratarCelula(campos[9]);
	}
	
	public String getFax() {
		return tratarCelula(campos[10]);
	}
	
	public String getSite() {
		return tratarCelula(campos[11]);
	}
	
	public String getEmail() {
		return tratarCelula(campos[12]);
	}
	
	public String getProduto() {
		return tratarCelula(campos[13]);
	}

	public int getColunasDisponiveis() {
		return campos.length;
	}
	
	
}
