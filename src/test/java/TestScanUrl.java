import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import logia.cophieu.controller.listener.GetShareDataListener;
import logia.cophieu.model.GetStockData;
import logia.httpclient.HttpSendGet;
import logia.httpclient.response.listener.HttpResponseListener;

/**
 * The Class TestScanUrl.
 *
 * @author Paul Mai
 */
public class TestScanUrl {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnsupportedOperationException the unsupported operation exception
	 * @throws TimeoutException the timeout exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws UnsupportedOperationException, TimeoutException, IOException {
		String _requestURL = "http://www.cophieu68.vn/events.php";
		Map<String, String> _parameters = new HashMap<String, String>();
		_parameters.put("event_type", "1");
		_parameters.put("stockname", "ree");
		_parameters.put("search", "Tìm+Kiếm");

		HttpResponseListener<GetStockData> _listener = new GetShareDataListener();
		HttpSendGet _get = new HttpSendGet(_requestURL, new HashMap<String, String>(0), _parameters, _listener);
		_get.execute();
	}
}
