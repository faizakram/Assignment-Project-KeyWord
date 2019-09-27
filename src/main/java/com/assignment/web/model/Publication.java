package com.assignment.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the publication database table.
 * 
 */
@Entity
@Table(name = "publication")
@NamedQuery(name = "Publication.findAll", query = "SELECT p FROM Publication p")
public class Publication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 200)
	private String hero;

	@Column(nullable = false, length = 200)
	private String title;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date year;

	// bi-directional many-to-one association to Author
	// bi-directional many-to-one association to PublicationAuthor
	@OneToMany(mappedBy = "publication", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PublicationAuthor> publicationAuthors;

	// bi-directional many-to-one association to EnumItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publication_enum_id", nullable = false)
	private EnumItem publicationEnum;

	// bi-directional many-to-one association to EnumItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "magazine_type_enum_id")
	private EnumItem magazineEnum;

	// bi-directional many-to-one association to EnumItem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_genre_enum_id")
	private EnumItem bookEnum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public EnumItem getPublicationEnum() {
		return publicationEnum;
	}

	public void setPublicationEnum(EnumItem publicationEnum) {
		this.publicationEnum = publicationEnum;
	}

	public EnumItem getMagazineEnum() {
		return magazineEnum;
	}

	public void setMagazineEnum(EnumItem magazineEnum) {
		this.magazineEnum = magazineEnum;
	}

	public EnumItem getBookEnum() {
		return bookEnum;
	}

	public void setBookEnum(EnumItem bookEnum) {
		this.bookEnum = bookEnum;
	}

	public List<PublicationAuthor> getPublicationAuthors() {
		return publicationAuthors;
	}

	public void setPublicationAuthors(List<PublicationAuthor> publicationAuthors) {
		this.publicationAuthors = publicationAuthors;
	}

	public PublicationAuthor addPublicationAuthor(PublicationAuthor publicationAuthor) {
		getPublicationAuthors().add(publicationAuthor);
		publicationAuthor.setPublication(this);

		return publicationAuthor;
	}

	public PublicationAuthor removePublicationAuthor(PublicationAuthor publicationAuthor) {
		getPublicationAuthors().remove(publicationAuthor);
		publicationAuthor.setPublication(null);

		return publicationAuthor;
	}


}