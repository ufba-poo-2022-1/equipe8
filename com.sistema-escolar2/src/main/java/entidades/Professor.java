package entidades;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cascade;

import DAOs.BD;

@Entity

public class Professor extends Pessoa{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private boolean status;
	public Professor() {
		this.status = true;
	}
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Professor(String nome,String nascimento, String cpf,String email) {
		super(nome, nascimento, cpf,email);
		this.status = true;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	@Override
	public boolean equals(Object obj) {
		  if(this == obj) return true;
	        if(obj == null || getClass() != obj.getClass()) return false;
	        Professor outroProfessor = (Professor) obj;
	        if(this.getCodigo()==outroProfessor.getCodigo()) {
	        	return true;
	        }
	        return false;
	}
	
	@Override
	public String toString() {
		return "Nome: "+getNome()+"\n Nascimento: "+getNascimento()+"\nCpf: "+getCpf()+"\nEmail: "+getEmail()+"\n Codigo: "+getCodigo()+"\n";

	}
	
}
