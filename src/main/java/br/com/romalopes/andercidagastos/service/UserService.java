package br.com.romalopes.andercidagastos.service;

import java.util.List;

import br.com.romalopes.andercidagastos.model.bean.User;

public interface UserService {

	public List<User> getUsers();
	public User getUser(String email);
	void saveUser(User user);
	List<User> findUsers(String firstName);

}
