package br.dcc.ufmg.pm.dao.jpa;

import br.dcc.ufmg.pm.model.Message;

public class JpaMessageDao extends AbstractJpaDao<Long, Message> {

	@Override
	public Class<Message> getEntityClass() {
		return Message.class;
	}

}
