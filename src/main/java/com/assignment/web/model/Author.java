package com.assignment.web.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the author database table.
 * 
 */
@Entity
@Table(name = "author")
@NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 200)
	private String name;

	// bi-directional many-to-one association to PublicationAuthor
	@OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PublicationAuthor> publicationAuthors;

	public Author() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PublicationAuthor> getPublicationAuthors() {
		return this.publicationAuthors;
	}

	public void setPublicationAuthors(List<PublicationAuthor> publicationAuthors) {
		this.publicationAuthors = publicationAuthors;
	}

	public PublicationAuthor addPublicationAuthor(PublicationAuthor publicationAuthor) {
		getPublicationAuthors().add(publicationAuthor);
		publicationAuthor.setAuthor(this);

		return publicationAuthor;
	}

	public PublicationAuthor removePublicationAuthor(PublicationAuthor publicationAuthor) {
		getPublicationAuthors().remove(publicationAuthor);
		publicationAuthor.setAuthor(null);

		return publicationAuthor;
	}

}