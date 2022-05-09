package Utilitarios;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitario {
	
	
	public boolean validarCpf(String cpf) {
			if(cpf.isEmpty() || cpf == null || cpf.length()<11) {
				return false;
			}
			 boolean valid;
		        
		        String regex = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";
		        Pattern pattern = Pattern.compile(regex);
		        Matcher matcher = pattern.matcher(cpf);
		        valid =  matcher.matches();
		        return valid;
	}
	
	public boolean validarEmail(String email) {
			 if(email.isEmpty() || email == null) {
				 return false;
			 }
		    boolean valid;
	        
	        String regex = "^[\\w!#$%&�+/=?`{|}~^-]+(?:\\.[\\w!#$%&�+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(email);
	        valid =  matcher.matches();
	        return valid;
	}
	
	
	public boolean valideMatricula(String matricula) {
		if(matricula.length()>12) {
			return false;
		}
		return  matricula.matches("-?\\d+");
	}
	
	
	public boolean valideCodigo(String codigo) {
		if(codigo.length()>12) {
			return false;
		}
		return  codigo.matches("-?\\d+");
	}
	
	
	public boolean valideNome(String nome) {
		if(nome.isEmpty() || nome.length()<4) {
			return false;
		}	
		return true;
	}
	
	public boolean ValideDate(String data) { ///recebe  dd-mm-yyyy
		if(data.contains(" ")) {
			return false;
		}
		return true;
	}
	
	public String gerarData(String dia,String mes, String ano) {
		
		return dia+"-"+mes+"-"+ano;	/// DEVE RETORNAR dd-mm-yyyy exemplo 24-01-2022
	}
	
	
	
	
	
	
	
}
