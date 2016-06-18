package br.dcc.ufmg.pm.mimimi.lazy;

import br.dcc.ufmg.pm.mimimi.dao.LikeDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link AbstractLazyList} to load the {@link User}'s likes page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public class LikesLazyList extends AbstractLazyList<Mimimi> {
	
	private static final long serialVersionUID = 1L;

	private LikeDao likeDao;
	
	private User user;
	
	public LikesLazyList(User user) {
		this.user = user;
		likeDao = getDao(LikeDao.class);
	}

	@Override
	protected int load(int first, int pageSize) {
		super.setWrappedData(likeDao.listLikedMimimisByUser(first,pageSize,user));
		return likeDao.countLikes(user).intValue();
	}

}
