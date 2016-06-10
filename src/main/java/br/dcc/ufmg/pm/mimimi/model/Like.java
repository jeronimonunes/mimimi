package br.dcc.ufmg.pm.mimimi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="likes")
@NamedQueries({
	@NamedQuery(name=Like.COUNT_LIKES, query="select count(l) from Like l where l.id.user = :user"),
	@NamedQuery(name=Like.LIST_BY_USER, query="select l from Like l where l.id.user = :user")
})
public class Like implements EntityInterface<LikeId> {
	
	private static final long serialVersionUID = 1L;

	public static final String LIST_BY_USER = "like.list.by.user";

	public static final String COUNT_LIKES = "like.count";

	@EmbeddedId
	private LikeId id;

	public LikeId getId() {
		return id;
	}

	public void setId(LikeId id) {
		this.id = id;
	}

}
