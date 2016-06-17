package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.FollowingLazyList;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link ManagedBean} to store data about the following page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
@ViewScoped
@ManagedBean(name="followingBean")
public class FollowingBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private AbstractLazyList<User> following;
	
	public FollowingBean() {
		setFollowing(new FollowingLazyList(getSessionBean(HeaderBean.class).getSelectedUser()));
	}

	public AbstractLazyList<User> getFollowing() {
		return following;
	}

	public void setFollowing(AbstractLazyList<User> following) {
		this.following = following;
	}
	
}
