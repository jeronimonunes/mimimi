package br.dcc.ufmg.pm.mimimi.beans;

import java.io.ByteArrayInputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaMimimiDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean extends AbstractBean {

	private static final long serialVersionUID = 4L;

	private User user;
	
	private String mimimiMsg;
	
	private String username;
	
	private String password;
	
	private String searchQuery;
	
	public void sendMimimi(){
		MimimiDao dao = getDao(MimimiDao.class);
			try {
				Mimimi mimimi = new Mimimi(getMimimiMsg(),getUser());
				dao.save(mimimi);
			
			} catch (Exception e){
				addError("Não foi possível Mimimizar.");
				e.printStackTrace();
			}
	}
	
	public String login() {
		user = getDao(UserDao.class).login(username,password);
		if(user==null){
			addError("Usuários ou senha inválidos");
			return null;
		} else {
			return "pretty:feed";
		}
	}
	
	public StreamedContent getPicture() {
		return new DefaultStreamedContent(new ByteArrayInputStream(user.getPicture()));
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public String getMimimiMsg() {
		return mimimiMsg;
	}

	public void setMimimiMsg(String mimimiMsg) {
		this.mimimiMsg = mimimiMsg;
	}
}
