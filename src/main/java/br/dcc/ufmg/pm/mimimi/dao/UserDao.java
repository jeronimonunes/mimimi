package br.dcc.ufmg.pm.mimimi.dao;

import br.dcc.ufmg.pm.mimimi.model.User;

public interface UserDao extends Dao<String,User> {

	public User login(String username, String password);

}
