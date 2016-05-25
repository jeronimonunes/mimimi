package br.dcc.ufmg.pm.mimimi.dao.jpa;

import br.dcc.ufmg.pm.mimimi.model.Message;

public class JpaMessageDao extends AbstractJpaDao<Long, Message> {

	@Override
	public Class<Message> getEntityClass() {
		return Message.class;
	}

}
