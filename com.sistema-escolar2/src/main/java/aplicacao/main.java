package aplicacao;

import DAOs.Dao_Aluno;
import DAOs.Dao_Turma;
import Telas.Tela_Inicial_Diretor;

public class main{




	public static void main(String[] args) {
     
		System.out.println(new Dao_Turma().getAll());
	Tela_Inicial_Diretor.start_tela_diretor(null);
		
		
	}
	
	
	
	

}
