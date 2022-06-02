package daos;

import java.util.List;
import java.util.stream.Collectors;
import entidades.Aluno;

public class Dao_Aluno {
	
	public Long saveAluno(Aluno aluno){
		
		BD.bdAluno.add(aluno);
		return aluno.getMatricula();
	}
	
	public boolean updateAluno(Aluno alunoParameter){
		
		BD.bdAluno.stream().filter(a -> a.getMatricula()==alunoParameter.getMatricula()).
		collect(Collectors.toList()).forEach(p ->{
		p.setCpf(alunoParameter.getCpf());
		p.setEmail(alunoParameter.getEmail());
		p.setNascimento(alunoParameter.getNascimento());
		p.setNome(alunoParameter.getNome());
		});
	    return true;
	
}
	
	public List<Aluno> getAll(){
	return BD.bdAluno;
	}

	public List<Aluno> getAlunoNome(String nome){
	    return BD.bdAluno.stream().filter(p -> p.getNome().contains(nome)).
	    collect(Collectors.toList());
	}
	
	public List<Aluno> getAlunoCpf(String cpf){
		return BD.bdAluno.stream().filter(p -> p.getCpf().equalsIgnoreCase(cpf)).
				  collect(Collectors.toList());
	}
	public Aluno getAlunoMatricula(Long matricula){
	List<Aluno> list = BD.bdAluno.stream().filter(p -> p.getMatricula()== matricula).collect(Collectors.toList());
	if(list.isEmpty()) { return null;}	
		return list.get(0);		
	}
	public boolean deleteAluno(Long matricula) {
		Dao_Turma dao_turma = new Dao_Turma();
		dao_turma.getAll().forEach(p -> {
			p.removAluno(matricula);
		});
		return BD.bdAluno.removeIf(p -> p.getMatricula() == matricula);
	}
	
}



