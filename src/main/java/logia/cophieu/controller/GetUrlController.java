package logia.cophieu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;

import logia.cophieu.controller.listener.GetDataListener;
import logia.cophieu.model.GetUrlData;
import logia.httpclient.HttpSendGet;
import logia.httpclient.response.listener.HttpResponseListener;

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

	private String              url;
	private String              output;
	private JProgressBar        progressBar;
	private List<String>        stocks;

	public GetUrlController() {
	}

	public GetUrlController(String __url, String __output, JProgressBar __progressBar, List<String> __stocks) {
		super();
		this.numProcess = 0;
		this.url = __url;
		this.output = __output;
		this.progressBar = __progressBar;
		this.stocks = __stocks;
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

			try {
				Map<String, String> _parameters = new HashMap<String, String>();
				_parameters.put("event_type", "1");
				_parameters.put("search", "Tìm+Kiếm");

				for (String _eachStock : stocks) {
					_parameters.put("stockname", _eachStock);

					// Execute query
					HttpResponseListener<GetUrlData> _listener = new GetDataListener();
					HttpSendGet _get = new HttpSendGet(url, new HashMap<String, String>(0), _parameters, _listener);
					_get.execute();

					this.numProcess++;
					progressBar.setValue(this.numProcess);

					Thread.sleep(5000);
				}
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
