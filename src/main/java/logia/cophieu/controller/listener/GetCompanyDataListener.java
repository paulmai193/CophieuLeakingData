package logia.cophieu.controller.listener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import logia.cophieu.model.GetStockData;
import logia.cophieu.model.database.DatabaseStockInfo;
import logia.httpclient.HttpUtility;
import logia.httpclient.response.listener.HttpResponseListener;

/**
 * The listener interface for receiving getCompanyData events. The class that is interested in
 * processing a getCompanyData event implements this
 * interface, and the object created with that class is registered with a component using the
 * component's
 * <code>addGetCompanyDataListener<code> method. When
 * the getCompanyData event occurs, that object's appropriate
 * method is invoked.
 *
 * @see GetCompanyDataEvent
 */
public class GetCompanyDataListener implements HttpResponseListener<GetStockData> {

	/** The Constant LOGGER. */
	private static final Logger	LOGGER = Logger.getLogger(GetCompanyDataListener.class);

	/** The data. */
	// private GetStockData data;

	private DatabaseStockInfo	data;

	/**
	 * Instantiates a new gets the company data listener.
	 */
	public GetCompanyDataListener() {
		super();
		this.data = new DatabaseStockInfo();
	}

	/**
	 * Instantiates a new gets the company data listener.
	 *
	 * @param __maCoPhieu
	 *            the __ma co phieu
	 */
	public GetCompanyDataListener(String __maCoPhieu) {
		super();
		this.data = new DatabaseStockInfo();
		this.data.setMaCoPhieu(__maCoPhieu);
	}

	/**
	 * Gets the comp name.
	 *
	 * @param __webContent
	 *            the __web content
	 * @return the comp name
	 */
	private String getCompName(String __webContent) {
		Matcher _matcher = Pattern.compile("<h1>(.*?)</h1>").matcher(__webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		return null;
	}

	/**
	 * Gets the current price.
	 *
	 * @param __webContent
	 *            the __web content
	 * @return the current price
	 */
	private String getCurrentPrice(String __webContent) {
		Matcher _matcher = Pattern
		        .compile("<strong class=\"pricedown\" id=\"stockname_close\">(.*?)</strong>")
		        .matcher(__webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		_matcher = Pattern
		        .compile("<strong class=\"priceup\" id=\"stockname_close\">(.*?)</strong>")
		        .matcher(__webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		_matcher = Pattern.compile("<strong class=\"price\" id=\"stockname_close\">(.*?)</strong>")
		        .matcher(__webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		_matcher = Pattern
		        .compile("<strong class=\"price_floor\" id=\"stockname_close\">(.*?)</strong>")
		        .matcher(__webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		_matcher = Pattern
		        .compile("<strong class=\"price_ceiling\" id=\"stockname_close\">(.*?)</strong>")
		        .matcher(__webContent);
		while (_matcher.find()) {
			return _matcher.group(1).trim();
		}
		return null;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public DatabaseStockInfo getData() {
		return this.data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see logia.httpclient.response.listener.HttpResponseListener#onResponse(logia.httpclient.
	 * HttpUtility)
	 */
	@Override
	public void onResponse(HttpUtility __httpUtility)
	        throws UnsupportedOperationException, IOException {
		String _html;
		try (InputStream _inputStream = __httpUtility.getHttpResponse().getEntity().getContent();) {
			_html = IOUtils.toString(_inputStream, StandardCharsets.UTF_8);
		}
		catch (UnsupportedOperationException | IOException e) {
			throw e;
		}
		_html = _html.replaceAll("/>\\s</", "><").replaceAll(">\\s</", "><").replaceAll("\t", "")
		        .replaceAll("\r\n", "");

		String _tenCty = this.getCompName(_html);
		float _curPrice = 0;
		String _strPrice = this.getCurrentPrice(_html);
		try {
			_curPrice = Float.parseFloat(_strPrice);
		}
		catch (Exception __e) {
			GetCompanyDataListener.LOGGER.error(
			        "Error parse string " + _strPrice + " of stock " + this.data.getMaCoPhieu(),
			        __e);
		}

		this.data.setTenCongTy(_tenCty);
		this.data.setGiaHienTai(_curPrice * 1000);

	}

}
