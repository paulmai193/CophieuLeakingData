import logia.cophieu.model.DAO.StockInfoDAO;
import logia.cophieu.model.database.DatabaseStockInfo;
import logia.hibernate.util.HibernateUtil;

public class TestDatabase {

	public static void main(String[] args) {
		HibernateUtil.setConfigPath("hibernate.cfg.xml");
		DatabaseStockInfo _stockInfo = new StockInfoDAO().selectByMaCoPhieu("KDC");
		System.out.println(_stockInfo);

	}

}
