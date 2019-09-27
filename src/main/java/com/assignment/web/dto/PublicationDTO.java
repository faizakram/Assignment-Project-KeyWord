package com.assignment.web.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PublicationDTO {
	private Long id;
	private List<CommonDTO> authors;
	private String title;
	private String year;
	private Long publicationEnumId;
	private Long magazineEnumId;
	private Long bookEnumId;
	private String hero;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getPublicationEnumId() {
		return publicationEnumId;
	}

	public void setPublicationEnumId(Long publicationEnumId) {
		this.publicationEnumId = publicationEnumId;
	}

	public Long getMagazineEnumId() {
		return magazineEnumId;
	}

	public void setMagazineEnumId(Long magazineEnumId) {
		this.magazineEnumId = magazineEnumId;
	}

	public Long getBookEnumId() {
		return bookEnumId;
	}

	public void setBookEnumId(Long bookEnumId) {
		this.bookEnumId = bookEnumId;
	}

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

	public List<CommonDTO> getAuthors() {
		return authors;
	}

	public void setAuthors(List<CommonDTO> authors) {
		this.authors = authors;
	}

	

	
}
