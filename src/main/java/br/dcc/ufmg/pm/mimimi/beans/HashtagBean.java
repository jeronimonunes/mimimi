package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.HashtagLazyList;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

@ManagedBean(name="hashtagBean")
@ViewScoped
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
