package DAOs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidades.Professor;
import entidades.Turma;

public class Dao_Turma {
	
	public Dao_Turma() {
		
	}
	
	
	public Long saveTurma(Turma turma){
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	    em.persist(turma);
	    em.getTransaction().commit();
	    em.close();
	    return turma.getId();
	
	}
	
	
	public boolean updateTurma(Turma newTurma){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Turma turma = em.find(Turma.class, newTurma.getId());
		if(turma==null) { return false;}
		
		turma.setDisciplina(newTurma.getDisciplina());
		turma.setProfessor(newTurma.getProfessor());
		turma.setListAluno(newTurma.getListAluno());
		 em.merge(turma);
		 em.getTransaction().commit();
	     em.close();
	     return true;
}
	
	
	
	public List<Turma> getAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Turma> query = em.createQuery("select u from Turma u",Turma.class);
	    List<Turma> list = query.getResultList();
	    return list;
	}


	public List<Turma> getTurmaDisciplina(Long codigoDisciplina){
		Stream<Turma>  bv = this.getAll().stream();
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
		if(codigo== null) { return null;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
	    return em.find(Turma.class, codigo);
	}

	public boolean deleteTurma(Long codigo) {
		
		if(codigo == null){return false;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Turma turmaExcluir = em.find(Turma.class,codigo);
		if(turmaExcluir != null) {
		    em.remove(turmaExcluir);
		    em.getTransaction().commit();
		    em.close();
		    return true;
		}
		em.getTransaction().commit();
	    em.close();
		return false;
	}
	
	
	public void refreshTurma() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		getAll().forEach(p -> em.refresh(p));
	  em.getTransaction().commit();
	    em.close();
	}
	
	
	
	
}
