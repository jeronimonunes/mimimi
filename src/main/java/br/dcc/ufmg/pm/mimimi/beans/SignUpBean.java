package br.dcc.ufmg.pm.mimimi.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name="signUpBean")
@ViewScoped
public class SignUpBean extends AbstractBean {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	
	
	public void save() {
		UserDao dao = getDao(UserDao.class);
		try {
			dao.save(getUser());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@PostConstruct
	private void init(){
		user = new User();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
