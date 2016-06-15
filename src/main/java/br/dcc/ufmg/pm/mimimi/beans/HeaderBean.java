package br.dcc.ufmg.pm.mimimi.beans;

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

@ManagedBean(name="headerBean")
@SessionScoped
public class HeaderBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HeaderBean.class);

	private User selectedUser;

	private LikeDao likeDao;
	private ConnectionDao connectionDao;
	private MimimiDao mimimiDao;

	public HeaderBean() {
		this.connectionDao = getDao(ConnectionDao.class);
		this.mimimiDao = getDao(MimimiDao.class);
		this.likeDao = getDao(LikeDao.class);
		this.selectedUser = getSessionBean(LoginBean.class).getUser();
	}
	
	public void selectUser() {
		String id = getExternalContext().getRequestParameterMap().get("user");
		if(id!=null){
			User user = getEntityManager().find(User.class, id);
			if(user!=null) setSelectedUser(user);
		}
	}
	
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

	public Long getMimimis(){
		return mimimiDao.countMimimis(selectedUser);
	}

	public Long getFollowers(){
		return connectionDao.countFollowers(selectedUser);
	}

	public Long getFollowing(){
		return connectionDao.countFollowing(selectedUser);
	}

	public Long getLikes(){
		return likeDao.countLikes(selectedUser);
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
