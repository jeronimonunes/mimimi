package br.dcc.ufmg.pm.mimimi.beans;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.application.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.dcc.ufmg.pm.mimimi.lazy.MimimisLazyList;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;

@ManagedBean(name="mimimisBean")
@ViewScoped
public class MimimisBean extends AbstractBean {

	private static final long serialVersionUID = 2L;
	
	private MimimisLazyList mimimis;
	
	public MimimisBean() {
		setMimimis(new MimimisLazyList(getSessionBean(HeaderBean.class).getSelectedUser()));
		
	}
	
	public StreamedContent profilePicture(Mimimi mimimi) {
		try {
			if(mimimi.getUser().getPicture()==null){
				Resource resource = getResourceHandler().createResource("user.png","pictures");
				return new DefaultStreamedContent(resource.getInputStream(),resource.getContentType());
			} else {
				return new DefaultStreamedContent(new ByteArrayInputStream(mimimi.getUser().getPicture()));
			}
		} catch (IOException e) {
			return null;
		}
	}

	public MimimisLazyList getMimimis() {
		return mimimis;
	}

	public void setMimimis(MimimisLazyList mimimis) {
		this.mimimis = mimimis;
	}
	
	
}
