package br.com.romalopes.andercidagastos.dao;

import java.util.List;

import br.com.romalopes.andercidagastos.model.bean.User;

public interface UserDAO extends AbstractDao<User, String> {
    
    public List<User> getUsers();

	public User getUser(String email);

	public void saveUser(User user);
	
	public List<User> findUsers(String firstName) ;
	
}
