package br.dcc.ufmg.pm.mimimi.dao;

import java.util.List;

import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

public interface MimimiDao extends Dao<Long,Mimimi> {
	
	public Long countMimimis(User user);

	public List<Mimimi> listMimimisByUser(int first, int pageSize, User user);

	public List<Mimimi> listFeed(int first, int pageSize, User user);

	public Long countFeed(User user);

}
