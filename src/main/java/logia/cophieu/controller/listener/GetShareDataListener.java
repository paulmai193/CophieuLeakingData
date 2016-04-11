package logia.cophieu.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logia.cophieu.model.GetStockData;
import logia.cophieu.model.database.DatabaseShareData;
import logia.cophieu.model.database.DatabaseStockInfo;
import logia.httpclient.HttpUtility;
import logia.httpclient.response.listener.HttpResponseListener;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * The listener interface for receiving getShareData events. The class that is interested in processing a getShareData event implements this
 * interface, and the object created with that class is registered with a component using the component's
 * <code>addGetShareDataListener<code> method. When
 * the getShareData event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GetShareDataEvent
 */
public class GetShareDataListener implements HttpResponseListener<GetStockData> {

	/** The Constant LOGGER. */
	private static final Logger     LOGGER = Logger.getLogger(GetShareDataListener.class);

	/** The data. */
	// private GetStockData data;

	/** The share data. */
	// private DatabaseShareData shareData;

	private List<DatabaseShareData> listShareData;

	private DatabaseStockInfo       stockInfo;

	/** The stock. */
	// private String stock;

	/**
	 * Instantiates a new gets the share data listener.
	 */
	public GetShareDataListener() {
		super();
	}

	public GetShareDataListener(DatabaseStockInfo __stockInfo) {
		super();
		this.stockInfo = __stockInfo;
		this.listShareData = new ArrayList<DatabaseShareData>();
	}

	/**
	 * Instantiates a new gets the share data listener.
	 *
	 * @param __stock the __stock
	 */
	// public GetShareDataListener(String __stock) {
	// super();
	// // this.data = new GetStockData();
	// this.stock = __stock;
	// }

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	// public GetStockData getData() {
	// return this.data;
	// }

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

		List<String> _listElement = this.readRow(_html);
		GetShareDataListener.LOGGER.debug(Arrays.toString(_listElement.toArray()));

		// this.data.setMaCk(this.stock);
		SortedMap<Date, Float> _coTuc = new TreeMap<Date, Float>(new Comparator<Date>() {

			@Override
			public int compare(Date __o1, Date __o2) {
				return __o1.compareTo(__o2);
			}

		});
		DateFormat _df = new SimpleDateFormat("dd/MM/yyyy");
		for (String _element : _listElement) {
			// Read date

			try {
				Date _ngay = _df.parse(this.readDate(_element));

				String _percentage = this.readShare(_element);
				Float _share = 10000 * (Float.parseFloat(_percentage.replace("%", "")) / 100);

				_coTuc.put(_ngay, _share);
			}
			catch (ParseException _e) {
				GetShareDataListener.LOGGER.warn(_e.getMessage(), _e);
			}
		}
		// this.data.setCoTuc(_coTuc);
		// GetShareDataListener.LOGGER.debug(this.data.getCoTuc());

		// Process share of each year
		Map<Integer, Float> _shareMap = new HashMap<Integer, Float>();

		Calendar _calendar = Calendar.getInstance();
		// int _year = 0;
		// float _totalShare = 0;
		for (Entry<Date, Float> _eachShare : _coTuc.entrySet()) {
			_calendar.setTime(_eachShare.getKey());
			if (_eachShare.getValue() != null) {
				if (_shareMap.containsKey(_calendar.get(Calendar.YEAR))) {
					// _totalShare = _shareMap.get(_year) + _eachShare.getValue();
					// _totalShare += _eachShare.getValue();
					_shareMap.put(_calendar.get(Calendar.YEAR), _shareMap.get(_calendar.get(Calendar.YEAR)) + _eachShare.getValue());
				}
				else {
					// _year = _calendar.get(Calendar.YEAR);
					// _totalShare = _eachShare.getValue();
					_shareMap.put(_calendar.get(Calendar.YEAR), _eachShare.getValue());
				}
			}
		}

		for (Entry<Integer, Float> _eachShare : _shareMap.entrySet()) {
			DatabaseShareData _data = new DatabaseShareData(stockInfo, _eachShare.getKey(), _eachShare.getValue());
			this.listShareData.add(_data);
		}

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
	 * Gets the list share data.
	 *
	 * @return the shareData
	 */
	// public DatabaseShareData getShareData() {
	// return this.shareData;
	// }

	public List<DatabaseShareData> getListShareData() {
		return this.listShareData;
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
