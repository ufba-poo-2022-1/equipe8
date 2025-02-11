package Utilitarios;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitario {
	
	public boolean validarCpf(String CPF) {
	        boolean valid = false;
	        if(CPF.isEmpty()) {return false;}
	        if (CPF.equals("00000000000") ||
	                CPF.equals("11111111111") ||
	                CPF.equals("22222222222") || CPF.equals("33333333333") ||
	                CPF.equals("44444444444") || CPF.equals("55555555555") ||
	                CPF.equals("66666666666") || CPF.equals("77777777777") ||
	                CPF.equals("88888888888") || CPF.equals("99999999999") ||
	                (CPF.length() != 11)) {
	            return (valid);
	        }

	        char dig10, dig11;
	        int sm, i, r, num, peso;

	        try {
	            sm = 0;
	            peso = 10;
	            for (i = 0; i < 9; i++) {
	                num = (int) (CPF.charAt(i) - 48);
	                sm = sm + (num * peso);
	                peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else
	                dig10 = (char) (r + 48);

	            sm = 0;
	            peso = 11;
	            for (i = 0; i < 10; i++) {
	                num = (int) (CPF.charAt(i) - 48);
	                sm = sm + (num * peso);
	                peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig11 = '0';
	            else
	                dig11 = (char) (r + 48);

	            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
	                valid = true;
	      
	            return (valid);
	        } catch (Error e) {       
	            return (valid);
	        }
	    
	}
	//private static boolean validateDate(String date) {
       
    //}
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
	
	public boolean ValideDate(String date) {
	      boolean valid;

	        String regex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";

	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(date);
	        valid = matcher.matches();

	        if (valid) {
	            String[] splitDate = date.split("-");
	            int year = Integer.parseInt(splitDate[2]);
			    Calendar c = Calendar.getInstance();
	            if ((year < 1901) || (year+1 > c.get(Calendar.YEAR))){
	                valid = false;
	            }
	        }


	        return valid;
	    }
	
	
	public String gerarData(String dia,String mes, String ano) {
		
		return dia+"-"+mes+"-"+ano;	/// DEVE RETORNAR dd-mm-yyyy exemplo 24-01-2022
	}
	
	
	
	
	
	
	
}
