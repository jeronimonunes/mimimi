package br.dcc.ufmg.pm.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.dcc.ufmg.pm.model.User;

public class JpaTest {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mimimi");  
		EntityManager em = emf.createEntityManager();                               
		EntityTransaction tx = em.getTransaction();  
		tx.begin();
		User user = em.find(User.class,"jeronimonunes");
		System.out.println(user.getCity());
		tx.commit();
		em.close();
		emf.close();
	}

}
