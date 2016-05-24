/*
 * 
 */
package logia.cophieu.model.database;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * The Class DatabaseShareData.
 *
 * @author Paul Mai
 */
@Entity
@Table(name = "share_data")
@IdClass(value = ShareDataPrimaryKey.class)
@DynamicInsert
@DynamicUpdate
public class DatabaseShareData {

	// /** The id. */
	// @Id
	// @GeneratedValue
	// @Column(name = "id")
	// private Long id;

	/** The ma. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ma_co_phieu", nullable = false)
	@Id
	private DatabaseStockInfo maCoPhieu;

	/** The nam. */
	@Column(name = "nam")
	@Id
	private int				  nam;

	/** The co tuc. */
	@Column(name = "co_tuc")
	private float			  coTuc;

	/**
	 * Instantiates a new database share data.
	 */
	public DatabaseShareData() {
	}

	/**
	 * Instantiates a new database share data.
	 *
	 * @param __maCoPhieu
	 *            the __ma co phieu
	 * @param __nam
	 *            the __nam
	 * @param __coTuc
	 *            the __co tuc
	 */
	public DatabaseShareData(DatabaseStockInfo __maCoPhieu, int __nam, float __coTuc) {
		super();
		this.maCoPhieu = __maCoPhieu;
		this.nam = __nam;
		this.coTuc = __coTuc;
	}

	// /**
	// * Gets the id.
	// *
	// * @return the id
	// */
	// public Long getId() {
	// return this.id;
	// }
	//
	// /**
	// * Sets the id.
	// *
	// * @param __id the id to set
	// */
	// public void setId(Long __id) {
	// this.id = __id;
	// }

	/**
	 * Gets the ma co phieu.
	 *
	 * @return the maCoPhieu
	 */
	public DatabaseStockInfo getMaCoPhieu() {
		return this.maCoPhieu;
	}

	/**
	 * Sets the ma co phieu.
	 *
	 * @param __maCoPhieu
	 *            the maCoPhieu to set
	 */
	public void setMaCoPhieu(DatabaseStockInfo __maCoPhieu) {
		this.maCoPhieu = __maCoPhieu;
	}

	/**
	 * Gets the nam.
	 *
	 * @return the nam
	 */
	public int getNam() {
		return this.nam;
	}

	/**
	 * Sets the nam.
	 *
	 * @param __nam
	 *            the nam to set
	 */
	public void setNam(int __nam) {
		this.nam = __nam;
	}

	/**
	 * Gets the co tuc.
	 *
	 * @return the coTuc
	 */
	public float getCoTuc() {
		return this.coTuc;
	}

	/**
	 * Sets the co tuc.
	 *
	 * @param __coTuc
	 *            the coTuc to set
	 */
	public void setCoTuc(float __coTuc) {
		this.coTuc = __coTuc;
	}

}

class ShareDataPrimaryKey implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ma co phieu. */
	private DatabaseStockInfo maCoPhieu;

	/** The nam. */
	private int				  nam;

	/**
	 * Gets the ma co phieu.
	 *
	 * @return the maCoPhieu
	 */
	public DatabaseStockInfo getMaCoPhieu() {
		return this.maCoPhieu;
	}

	/**
	 * Sets the ma co phieu.
	 *
	 * @param __maCoPhieu
	 *            the maCoPhieu to set
	 */
	public void setMaCoPhieu(DatabaseStockInfo __maCoPhieu) {
		this.maCoPhieu = __maCoPhieu;
	}

	/**
	 * Gets the nam.
	 *
	 * @return the nam
	 */
	public int getNam() {
		return this.nam;
	}

	/**
	 * @param __nam
	 *            the nam to set
	 */
	public void setNam(int __nam) {
		this.nam = __nam;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder _builder = new HashCodeBuilder();
		return _builder.append(this.nam).append(this.maCoPhieu).build();
	}

	@Override
	public boolean equals(Object __obj) {
		if (__obj instanceof ShareDataPrimaryKey) {
			EqualsBuilder _builder = new EqualsBuilder();
			return _builder.append(this.nam, ((ShareDataPrimaryKey) __obj).getNam())
			        .append(this.maCoPhieu, ((ShareDataPrimaryKey) __obj).getMaCoPhieu()).build();
		}
		else {
			return false;
		}

	}

}
