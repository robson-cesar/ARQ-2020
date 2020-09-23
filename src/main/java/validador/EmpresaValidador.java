package validador;

import model.Empresa;

public class EmpresaValidador {

	private String mensagem = null;
	
	public boolean isNaoValido(Empresa empresa) {
		mensagem = null;
		
		if (empresa.getUf() == null) { 
			mensagem = "UF não informada.";
			return true;
		}
		
		if (empresa.getCidade() == null) { 
			mensagem = "Cidade não informada.";
			return true;
		}
		
		if (empresa.getBairro() == null) { 
			mensagem = "Bairro não informado.";
			return true;
		}
		
		if (empresa.getRamo() == null) { 
			mensagem = "Ramo não informado.";
			return true;
		}
		
		if (isEmpty(empresa.getCep())) {
			mensagem = "CEP não informado.";
			return true;
		}
		
		if (empresa.getCep().length() > 10) {
			mensagem = "CEP inválido.";
			return true;
		}
		
		if (isEmpty(empresa.getLogradouro())) {
			mensagem = "Logradouro não informado.";
			return true;
		}
		
		if (empresa.getLogradouro().length() > 70) {
			mensagem = "Logradouro inválido.";
			return true;
		}
		
		if (isEmpty(empresa.getEmail())) {
			mensagem = "E-mail não informado.";
			return true;
		}
		
		if (empresa.getEmail().length() > 50) {
			mensagem = "E-mail inválido.";
			return true;
		}
		
		if (isEmpty(empresa.getSite())) {
			mensagem = "Site não informado.";
			return true;
		}
		
		if (empresa.getSite().length() > 50) {
			mensagem = "Site inválido.";
			return true;
		}
		
		if (isEmpty(empresa.getFax())) {
			mensagem = "Fax não informado.";
			return true;
		}
		
		if (empresa.getFax().length() > 30) {
			mensagem = "Fax inválido.";
			return true;
		}
		
		if (isEmpty(empresa.getTelefone())) {
			mensagem = "Telefone não informado.";
			return true;
		}
		
		if (empresa.getTelefone().length() > 30) {
			mensagem = "Telefone inválido.";
			return true;
		}
		
		if (isEmpty(empresa.getqtdFuncionario().toString())) {
			mensagem = "Quantidade de Funcionários não informada.";
			return true;
		}
		
		if (isEmpty(empresa.getContato())) {
			mensagem = "Contato não informado.";
			return true;
		}
		
		if (empresa.getContato().length() > 50) {
			mensagem = "Contato inválido.";
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
