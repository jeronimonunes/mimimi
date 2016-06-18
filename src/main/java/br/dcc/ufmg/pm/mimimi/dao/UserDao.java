package br.dcc.ufmg.pm.mimimi.dao;

import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link Dao} extended for {@link User}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public interface UserDao extends Dao<String,User> {
	
	/**
	 * The save User method will transform the password to an encrypted form
	 */
	@Override
	public User save(User t) throws DaoException;
	
	/**
	 * The update User method will transform the password to an encrypted form
	 */
	@Override
	public User update(User t) throws DaoException;

	/**
	 * Locates a user given username and unencrypted password
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
	
}
