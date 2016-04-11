package logia.cophieu.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import logia.cophieu.controller.listener.GetCompanyDataListener;
import logia.cophieu.controller.listener.GetShareDataListener;
import logia.cophieu.model.DAO.ShareDataDAO;
import logia.cophieu.model.DAO.StockInfoDAO;
import logia.cophieu.model.database.DatabaseShareData;
import logia.cophieu.model.database.DatabaseStockInfo;
import logia.hibernate.util.HibernateUtil;
import logia.httpclient.HttpSendGet;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * The Class GetUrlController.
 *
 * @author Paul Mai
 */
public final class GetUrlController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GetUrlController.class);

	/** The list datas. */
	// private List<DataInterface> listDatas;

	// /** The _num process. */
	// private int numProcess;
	//
	// /** The output. */
	// private String output;

	/** The progress bar. */
	// private JProgressBar progressBar;

	/** The stocks. */
	private List<String>        stocks;

	/**
	 * Instantiates a new gets the url controller.
	 */
	public GetUrlController() {
	}

	/**
	 * Instantiates a new gets the url controller.
	 *
	 * @param __output the __output
	 * @param __progressBar the __progress bar
	 * @param __stocks the __stocks
	 */
	// public GetUrlController(String __output, JProgressBar __progressBar, List<String> __stocks) {
	// super();
	// // this.numProcess = 0;
	// // this.output = __output;
	// // this.progressBar = __progressBar;
	// this.stocks = __stocks;
	// // this.listDatas = new ArrayList<DataInterface>();
	// }

	/**
	 * Scan url.
	 */
	// public synchronized void scanUrl() {
	// try {
	// // this.progressBar.setString("Scanning link 0%");
	//
	// for (String _eachStock : this.stocks) {
	// // Execute get share query
	// String _url = "http://www.cophieu68.vn/events.php";
	// Map<String, String> _parameters = new HashMap<String, String>();
	// _parameters.put("event_type", "1");
	// _parameters.put("search", "Tìm+Kiếm");
	// _parameters.put("stockname", _eachStock);
	// GetShareDataListener _shareDataListener = new GetShareDataListener(_eachStock);
	// HttpSendGet _get = new HttpSendGet(_url, new HashMap<String, String>(0), _parameters, _shareDataListener);
	// _get.execute();
	//
	// // Execute get company information query
	// _url = "http://www.cophieu68.vn/snapshot.php";
	// _parameters.clear();
	// _parameters.put("id", _eachStock);
	// GetCompanyDataListener _companyDataListener = new GetCompanyDataListener(_shareDataListener.getData());
	// _get = new HttpSendGet(_url, new HashMap<String, String>(0), _parameters, _companyDataListener);
	// _get.execute();
	//
	// // this.listDatas.add(_shareDataListener.getData());
	//
	// this.numProcess++;
	// // this.progressBar.setString("Scanning link " + (this.numProcess * 100 / this.stocks.size()) + "%");
	// // this.progressBar.setValue(this.numProcess);
	//
	// Thread.sleep(500);
	// }
	// // try (CophieuReport _report = new CophieuReport(this.output)) {
	// // // Export report
	// // this.progressBar.setString("Export report file 0%");
	// // _report.createData(this.listDatas, new CotucSheet(_report.getWorkbook(), "Cổ tức"), this.progressBar);
	// //
	// // _report.exportReport();
	// //
	// // }
	// // catch (Exception _e) {
	// // throw _e;
	// // }
	// }
	// catch (Exception _e) {
	// GetUrlController.LOGGER.error("Error when reading data from url", _e);
	// }
	// finally {
	// // this.progressBar.setString("Finish!");
	// // this.progressBar.setValue(0);
	// }
	// }

	/**
	 * Scan stock info.
	 *
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws TimeoutException the timeout exception
	 */
	public synchronized void scanStockInfo() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException,
	        UnsupportedOperationException, TimeoutException {
		String _url = "http://www.cophieu68.vn/snapshot.php";
		Map<String, String> _parameters = new HashMap<String, String>(1);
		for (String _eachStock : this.stocks) {
			// Execute get company information query
			_parameters.put("id", _eachStock);
			GetCompanyDataListener _companyDataListener = new GetCompanyDataListener(_eachStock);
			HttpSendGet _get = new HttpSendGet(_url, new HashMap<String, String>(0), _parameters, _companyDataListener);
			_get.execute();
		}
	}

	/**
	 * Scan data.
	 *
	 * @param __stocks the __stocks
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws TimeoutException the timeout exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void scanData(List<String> __stocks) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException,
	        UnsupportedOperationException, TimeoutException, InterruptedException {
		String _url = "http://www.cophieu68.vn/events.php";
		Map<String, String> _parameters = new HashMap<String, String>();
		_parameters.put("event_type", "1");
		_parameters.put("search", "Tìm+Kiếm");
		StockInfoDAO _dao = new StockInfoDAO();
		List<DatabaseShareData> _listData = new ArrayList<DatabaseShareData>();
		for (String _eachStock : __stocks) {
			_parameters.put("stockname", _eachStock);
			DatabaseStockInfo _stockInfo = _dao.selectByMaCoPhieu(_eachStock);
			if (_stockInfo != null) {
				GetShareDataListener _shareDataListener = new GetShareDataListener(_stockInfo);
				HttpSendGet _get = new HttpSendGet(_url, new HashMap<String, String>(0), _parameters, _shareDataListener);
				_get.execute();

				_listData.addAll(_shareDataListener.getListShareData());

				System.out.println(_eachStock);
			}

			Thread.sleep(5000);
		}

		Session _session = HibernateUtil.beginTransaction();
		try {
			new ShareDataDAO().saveOrUpdate(_session, _listData);
			HibernateUtil.commitTransaction(_session);
		}
		catch (HibernateException _ex) {
			HibernateUtil.rollbackTransaction(_session);
			throw _ex;
		}
		finally {
			HibernateUtil.closeSession(_session);
		}
	}

	public static void exportData() {
		// TODO Auto-generated method stub

	}

	/**
	 * Inits the data.
	 *
	 * @param __stocks the __stocks
	 * @throws KeyManagementException the key management exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws KeyStoreException the key store exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws TimeoutException the timeout exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void initData(List<String> __stocks) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException,
	        UnsupportedOperationException, TimeoutException, InterruptedException {
		String _url = "http://www.cophieu68.vn/snapshot.php";
		Map<String, String> _parameters = new HashMap<String, String>(1);
		List<DatabaseStockInfo> _listInfoData = new ArrayList<DatabaseStockInfo>();
		for (String _eachStock : __stocks) {
			// Execute get company information query
			_parameters.put("id", _eachStock);
			GetCompanyDataListener _companyDataListener = new GetCompanyDataListener(_eachStock);
			HttpSendGet _get = new HttpSendGet(_url, new HashMap<String, String>(0), _parameters, _companyDataListener);
			_get.execute();

			_listInfoData.add(_companyDataListener.getData());

			System.out.println(_eachStock);

			Thread.sleep(5000);
		}

		Session _session = HibernateUtil.beginTransaction();
		try {
			new StockInfoDAO().saveOrUpdate(_session, _listInfoData);
			HibernateUtil.commitTransaction(_session);
		}
		catch (HibernateException _ex) {
			HibernateUtil.rollbackTransaction(_session);
			throw _ex;
		}
		finally {
			HibernateUtil.closeSession(_session);
		}
	}
}
