package logia.cophieu.model;

import java.util.SortedMap;

/**
 * The Class GetStockData.
 *
 * @author Paul Mai
 */
public class GetStockData implements DataInterface {

	/** The co tuc. {year, share} */
	private SortedMap<Integer, Float> coTuc;

	/** The gia hien tai. */
	private float					  giaHienTai;

	/** The ma ck. */
	private String					  maCk;

	/** The ten cty. */
	private String					  tenCty;

	/**
	 * Instantiates a new gets the stock data.
	 */
	public GetStockData() {
		super();
	}

	/**
	 * Instantiates a new gets the stock data.
	 *
	 * @param __maCk
	 *            the __ma ck
	 * @param __coTuc
	 *            the __co tuc
	 */
	public GetStockData(String __maCk, SortedMap<Integer, Float> __coTuc) {
		super();
		this.maCk = __maCk;
		this.coTuc = __coTuc;
	}

	/**
	 * Gets the co tuc.
	 *
	 * @return the coTuc
	 */
	public SortedMap<Integer, Float> getCoTuc() {
		return this.coTuc;
	}

	/**
	 * Gets the gia hien tai.
	 *
	 * @return the giaHienTai
	 */
	public float getGiaHienTai() {
		return this.giaHienTai;
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
	 * Gets the ten cty.
	 *
	 * @return the tenCty
	 */
	public String getTenCty() {
		return this.tenCty;
	}

	/**
	 * Sets the co tuc.
	 *
	 * @param __coTuc
	 *            the coTuc to set
	 */
	public void setCoTuc(SortedMap<Integer, Float> __coTuc) {
		this.coTuc = __coTuc;
	}

	/**
	 * Sets the gia hien tai.
	 *
	 * @param __giaHienTai
	 *            the giaHienTai to set
	 */
	public void setGiaHienTai(float __giaHienTai) {
		this.giaHienTai = __giaHienTai;
	}

	/**
	 * Sets the ma ck.
	 *
	 * @param __maCk
	 *            the maCk to set
	 */
	public void setMaCk(String __maCk) {
		this.maCk = __maCk;
	}

	/**
	 * Sets the ten cty.
	 *
	 * @param __tenCty
	 *            the tenCty to set
	 */
	public void setTenCty(String __tenCty) {
		this.tenCty = __tenCty;
	}

}
