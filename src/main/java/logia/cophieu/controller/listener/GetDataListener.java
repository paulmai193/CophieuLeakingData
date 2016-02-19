package logia.cophieu.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logia.cophieu.model.GetUrlData;
import logia.httpclient.HttpUtility;
import logia.httpclient.response.listener.HttpResponseListener;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * The listener interface for receiving getData events. The class that is interested in processing a getData event implements this interface, and the
 * object created with that class is registered with a component using the component's <code>addGetDataListener<code> method. When
 * the getData event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GetDataEvent
 */
public class GetDataListener implements HttpResponseListener<GetUrlData> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GetDataListener.class);

	/** The data. */
	private GetUrlData          data;

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
		LOGGER.debug(Arrays.toString(_listElement.toArray()));

		GetUrlData _data = new GetUrlData();
		_data.setMaCk("REE");
		SortedMap<Date, Float> _coTuc = new TreeMap<Date, Float>(new Comparator<Date>() {

			@Override
			public int compare(Date __o1, Date __o2) {
				return __o1.compareTo(__o2);
			}

		});
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		for (String _element : _listElement) {
			// Read date

			try {
				Date _ngay = df.parse(readDate(_element));

				String _percentage = readShare(_element);
				Float _share = 10000 * (Float.parseFloat(_percentage.replace("%", "")) / 100);

				_coTuc.put(_ngay, _share);
			}
			catch (ParseException _e) {
				LOGGER.warn(_e.getMessage(), _e);
			}
		}
		_data.setCoTuc(_coTuc);
		LOGGER.debug(_data.getCoTuc());
	}

	/**
	 * Read row.
	 *
	 * @param __webContent the __web content
	 * @return the list
	 */
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

	/**
	 * Read date.
	 *
	 * @param __webContent the __web content
	 * @return the string
	 */
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

	/**
	 * Read share.
	 *
	 * @param __webContent the __web content
	 * @return the string
	 */
	private String readShare(String __webContent) {
		Matcher _matcher = Pattern.compile("<td class=\"td_bottom3 td_bg2\" align=\"right\" style=\" font-weight:bold\">(.*?)</td>").matcher(
		        __webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		return null;
	}
}
