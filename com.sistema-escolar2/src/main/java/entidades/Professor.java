package entidades;



import daos.BD;

public class Professor extends Pessoa{

	private Long codigo;
	private boolean status;
	public Professor() {
		this.status = true;
		this.codigo = BD.gerarIdProfessor();
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
		this.codigo = BD.gerarIdProfessor();
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
