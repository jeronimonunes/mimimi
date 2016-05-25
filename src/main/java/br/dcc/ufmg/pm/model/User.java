package br.dcc.ufmg.pm.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="users")
public class User implements EntityInterface<String> {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	
	@Column
	private String password;
	
	@Column
	private String description;
	
	@Lob
	@Column
	private byte[] picture;
	
	@Column
	private Calendar birthdate;
	
	@Column
	private Calendar membersince;
	
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
	
	@OneToMany(mappedBy = "user", targetEntity = Message.class, 
			   fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Message> messages;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public Calendar getMembersince() {
		return membersince;
	}

	public void setMembersince(Calendar membersince) {
		this.membersince = membersince;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
}
