package com.example.volleyresttest.models;

import com.google.gson.Gson;

public class Snippet {

	private Long id;
	private String url;
	private String highlight;
	private String owner;
	private String title;
	private String code;
	private Boolean lineos;
	private String language;
	private String style;

	public Snippet() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the lineos
	 */
	public Boolean getLineos() {
		return lineos;
	}

	/**
	 * @param lineos
	 *            the lineos to set
	 */
	public void setLineos(Boolean lineos) {
		this.lineos = lineos;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the highlight
	 */
	public String getHighlight() {
		return highlight;
	}

	/**
	 * @param highlight
	 *            the highlight to set
	 */
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
