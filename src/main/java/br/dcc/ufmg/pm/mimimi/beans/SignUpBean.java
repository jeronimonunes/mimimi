package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name = "signUpBean")
@ViewScoped
public class SignUpBean extends AbstractBean {

	private static final long serialVersionUID = 3L;

	private User user = new User();

	public String save() {
		UserDao dao = getDao(UserDao.class);
			try {
				dao.save(getUser());
				addMessage("Cadastro realizado com sucesso!");
				return "pretty:login";
			} catch (PersistenceException e) {
				addError("Não foi possível realizar o cadastro, usuário já existente.");
				e.printStackTrace();
				return null;
			} catch (Exception e) {
				addError("Não foi possível realizar o cadastro.");
				e.printStackTrace();
				return null;
			}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
