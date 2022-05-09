package entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import DAOs.BD;

@Entity
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = { CascadeType.MERGE})
	private Disciplina disciplina;
	
	@OneToOne(cascade = { CascadeType.MERGE})
	private Professor professor;
	
	@OneToMany(cascade =  {CascadeType.ALL})
	private Set<Aluno> listAluno = null;
	
	public Turma() {
		
	}
	
	public Turma(Disciplina disciplina,Professor professor) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.listAluno = new HashSet<Aluno>();
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getListAluno() {
		List<Aluno> list = new ArrayList<Aluno>();
		list.addAll(listAluno);
		return list;
	}
		
	public boolean contemAluno(Long matriculaAluno) {	
		return	listAluno.stream().anyMatch(p-> p.getMatricula()==matriculaAluno);
	}
	
	
	public boolean contemProfessor(Long Codigoprofessor) {
		
		if(this.getProfessor()!=null && this.getProfessor().getCodigo()!=null) {
			return Codigoprofessor== this.getProfessor().getCodigo();
		}
		return false;
	}
	
	public boolean contemDisciplina(Long CodigoDisciplina) {
		
		if(this.getDisciplina()!=null && this.getDisciplina().getCodigo()!=null) {
			return CodigoDisciplina== this.getDisciplina().getCodigo();
		}
		return false;
	}
	
	public void setListAluno(List<Aluno> newLista) {
		if(newLista !=null) {
			this.listAluno.clear();
			this.listAluno.addAll(newLista);
		}
	}

	public boolean addAluno(Aluno Aluno) {
		return this.listAluno.add(Aluno);
	}

	public Long getId() {
		return id;
	}
	
	
	@Override
	public String toString() {
		String professor = " - ";
		String disciplina = " - ";
		String cdProfessor = " - ";
		String cdDisciplina = " - ";
		if(this.getProfessor() != null) {
			if(this.getProfessor().getCodigo() !=null) {
				cdProfessor = ""+this.getProfessor().getCodigo();
			}
			if(this.getProfessor().getNome()!=null) {
				professor = this.getProfessor().getNome();
			}
		}
		
		if(this.getDisciplina() != null) {
			if(this.getDisciplina().getCodigo() !=null) {
				cdProfessor = ""+this.getDisciplina().getCodigo();
			}
			if(this.getDisciplina().getNome()!=null) {
				professor = this.getDisciplina().getNome();
			}
		}
		
		
		return  "CODIGO: "+this.id+"\nDISCIPLINA: "+disciplina+"\nCODIGO DISCIPLINA: "
		+cdDisciplina+"\nPROFESSOR: "+professor+"\nCODIGO DO PROFESSOR: "
		+cdProfessor;
	}
	


	
}
