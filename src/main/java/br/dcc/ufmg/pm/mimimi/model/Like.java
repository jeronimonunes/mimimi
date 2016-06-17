package br.dcc.ufmg.pm.mimimi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="likes")
@NamedQueries({
	@NamedQuery(name=Like.COUNT_LIKES, query="select count(l) from Like l where l.id.user = :user"),
	@NamedQuery(name=Like.LIST_BY_USER, query="select l from Like l where l.id.user = :user"),
	@NamedQuery(name=Like.LIST_MIMIMIS_BY_USER, query="select l.id.mimimi from Like l where l.id.user = :user")
})
public class Like implements EntityInterface<LikeId> {

	private static final long serialVersionUID = 1L;

	public static final String LIST_BY_USER = "like.list.by.user";
	
	public static final String LIST_MIMIMIS_BY_USER = "like.list.mimimis.by.user";

	public static final String COUNT_LIKES = "like.count";

	public Like() {

	}

	public Like(LikeId id) {
		setId(id);
		this.date = new Date();
	}

	@EmbeddedId
	private LikeId id;
	
	@Column
	private Date date;

	public LikeId getId() {
		return id;
	}

	public void setId(LikeId id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Like other = (Like) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
