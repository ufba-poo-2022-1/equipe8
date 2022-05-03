package entidades;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Aluno extends Pessoa{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricula;
	public Aluno(){
		
	}
	
	public Aluno(String nome, Date nascimento, String cpf,String email) {
		super(nome, nascimento, cpf,email);
	}
	
	public Long getMatricula() {
		return matricula;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Aluno outroAluno = (Aluno) obj;
        if(this.getMatricula()==outroAluno.getMatricula()) {
        	return true;
        }
        return false;
    }
	
	@Override
	public int hashCode() {
		return this.getCpf().hashCode();
	}
	
	@Override
	public String toString() {
		return "Nome: "+getNome()+"\nNascimento: "+getNascimento()+"\nCpf: "+getCpf()+"\nEmail: "+getEmail() +"\nMatricula: "+getMatricula()+"\n";
	}

}
