package br.dcc.ufmg.pm.mimimi.lazy;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.User;

public class FollowingLazyList extends AbstractLazyList<Connection> {
	
	private static final long serialVersionUID = 1L;

	private ConnectionDao connectionDao;
	
	private User user;
	
	public FollowingLazyList(User user) {
		this.user = user;
		connectionDao = getDao(ConnectionDao.class);
	}

	@Override
	protected int load(int first, int pageSize) {
		super.setWrappedData(connectionDao.listFollowingByUser(first,pageSize,user));
		return connectionDao.countFollowing(user).intValue();
	}

}
