package br.com.romalopes.andercidagastos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.romalopes.andercidagastos.dao.UserDAO;
import br.com.romalopes.andercidagastos.model.bean.User;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public List<User> getUsers() {
		return userDAO.getUsers();
	}
	
	@Override
    @Transactional(readOnly = false)
	public void saveUser(User user) {
		userDAO.saveUser(user);
	}

	@Override
	@Transactional
	public User getUser(String email){
		System.out.println("Entrou UserServiceImpl.getUser");
		return userDAO.getUser(email);
	}
	
	@Override
	@Transactional
	public List<User> findUsers(String firstName) {
		System.out.println("Entrou UserServiceImpl.findUsers");
		return userDAO.findUsers(firstName);
	}

}