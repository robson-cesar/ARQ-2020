package processor;

import java.util.List;

import model.Cidade;

public class DadosCsv {
	
	private String campos[];
	
	public DadosCsv(String linha) {
		campos = linha.split(";");
	}
	
	public String getRamo() {
		return campos[0];
	}
	
	public String getRazaoSocial() {
		return campos[1];
	}
	
	public String getLogradouro() {
		return campos[2];
	}
	
	public String getBairro() {
		return campos[3];
	}
	
	public String getCep() {
		return campos[4];
	}
	
	public String getCidade() {
		return campos[5];
	}
	
	public String getSiglaUf() {
		return campos[6];
	}
	
	public String getContato() {
		return campos[7];
	}
	
	public Integer getFuncionario () {
		try {
			return Integer.parseInt(campos[8]);
		} catch(Exception e) {
			return null;	
		}
	}
		
	public String getTelefone() {
		return campos[9];
	}
	
	public String getFax() {
		return campos[10];
	}
	
	public String getSite() {
		return campos[11];
	}
	
	public String getEmail() {
		return campos[12];
	}
	
	public String getProduto() {
		return campos[13];
	}

	public int getColunasDisponiveis() {
		return campos.length;
	}
}
