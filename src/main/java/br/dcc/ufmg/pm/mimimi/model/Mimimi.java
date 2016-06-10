package br.dcc.ufmg.pm.mimimi.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="mimimis")
@NamedQueries({
	@NamedQuery(name=Mimimi.COUNT_MIMIMIS, query="select count(m) from Mimimi m where m.user = :user"),
	@NamedQuery(name=Mimimi.LIST_BY_USER, query="select m from Mimimi m where m.user = :user"),
	@NamedQuery(name=Mimimi.COUNT_FEED, query="select count(m) from Mimimi m where exists (select c from Connection c where c.id.followed = m.user and c.id.follower = :user) or (m.user = :user)"),
	@NamedQuery(name=Mimimi.LIST_FEED, query="select m from Mimimi m where exists (select c from Connection c where c.id.followed = m.user and c.id.follower = :user) or (m.user = :user)")
})
public class Mimimi implements EntityInterface<Long> {
	
	private static final long serialVersionUID = 1L;
	
	public static final String COUNT_MIMIMIS = "mimimi.count";

	public static final String LIST_BY_USER = "mimimi.list.by.user";

	public static final String LIST_FEED = "mimimi.list.feed";

	public static final String COUNT_FEED = "mimimi.count.feed";
	
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private String message;
	
	@ManyToOne
	@JoinColumn(name="idUser", nullable = false)
	private User user;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar date;
	
	@OneToMany(mappedBy = "id.mimimi", targetEntity = Like.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Like> likes;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
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
		Mimimi other = (Mimimi) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}