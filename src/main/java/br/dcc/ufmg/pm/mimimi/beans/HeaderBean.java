package br.dcc.ufmg.pm.mimimi.beans;

import java.io.ByteArrayInputStream;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
	
	@PostConstruct
	public void init(){
		selectedUser = getLoginBean().getUser();
		connectionDao = getDao(ConnectionDao.class);
		mimimiDao = getDao(MimimiDao.class);
		likeDao = getDao(LikeDao.class);
	}
	
	public StreamedContent getCover() {
		return new DefaultStreamedContent(new ByteArrayInputStream(selectedUser.getCover()));
	}
	
	public StreamedContent getPicture() {
		return new DefaultStreamedContent(new ByteArrayInputStream(selectedUser.getPicture()));
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

}
