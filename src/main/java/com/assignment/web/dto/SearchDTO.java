package com.assignment.web.dto;

public class SearchDTO {
	private Long publicationEnumId;
	private Long magazineEnumId;
	private Long bookEnumId;
	private Long authorId;
	private String authorName;
	private String title;
	private String hero;
	private String year;

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

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHero() {
		return hero;
	}

	public void setHero(String hero) {
		this.hero = hero;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

}
