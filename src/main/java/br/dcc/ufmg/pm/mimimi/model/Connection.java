package br.dcc.ufmg.pm.mimimi.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="connections")
public class Connection implements EntityInterface<ConnectionId> {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ConnectionId id;

	public ConnectionId getId() {
		return id;
	}

	public void setId(ConnectionId id) {
		this.id = id;
	}

}
