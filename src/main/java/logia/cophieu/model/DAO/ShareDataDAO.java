package logia.cophieu.model.DAO;

import logia.cophieu.model.database.DatabaseShareData;
import logia.hibernate.dao.AbstractDAO;

public class ShareDataDAO extends AbstractDAO<DatabaseShareData, Long> {

	public ShareDataDAO() {
	}

	@Override
	protected Class<DatabaseShareData> getPOJOClass() {
		return DatabaseShareData.class;
	}

}
