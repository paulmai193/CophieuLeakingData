package logia.cophieu.model.DAO;

import logia.cophieu.model.database.DatabaseStockInfo;
import logia.hibernate.dao.AbstractDAO;

/**
 * The Class StockInfoDAO.
 *
 * @author Paul Mai
 */
public class StockInfoDAO extends AbstractDAO<DatabaseStockInfo, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.hibernate.dao.AbstractDAO#getPOJOClass()
	 */
	@Override
	protected Class<DatabaseStockInfo> getPOJOClass() {
		return DatabaseStockInfo.class;
	}

	/**
	 * Select by ma co phieu.
	 *
	 * @param __maCoPhieu the __ma co phieu
	 * @return the database stock info
	 */
	public DatabaseStockInfo selectByMaCoPhieu(String __maCoPhieu) {
		String queryString = "from DatabaseStockInfo where maCoPhieu = '" + __maCoPhieu + "'";
		return selectUniqueByQuery(queryString);
	}

}
