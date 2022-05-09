package DAOs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entidades.Disciplina;

public class Dao_Disciplina{
	
	
	public Dao_Disciplina() {
		
	}
	
	public Long saveDisciplina(Disciplina disciplina){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
	    em.persist(disciplina);
	    em.getTransaction().commit();
	    em.close();
	    return disciplina.getCodigo();
	
	}

	public boolean updateDisciplinar(Disciplina disciplinaParameter){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Disciplina disciplina = em.find(Disciplina.class, disciplinaParameter.getCodigo());
		if(disciplina==null) { return false;}
		 em.merge(disciplina);
		 disciplina.setNome(disciplinaParameter.getNome());
		 disciplina.setDescricao(disciplinaParameter.getDescricao());
	     em.getTransaction().commit();
	     em.close();
	     return true;
	}
	
	public List<Disciplina> getAll(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Disciplina> query = em.createQuery("select u from Disciplina u",Disciplina.class);
	    List<Disciplina> list = query.getResultList();
	    return list;
	}

	@SuppressWarnings("unchecked")
	public List<Disciplina> getDisciplinaNome(String nome){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
			EntityManager em = emf.createEntityManager();
			List<Disciplina> list = em.createQuery("SELECT c FROM Disciplina c  WHERE c.nome LIKE :Cnome AND status = 1").setParameter("Cnome","%"+nome+"%").getResultList();
			return list;
	}
	
	public Disciplina getDisciplinaCodigo(Long codigo){
		if(codigo== null) { return null;}
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
	    return em.find(Disciplina.class, codigo);
	}

	public boolean deleteDisciplina(Long codigo) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("escola");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Disciplina disciplina = em.find(Disciplina.class,codigo);
		if(disciplina==null) { return false;}
		 em.merge(disciplina);
		 disciplina.setStatus(false);
	     em.getTransaction().commit();
	     em.close();
	     return true;
	}
	
}
