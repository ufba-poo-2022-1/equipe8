package daos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entidades.Turma;

public class Dao_Turma {
	
	
	public Long saveTurma(Turma turma){
	
		BD.bdTurma.add(turma);
		return turma.getId();
	
	}
	
	
	public boolean updateTurma(Turma newTurma){
		
		BD.bdTurma.stream().filter(a -> a.getId()==newTurma.getId()).
			collect(Collectors.toList()).forEach(p ->{
			p.setDisciplina(newTurma.getDisciplina());
			p.setProfessor(newTurma.getProfessor());
		});
	    return true;
}
	
	
	
	public List<Turma> getAll(){
		return BD.bdTurma;
	}


	public List<Turma> getTurmaDisciplina(Long codigoDisciplina){
		Stream<Turma>  stream = this.getAll().stream();
		return stream.filter(p -> p.contemDisciplina(codigoDisciplina)).collect(Collectors.toList());
	}
	
	public List<Turma> getTurmaProfessor(Long codigoProfessor){
		Stream<Turma> stream = this.getAll().stream();
		return stream.filter(p -> p.contemProfessor(codigoProfessor)).collect(Collectors.toList());
	}
	

	public List<Turma> getTurmaAlnuo(Long matriculaAlubo){
		return this.getAll().stream().filter(turma -> turma.contemAluno(matriculaAlubo)).collect(Collectors.toList());
	}
	
	public Turma getTurmaCodigo(Long codigo){
		List<Turma> list = BD.bdTurma.stream().filter(p -> p.getId()== codigo).collect(Collectors.toList());
		if(list.isEmpty()) { return null;}	
		return list.get(0);	
	}

	public boolean deleteTurma(Long codigo) {
		return BD.bdTurma.removeIf(P -> P.getId() == codigo);
	}
	
	
	
}
