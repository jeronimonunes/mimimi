package br.dcc.ufmg.pm.mimimi.dao.jpa;

import br.dcc.ufmg.pm.mimimi.dao.MessageDao;
import br.dcc.ufmg.pm.mimimi.model.Message;

public class JpaMessageDao extends AbstractJpaDao<Long, Message> implements MessageDao {

	@Override
	public Class<Message> getEntityClass() {
		return Message.class;
	}

}
