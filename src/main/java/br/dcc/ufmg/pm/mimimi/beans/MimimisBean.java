package br.dcc.ufmg.pm.mimimi.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="mimimisBean")
@ViewScoped
public class MimimisBean extends AbstractBean {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{headerBean}")
	private HeaderBean headerBean;
	
	@PostConstruct
	public void init(){

	}
	
}
