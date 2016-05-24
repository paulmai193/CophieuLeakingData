package logia.cophieu.model.database;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The Class DatabaseStockInfo.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "stock_info")
@DynamicInsert
@DynamicUpdate
public class DatabaseStockInfo {

	/** The gia hien tai. */
	@Column(name = "gia_hien_tai", nullable = false)
	private Float				   giaHienTai;

	/** The ma. */
	@Id
	@Column(length = 10, name = "ma_co_phieu", unique = true, nullable = false)
	private String				   maCoPhieu;

	/** The shares. */
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DatabaseShareData> shares;

	/** The ten cong ty. */
	@Column(length = 1000, name = "ten_cong_ty", nullable = false)
	private String				   tenCongTy;

	/**
	 * Instantiates a new database stock info.
	 */
	public DatabaseStockInfo() {
		super();
	}

	/**
	 * Instantiates a new database stock info.
	 *
	 * @param __maCoPhieu
	 *            the __ma co phieu
	 * @param __tenCongTy
	 *            the __ten cong ty
	 * @param __giaHienTai
	 *            the __gia hien tai
	 */
	public DatabaseStockInfo(String __maCoPhieu, String __tenCongTy, Float __giaHienTai) {
		super();
		this.maCoPhieu = __maCoPhieu;
		this.tenCongTy = __tenCongTy;
		this.giaHienTai = __giaHienTai;
		this.shares = new HashSet<DatabaseShareData>();
	}

	/**
	 * Gets the gia hien tai.
	 *
	 * @return the giaHienTai
	 */
	public Float getGiaHienTai() {
		return this.giaHienTai;
	}

	/**
	 * Gets the ma co phieu.
	 *
	 * @return the maCoPhieu
	 */
	public String getMaCoPhieu() {
		return this.maCoPhieu;
	}

	/**
	 * Gets the shares.
	 *
	 * @return the shares
	 */
	public Set<DatabaseShareData> getShares() {
		return this.shares;
	}

	/**
	 * Gets the ten cong ty.
	 *
	 * @return the tenCongTy
	 */
	public String getTenCongTy() {
		return this.tenCongTy;
	}

	/**
	 * Sets the gia hien tai.
	 *
	 * @param __giaHienTai
	 *            the giaHienTai to set
	 */
	public void setGiaHienTai(Float __giaHienTai) {
		this.giaHienTai = __giaHienTai;
	}

	/**
	 * Sets the ma co phieu.
	 *
	 * @param __maCoPhieu
	 *            the maCoPhieu to set
	 */
	public void setMaCoPhieu(String __maCoPhieu) {
		this.maCoPhieu = __maCoPhieu;
	}

	/**
	 * Sets the shares.
	 *
	 * @param __shares
	 *            the shares to set
	 */
	public void setShares(Set<DatabaseShareData> __shares) {
		this.shares = __shares;
	}

	/**
	 * Sets the ten cong ty.
	 *
	 * @param __tenCongTy
	 *            the tenCongTy to set
	 */
	public void setTenCongTy(String __tenCongTy) {
		this.tenCongTy = __tenCongTy;
	}

}
