package br.dcc.ufmg.pm.mimimi.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LikeId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="idUser", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="idMimimi", nullable = false)
	private Mimimi mimimi;
	
	public LikeId() {
		
	}

	public LikeId(User user, Mimimi mimimi) {
		this.user = user;
		this.mimimi = mimimi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mimimi == null) ? 0 : mimimi.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeId other = (LikeId) obj;
		if (mimimi == null) {
			if (other.mimimi != null)
				return false;
		} else if (!mimimi.equals(other.mimimi))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
