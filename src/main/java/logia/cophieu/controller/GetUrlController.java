package logia.cophieu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;

import logia.cophieu.controller.listener.GetCompanyDataListener;
import logia.cophieu.controller.listener.GetShareDataListener;
import logia.cophieu.model.DataInterface;
import logia.cophieu.report.form.implement.CophieuReport;
import logia.cophieu.report.form.implement.CotucSheet;
import logia.httpclient.HttpSendGet;

import org.apache.log4j.Logger;

/**
 * The Class GetUrlController.
 *
 * @author Paul Mai
 */
public final class GetUrlController {

	/** The Constant MAX. */
	public static final int     MAX    = 99;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GetUrlController.class);

	/** The _num process. */
	private int                 numProcess;

	private String              output;
	private JProgressBar        progressBar;
	private List<String>        stocks;
	private List<DataInterface> listDatas;

	public GetUrlController() {
	}

	public GetUrlController(String __output, JProgressBar __progressBar, List<String> __stocks) {
		super();
		this.numProcess = 0;
		this.output = __output;
		this.progressBar = __progressBar;
		this.stocks = __stocks;
		this.listDatas = new ArrayList<DataInterface>();
	}

	/**
	 * Scan url.
	 *
	 * @param __url the url
	 * @param __output the output file or folder
	 * @param __progressBar the progress bar
	 */
	public synchronized void scanUrl() {
		try {
			progressBar.setString("Scanning link, please do not turn off application!");

			try (CophieuReport _report = new CophieuReport(output)) {

				for (String _eachStock : stocks) {
					// Execute get share query
					String url = "http://www.cophieu68.vn/events.php";
					Map<String, String> _parameters = new HashMap<String, String>();
					_parameters.put("event_type", "1");
					_parameters.put("search", "Tìm+Kiếm");
					_parameters.put("stockname", _eachStock);
					GetShareDataListener _shareDataListener = new GetShareDataListener(_eachStock);
					HttpSendGet _get = new HttpSendGet(url, new HashMap<String, String>(0), _parameters, _shareDataListener);
					_get.execute();

					// Execute get company information query
					url = "http://www.cophieu68.vn/snapshot.php";
					_parameters.clear();
					_parameters.put("id", _eachStock);
					GetCompanyDataListener _companyDataListener = new GetCompanyDataListener(_shareDataListener.getData());
					_get = new HttpSendGet(url, new HashMap<String, String>(0), _parameters, _companyDataListener);
					_get.execute();

					listDatas.add(_shareDataListener.getData());

					this.numProcess++;
					progressBar.setValue(this.numProcess);

					Thread.sleep(1000);
				}

				// Export report
				_report.createData(this.listDatas, new CotucSheet(_report.getWorkbook(), "Cổ tức"));

				_report.exportReport();

			}
			catch (Exception _e) {
				throw _e;
			}
		}
		catch (Exception _e) {
			GetUrlController.LOGGER.error("Error when reading data from url", _e);
		}
		finally {
			progressBar.setString("Finish!");
			progressBar.setValue(0);
		}
	}
}
