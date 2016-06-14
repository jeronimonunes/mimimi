package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.MimimisLazyList;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

@ManagedBean(name="mimimisBean")
@ViewScoped
public class MimimisBean extends AbstractBean {

	private static final long serialVersionUID = 2L;
	
	private AbstractLazyList<Mimimi> mimimis;
	
	public MimimisBean() {
		setMimimis(new MimimisLazyList(getSessionBean(HeaderBean.class).getSelectedUser()));
	}
	
	public AbstractLazyList<Mimimi> getMimimis() {
		return mimimis;
	}

	public void setMimimis(AbstractLazyList<Mimimi> mimimis) {
		this.mimimis = mimimis;
	}
	
	
}
