package br.dcc.ufmg.pm.mimimi.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

public class JpaMimimiDao extends AbstractJpaDao<Long, Mimimi> implements MimimiDao {
	
	protected JpaMimimiDao() {
		
	}

	@Override
	public Class<Mimimi> getEntityClass() {
		return Mimimi.class;
	}
	
	@Override
	public Long countMimimis(User user) {
		HashMap<String, Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findTypedResult(Mimimi.COUNT_MIMIMIS, params);
	}

	@Override
	public List<Mimimi> listMimimisByUser(int first, int size, User user) {
		Map<String,Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findListResult(Mimimi.LIST_BY_USER,params,first,size);
	}

	@Override
	public List<Mimimi> listFeed(int first, int size, User user) {
		Map<String,Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findListResult(Mimimi.LIST_FEED,params,first,size);
	}

	@Override
	public Long countFeed(User user) {
		HashMap<String, Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findTypedResult(Mimimi.COUNT_FEED, params);
	}

}
