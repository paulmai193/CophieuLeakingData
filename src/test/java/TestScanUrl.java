import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import logia.cophieu.controller.listener.GetDataListener;
import logia.cophieu.model.GetUrlData;
import logia.httpclient.HttpSendGet;
import logia.httpclient.response.listener.HttpResponseListener;

public class TestScanUrl {

	public static void main(String[] args) throws UnsupportedOperationException, TimeoutException, IOException {
		String _requestURL = "http://www.cophieu68.vn/events.php";
		Map<String, String> _parameters = new HashMap<String, String>();
		_parameters.put("event_type", "1");
		_parameters.put("stockname", "ree");
		_parameters.put("search", "Tìm+Kiếm");

		HttpResponseListener<GetUrlData> _listener = new GetDataListener();
		HttpSendGet _get = new HttpSendGet(_requestURL, new HashMap<String, String>(0), _parameters, _listener);
		_get.execute();
	}
}
