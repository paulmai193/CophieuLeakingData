package logia.cophieu.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logia.cophieu.model.GetUrlData;
import logia.httpclient.HttpUtility;
import logia.httpclient.response.listener.HttpResponseListener;

import org.apache.commons.io.IOUtils;

/**
 * The listener interface for receiving getData events. The class that is interested in processing a getData event implements this interface, and the
 * object created with that class is registered with a component using the component's <code>addGetDataListener<code> method. When
 * the getData event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GetDataEvent
 */
public class GetDataListener implements HttpResponseListener<GetUrlData> {

	/** The data. */
	private GetUrlData data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public GetUrlData getData() {
		return this.data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.httpclient.response.listener.HttpResponseListener#onResponse(logia.httpclient.HttpUtility)
	 */
	@Override
	public void onResponse(HttpUtility __httpUtility) throws UnsupportedOperationException, IOException {
		String _html;
		try (InputStream _inputStream = __httpUtility.getHttpResponse().getEntity().getContent();) {
			_html = IOUtils.toString(_inputStream, StandardCharsets.UTF_8);
		}
		catch (UnsupportedOperationException | IOException e) {
			throw e;
		}
		_html = _html.replaceAll("/>\\s</", "><").replaceAll(">\\s</", "><").replaceAll("\t", "").replaceAll("\r\n", "");

		// TODO
		List<String> _listElement = readRow(_html);
		System.out.println(Arrays.toString(_listElement.toArray()));

		GetUrlData _data = new GetUrlData();
		_data.setMaCk("REE");
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (String _element : _listElement) {
			// Read date
			Map<Date, Float> _coTuc = new HashMap<Date, Float>();
			try {
				Date _ngay = df.parse(readDate(_element));

				String _percentage = readShare(_element);
				Float _share = 10000 * (Float.parseFloat(_percentage.replace("%", "")) / 100);

				_coTuc.put(_ngay, _share);
				System.out.println(_coTuc);
			}
			catch (ParseException _e) {
				_e.printStackTrace();
			}
		}
	}

	private List<String> readRow(String __webContent) {
		List<String> _contents = new ArrayList<String>();

		Matcher _matcher = Pattern.compile("<tr class=\"tr_row2\">(.*?)</tr>").matcher(__webContent);
		while (_matcher.find()) {
			String _tmpString = _matcher.group(1).trim();
			_contents.add(_tmpString);
		}

		_matcher = Pattern.compile("<tr class=\"tr_row1\">(.*?)</tr>").matcher(__webContent);
		while (_matcher.find()) {
			String _tmpString = _matcher.group(1).trim();
			_contents.add(_tmpString);
		}

		return _contents;
	}

	private String readDate(String __webContent) {
		Matcher _matcher = Pattern.compile("<td class=\"td_bottom3 td_bg1\" align=\"right\">(.*?)</td>").matcher(__webContent);
		int _count = 0;
		while (_matcher.find()) {
			_count++;
			if (_count == 2) {
				return _matcher.group(1).trim();
			}
		}
		return null;
	}

	private String readShare(String __webContent) {
		Matcher _matcher = Pattern.compile("<td class=\"td_bottom3 td_bg2\" align=\"right\" style=\" font-weight:bold\">(.*?)</td>").matcher(
		        __webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		return null;
	}
}
