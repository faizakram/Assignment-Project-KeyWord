package com.assignment.web.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the publication_author database table.
 * 
 */
@Entity
@Table(name="publication_author")
@NamedQuery(name="PublicationAuthor.findAll", query="SELECT p FROM PublicationAuthor p")
public class PublicationAuthor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="is_deleted")
	private boolean isDeleted;
	
	//bi-directional many-to-one association to Author
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id", nullable=false)
	private Author author;

	//bi-directional many-to-one association to Publication
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publication_id", nullable=false)
	private Publication publication;

	public PublicationAuthor() {
	}

	public PublicationAuthor(Author author, Publication publication) {
		super();
		this.author = author;
		this.publication = publication;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publication getPublication() {
		return this.publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}