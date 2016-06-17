package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.FollowersLazyList;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link ManagedBean} to store data about the followers page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
@ViewScoped
@ManagedBean(name="followersBean")
public class FollowersBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FollowersBean.class);
	
	private AbstractLazyList<User> followers;
	
	public FollowersBean() {
		setFollowers(new FollowersLazyList(getSessionBean(HeaderBean.class).getSelectedUser()));
	}

	public AbstractLazyList<User> getFollowers() {
		return followers;
	}

	public void setFollowers(AbstractLazyList<User> following) {
		this.followers = following;
	}
	
	/**
	 * Finds out if the logged user follows the given user
	 * @param user The user to lookup for
	 * @return true if the logged user follow the given user, false otherwise
	 */
	public boolean isFollowed(User user){
		try {
			LoginBean loginBean = getSessionBean(LoginBean.class);
			UserDao userDao = getDao(UserDao.class);
			ConnectionDao connectionDao = getDao(ConnectionDao.class);
			User follower;
			follower = userDao.find(loginBean.getUser().getId());
			User followed = userDao.find(user.getId());
			ConnectionId id = new ConnectionId(follower,followed);
			Connection con = connectionDao.find(id);
			return con!=null;
		} catch (DaoException e) {
			LOGGER.error("An error happened while trying to discover if an user is followed",e);
			return false;
		}
	}
	
}
