package entidades;

import daos.BD;

public class Disciplina {
	
	private Long codigo;
	private String nome;
	private String descricao;
	private boolean status;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Disciplina() {
		this.status = true;
		this.codigo = BD.gerarIdDisciplina();
	}

	public Disciplina(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
		this.status = true;
		this.codigo = BD.gerarIdDisciplina();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getCodigo() {
		return codigo;
	}
	

	@Override
	public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Disciplina outraDisciplina = (Disciplina) obj;
        if(this.getCodigo() == outraDisciplina.getCodigo()) {
        	return true;
        }
        return false;
    }
	
	@Override
	public String toString() {
		
		return "Codigo: "+getCodigo()+"\nNome: "+getNome()+"\nDescriçao: "+getDescricao()+"";
	}
	
	
	
}
