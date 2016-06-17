package br.dcc.ufmg.pm.mimimi.model;

import java.util.Date;

import javax.persistence.Column;
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
	@NamedQuery(name=Connection.LIST_USERS_FOLLOWERS, query="select c.id.follower from Connection c where c.id.followed = :user"),
	@NamedQuery(name=Connection.LIST_USERS_FOLLOWED, query="select c.id.followed from Connection c where c.id.follower = :user")
})
public class Connection implements EntityInterface<ConnectionId> {
	
	private static final long serialVersionUID = 1L;
	
	public static final String COUNT_FOLLOWERS = "connection.count.followers";
	public static final String COUNT_FOLLOWING = "connection.count.following";
	public static final String LIST_USERS_FOLLOWERS = "connection.list.followers";
	public static final String LIST_USERS_FOLLOWED = "connection.list.following";

	@EmbeddedId
	private ConnectionId id;
	
	@Column
	private Date date;
	
	public Connection() {
		
	}

	public Connection(ConnectionId id) {
		this.id = id;
		this.date = new Date();
	}

	public ConnectionId getId() {
		return id;
	}

	public void setId(ConnectionId id) {
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
		Connection other = (Connection) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
