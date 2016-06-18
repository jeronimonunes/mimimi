package br.dcc.ufmg.pm.mimimi.dao;

import java.util.List;

import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

/**
 * {@link Dao} extended for {@link Mimimi}
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public interface MimimiDao extends Dao<Long,Mimimi> {
	
	public Long countMimimis(User user);

	public List<Mimimi> listMimimisByUser(int first, int size, User user);

	public List<Mimimi> listFeed(int first, int size, User user);

	public Long countFeed(User user);
	
	public List<Mimimi> listMimimisByHashtag(int first, int size, String hashtag);
	
	public Long countHashtag(String hashtag);

}
