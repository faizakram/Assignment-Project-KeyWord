package com.assignment.web.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the enum_item database table.
 * 
 */
@Entity
@Table(name = "enum_item")
@NamedQuery(name = "EnumItem.findAll", query = "SELECT e FROM EnumItem e")
public class EnumItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "enam_item_name", nullable = false, length = 50)
	private String enamItemName;

	@Column(name = "enum_item_code", nullable = false, length = 50)
	private String enumItemCode;

	// bi-directional many-to-one association to Publication
	@OneToMany(mappedBy = "publicationEnum")
	private List<Publication> pubicationEnums;

	// bi-directional many-to-one association to Publication
	@OneToMany(mappedBy = "magazineEnum")
	private List<Publication> magazineEnums;

	// bi-directional many-to-one association to Publication
	@OneToMany(mappedBy = "bookEnum")
	private List<Publication> bookEnums;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnamItemName() {
		return enamItemName;
	}

	public void setEnamItemName(String enamItemName) {
		this.enamItemName = enamItemName;
	}

	public String getEnumItemCode() {
		return enumItemCode;
	}

	public void setEnumItemCode(String enumItemCode) {
		this.enumItemCode = enumItemCode;
	}

	public List<Publication> getPubicationEnums() {
		return pubicationEnums;
	}

	public void setPubicationEnums(List<Publication> pubicationEnums) {
		this.pubicationEnums = pubicationEnums;
	}

	public List<Publication> getMagazineEnums() {
		return magazineEnums;
	}

	public void setMagazineEnums(List<Publication> magazineEnums) {
		this.magazineEnums = magazineEnums;
	}

	public List<Publication> getBookEnums() {
		return bookEnums;
	}

	public void setBookEnums(List<Publication> bookEnums) {
		this.bookEnums = bookEnums;
	}

}