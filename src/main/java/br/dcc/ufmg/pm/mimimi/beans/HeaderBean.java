package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.LikeDao;
import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name="headerBean")
@SessionScoped
public class HeaderBean extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private User selectedUser;

	private LikeDao likeDao;
	private ConnectionDao connectionDao;
	private MimimiDao mimimiDao;

	@ManagedProperty("#{loginBean}")
	private LoginBean loginBean;

	public HeaderBean() {
		this.connectionDao = getDao(ConnectionDao.class);
		this.mimimiDao = getDao(MimimiDao.class);
		this.likeDao = getDao(LikeDao.class);
		this.selectedUser = getSessionBean(LoginBean.class).getUser();
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

}
