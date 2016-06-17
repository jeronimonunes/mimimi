package br.dcc.ufmg.pm.mimimi.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.FeedLazyList;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

/**
 * {@link ManagedBean} to store data about the feed page
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
@ViewScoped
@ManagedBean(name="feedBean")
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
