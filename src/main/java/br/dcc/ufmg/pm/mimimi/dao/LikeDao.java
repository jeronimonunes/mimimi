package br.dcc.ufmg.pm.mimimi.dao;

import java.util.List;

import br.dcc.ufmg.pm.mimimi.model.Like;
import br.dcc.ufmg.pm.mimimi.model.LikeId;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link Dao} extended for {@link Like}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public interface LikeDao extends Dao<LikeId,Like> {
	
	public Long countLikes(User user);

	List<Like> listLikesByUser(int first, int size, User user);
	
	List<Mimimi> listLikedMimimisByUser(int first, int size, User user);

}
