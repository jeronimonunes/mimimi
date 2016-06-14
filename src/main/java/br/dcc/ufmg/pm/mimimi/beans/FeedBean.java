package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.FeedLazyList;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

@ManagedBean(name="feedBean")
@ViewScoped
public class FeedBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	private AbstractLazyList<Mimimi> mimimis;
	
	public FeedBean() {
		setMimimis(new FeedLazyList(getSessionBean(HeaderBean.class).getSelectedUser()));
	}
	
	public AbstractLazyList<Mimimi> getMimimis() {
		return mimimis;
	}

	public void setMimimis(AbstractLazyList<Mimimi> mimimis) {
		this.mimimis = mimimis;
	}
	
	
}
