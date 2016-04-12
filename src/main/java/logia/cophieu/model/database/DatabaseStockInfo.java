package logia.cophieu.model.database;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "stock_info")
@DynamicInsert
@DynamicUpdate
public class DatabaseStockInfo {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long                   id;

	/** The ma. */
	@Column(length = 10, name = "ma_co_phieu", unique = true, nullable = false)
	private String                 maCoPhieu;

	@Column(length = 1000, name = "ten_cong_ty", nullable = false)
	private String                 tenCongTy;

	@Column(name = "gia_hien_tai", nullable = false)
	private Float                  giaHienTai;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<DatabaseShareData> shares;

	public DatabaseStockInfo() {
		super();
	}

	public DatabaseStockInfo(String __maCoPhieu, String __tenCongTy, Float __giaHienTai) {
		super();
		this.maCoPhieu = __maCoPhieu;
		this.tenCongTy = __tenCongTy;
		this.giaHienTai = __giaHienTai;
		this.shares = new HashSet<DatabaseShareData>();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @param __id the id to set
	 */
	public void setId(Long __id) {
		this.id = __id;
	}

	/**
	 * @return the maCoPhieu
	 */
	public String getMaCoPhieu() {
		return this.maCoPhieu;
	}

	/**
	 * @param __maCoPhieu the maCoPhieu to set
	 */
	public void setMaCoPhieu(String __maCoPhieu) {
		this.maCoPhieu = __maCoPhieu;
	}

	/**
	 * @return the tenCongTy
	 */
	public String getTenCongTy() {
		return this.tenCongTy;
	}

	/**
	 * @param __tenCongTy the tenCongTy to set
	 */
	public void setTenCongTy(String __tenCongTy) {
		this.tenCongTy = __tenCongTy;
	}

	/**
	 * @return the giaHienTai
	 */
	public Float getGiaHienTai() {
		return this.giaHienTai;
	}

	/**
	 * @param __giaHienTai the giaHienTai to set
	 */
	public void setGiaHienTai(Float __giaHienTai) {
		this.giaHienTai = __giaHienTai;
	}

	/**
	 * @return the shares
	 */
	public Set<DatabaseShareData> getShares() {
		return this.shares;
	}

	/**
	 * @param __shares the shares to set
	 */
	public void setShares(Set<DatabaseShareData> __shares) {
		this.shares = __shares;
	}

}
