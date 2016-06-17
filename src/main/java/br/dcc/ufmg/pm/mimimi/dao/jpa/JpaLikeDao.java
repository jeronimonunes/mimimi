package br.dcc.ufmg.pm.mimimi.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.dcc.ufmg.pm.mimimi.dao.LikeDao;
import br.dcc.ufmg.pm.mimimi.model.Like;
import br.dcc.ufmg.pm.mimimi.model.LikeId;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

public class JpaLikeDao extends AbstractJpaDao<LikeId, Like> implements LikeDao {
	
	protected JpaLikeDao() {
		
	}

	@Override
	public Class<Like> getEntityClass() {
		return Like.class;
	}
	
	@Override
	public Long countLikes(User user) {
		HashMap<String, Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findSingleResult(Like.COUNT_LIKES, params);
	}

	@Override
	public List<Like> listLikesByUser(int first, int size, User user) {
		Map<String,Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findListResult(Like.LIST_BY_USER,params,first,size);
	}

	@Override
	public List<Mimimi> listLikedMimimisByUser(int first, int size, User user) {
		Map<String,Object> params = new HashMap<>(1);
		params.put("user", user);
		return super.findListResult(Like.LIST_MIMIMIS_BY_USER,params,first,size);
	}

}
