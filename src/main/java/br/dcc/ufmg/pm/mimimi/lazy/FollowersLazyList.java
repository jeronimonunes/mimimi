package br.dcc.ufmg.pm.mimimi.lazy;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaConnectionDao;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.User;

public class FollowersLazyList extends AbstractLazyList<Connection> {
	
	private static final long serialVersionUID = 1L;

	private ConnectionDao connectionDao;
	
	private User user;
	
	public FollowersLazyList(User user) {
		this.user = user;
		connectionDao = new JpaConnectionDao();
	}

	@Override
	protected int load(int first, int pageSize) {
		super.setWrappedData(connectionDao.listFollowersByUser(first,pageSize,user));
		return connectionDao.countFollowers(user).intValue();
	}

}