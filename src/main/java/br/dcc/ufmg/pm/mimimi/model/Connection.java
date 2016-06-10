package br.dcc.ufmg.pm.mimimi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="connections")
@NamedQueries({
	@NamedQuery(name=Connection.COUNT_FOLLOWERS, query="select count(c) from Connection c where c.id.followed = :user"),
	@NamedQuery(name=Connection.COUNT_FOLLOWING, query="select count(c) from Connection c where c.id.follower = :user"),
	@NamedQuery(name=Connection.LIST_FOLLOWERS, query="select c from Connection c where c.id.followed = :user"),
	@NamedQuery(name=Connection.LIST_FOLLOWING, query="select c from Connection c where c.id.follower = :user")
})
public class Connection implements EntityInterface<ConnectionId> {
	
	private static final long serialVersionUID = 1L;
	
	public static final String COUNT_FOLLOWERS = "connection.count.followers";
	public static final String COUNT_FOLLOWING = "connection.count.following";
	public static final String LIST_FOLLOWERS = "connection.list.followers";
	public static final String LIST_FOLLOWING = "connection.list.following";

	@EmbeddedId
	private ConnectionId id;

	public ConnectionId getId() {
		return id;
	}

	public void setId(ConnectionId id) {
		this.id = id;
	}

}
