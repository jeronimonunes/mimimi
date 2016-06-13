package br.dcc.ufmg.pm.mimimi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="users")
@NamedQueries({
	@NamedQuery(name=User.LOGIN, query="select u from User u where u.username = :username and u.password = :password")
})
public class User implements EntityInterface<String> {
	
	private static final long serialVersionUID = 1L;
	
	public static final String LOGIN = "user.login";

	@Id
	private String username;
	
	@Column
	private String name;
	
	@Column
	private String password;
	
	@Column
	private String description;
	
	@Column
	private Date birthdate;
	
	@Column
	private Date membersince;
	
	@Column
	private String city;
	
	@OneToMany(mappedBy = "id.followed", targetEntity = Connection.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Connection> followers;
	
	@OneToMany(mappedBy = "id.follower", targetEntity = Connection.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Connection> following;
	
	@OneToMany(mappedBy = "id.user", targetEntity = Like.class,
			fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Like> likes;
	
	@OneToMany(mappedBy = "user", targetEntity = Mimimi.class, 
			   fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Mimimi> mimimis;
	
	@Override
	public String getId() {
		return getUsername();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String login) {
		this.username = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Connection> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Connection> followers) {
		this.followers = followers;
	}

	public List<Connection> getFollowing() {
		return following;
	}

	public void setFollowing(List<Connection> following) {
		this.following = following;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getMembersince() {
		return membersince;
	}

	public void setMembersince(Date membersince) {
		this.membersince = membersince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Mimimi> getMimimis() {
		return mimimis;
	}

	public void setMimimis(List<Mimimi> mimimis) {
		this.mimimis = mimimis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

}
