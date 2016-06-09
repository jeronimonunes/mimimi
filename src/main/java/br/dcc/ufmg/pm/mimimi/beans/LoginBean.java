package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaUserDao;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean extends AbstractBean {

	private static final long serialVersionUID = -6047623020631057290L;

	private User user;
	
	private String username;
	
	private String password;
	
	public String login() {
		user = new JpaUserDao().login(username,password);
		if(user==null){
			addError("Usuários ou senha inválidos");
			return null;
		} else {
			return "pretty:";
		}
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

}
