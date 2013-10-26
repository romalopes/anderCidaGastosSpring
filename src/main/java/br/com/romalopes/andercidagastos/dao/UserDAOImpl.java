package br.com.romalopes.andercidagastos.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.romalopes.andercidagastos.model.bean.User;

@Repository
public class UserDAOImpl extends AbstractDaoImpl<User, String> implements UserDAO { 

	 protected UserDAOImpl() {
	        super(User.class);
	 }

	@Override
	public User getUser(String email) {
		System.out.println("Entrou UserDAOImpl.getUser");
		List<User> userList = findByCriteria(Restrictions.like("userName", email, MatchMode.START));
		if(userList.size() > 0)
			return userList.get(0);
		
		return null;
	}
	
	@Override
	public List<User> getUsers() {
		System.out.println("Entrou UserDAOImpl.getUsers");
		getCurrentSession().beginTransaction();
		Query query = getCurrentSession().createQuery("from User");
		query.setCacheable(true);
		List l = query.list();
		getCurrentSession().getTransaction().commit();
		return l;
	}
	
	@Override
	public List<User> findUsers(String firstName) {
		System.out.println("Entrou UserDAOImpl.findUsers");
		return findByCriteria(Restrictions.like("firstName", firstName, MatchMode.START));
	}

	@Override
	public void saveUser(User user) {
		 saveOrUpdate(user);
		
	}
	
}
