package br.dcc.ufmg.pm.mimimi.lazy;

import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

/**
 * {@link AbstractLazyList} to load the hashtag page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public class HashtagLazyList extends AbstractLazyList<Mimimi> {
	
	private static final long serialVersionUID = 1L;

	private MimimiDao mimimiDao;
	
	private String hashtag;
	
	public HashtagLazyList(String hashtag) {
		this.hashtag = hashtag;
		mimimiDao = getDao(MimimiDao.class);
	}

	@Override
	protected int load(int first, int pageSize) {
		super.setWrappedData(mimimiDao.listMimimisByHashtag(first,pageSize,hashtag));
		return mimimiDao.countHashtag(hashtag).intValue();
	}

}
