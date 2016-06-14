package br.dcc.ufmg.pm.mimimi.lazy;

import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

public class FeedLazyList extends AbstractLazyList<Mimimi> {
	
	private static final long serialVersionUID = 1L;

	private MimimiDao mimimiDao;
	
	private User user;
	
	public FeedLazyList(User user) {
		this.user = user;
		mimimiDao = getDao(MimimiDao.class);
	}

	@Override
	protected int load(int first, int pageSize) {
		super.setWrappedData(mimimiDao.listFeed(first,pageSize,user));
		return mimimiDao.countFeed(user).intValue();
	}

}
