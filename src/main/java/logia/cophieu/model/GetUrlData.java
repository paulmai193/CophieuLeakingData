package logia.cophieu.model;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

/**
 * The Class GetUrlData.
 *
 * @author Paul Mai
 */
public class GetUrlData {

	/** The ma ck. */
	private String                 maCk;

	/** The co tuc. */
	private SortedMap<Date, Float> coTuc;

	/**
	 * Instantiates a new gets the url data.
	 */
	public GetUrlData() {
		super();
	}

	/**
	 * Instantiates a new gets the url data.
	 *
	 * @param __maCk the __ma ck
	 * @param __coTuc the __co tuc
	 */
	public GetUrlData(String __maCk, SortedMap<Date, Float> __coTuc) {
		super();
		this.maCk = __maCk;
		this.coTuc = __coTuc;
	}

	/**
	 * Gets the ma ck.
	 *
	 * @return the maCk
	 */
	public String getMaCk() {
		return this.maCk;
	}

	/**
	 * Sets the ma ck.
	 *
	 * @param __maCk the maCk to set
	 */
	public void setMaCk(String __maCk) {
		this.maCk = __maCk;
	}

	/**
	 * Gets the co tuc.
	 *
	 * @return the coTuc
	 */
	public Map<Date, Float> getCoTuc() {
		return this.coTuc;
	}

	/**
	 * Sets the co tuc.
	 *
	 * @param __coTuc the coTuc to set
	 */
	public void setCoTuc(SortedMap<Date, Float> __coTuc) {
		this.coTuc = __coTuc;
	}

}
