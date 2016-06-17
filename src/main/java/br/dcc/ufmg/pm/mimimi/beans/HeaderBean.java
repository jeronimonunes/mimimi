package br.dcc.ufmg.pm.mimimi.beans;

import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.LikeDao;
import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link ManagedBean} to store data about which user is selected
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
@SessionScoped
@ManagedBean(name="headerBean")
public class HeaderBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeaderBean.class);

	/**
	 * The user currently selected
	 */
	private User selectedUser;

	public HeaderBean() {
		this.selectedUser = getSessionBean(LoginBean.class).getUser();
	}
	
	/**
	 * Method listener of an event called by Pretty Faces when a user {@link URL} is accessed
	 */
	public void selectUser() {
		String id = getExternalContext().getRequestParameterMap().get("user");
		if(id!=null){
			try {
				User user = getDao(UserDao.class).find(id);
				if(user!=null) setSelectedUser(user);
			} catch (DaoException e) {
				addError("Não foi possível localizar o usuário solicitado");
				LOGGER.error("Some DaoException occurred",e);
			}
		}
	}
	
	/**
	 * Method listener of an event that makes the current logged user follow the selected user
	 * Or unfollow if the {@link Connection} already exists
	 */
	public void follow() {
		try {
			UserDao userDao = getDao(UserDao.class);
			User follower = userDao.find(getSessionBean(LoginBean.class).getUser().getId());
			User followed = userDao.find(getSelectedUser().getId());
			ConnectionDao connectionDao = getDao(ConnectionDao.class);
			ConnectionId id = new ConnectionId(follower,followed);
			Connection connection = connectionDao.find(id);
			if(connection==null){
				connectionDao.save(new Connection(id));
			} else {
				connectionDao.delete(connection);
			}
		} catch (Exception e) {
			LOGGER.error("It was not possible to save/delete Connection",e);
		}
	}
	
	/**
	 * 
	 * @return true if the selected user is followed by the currently logged user, false otherwise
	 */
	public boolean isFollowed() {
		try {
			LoginBean loginBean = getSessionBean(LoginBean.class);
			UserDao userDao = getDao(UserDao.class);
			User follower = userDao.find(loginBean.getUser().getId());
			User followed = userDao.find(getSelectedUser().getId());
			Connection connection = getDao(ConnectionDao.class).find(new ConnectionId(follower,followed));
			return connection!=null;
		} catch (DaoException e) {
			LOGGER.error("It was not possible to query Connections",e);
			return false;
		}
	}

	/**
	 * 
	 * @return The count number of mimimis of the selected user
	 */
	public Long getMimimis(){
		return getDao(MimimiDao.class).countMimimis(selectedUser);
	}

	/**
	 * 
	 * @return The count number of followers of the selected user
	 */
	public Long getFollowers(){
		return getDao(ConnectionDao.class).countFollowers(selectedUser);
	}

	/**
	 * 
	 * @return The count number of followeds of the selected user
	 */
	public Long getFollowing(){
		return getDao(ConnectionDao.class).countFollowing(selectedUser);
	}

	/**
	 * 
	 * @return The count number of likes of the selected user
	 */
	public Long getLikes(){
		return getDao(LikeDao.class).countLikes(selectedUser);
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
