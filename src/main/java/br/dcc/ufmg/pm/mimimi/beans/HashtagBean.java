package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.HashtagLazyList;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

/**
 * {@link ManagedBean} to store data about the hashtag page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
@ViewScoped
@ManagedBean(name="hashtagBean")
public class HashtagBean extends AbstractBean {

	private static final long serialVersionUID = 2L;
	
	private AbstractLazyList<Mimimi> mimimis;
	
	public HashtagBean() {
		setMimimis(new HashtagLazyList(getExternalContext().getRequestParameterMap().get("hashtag")));
	}
	
	public AbstractLazyList<Mimimi> getMimimis() {
		return mimimis;
	}

	public void setMimimis(AbstractLazyList<Mimimi> mimimis) {
		this.mimimis = mimimis;
	}
	
	
}
