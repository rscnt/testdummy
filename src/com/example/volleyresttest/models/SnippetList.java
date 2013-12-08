package com.example.volleyresttest.models;

import java.util.ArrayList;

import com.google.gson.Gson;

public class SnippetList {

	private Integer count;
	private Integer next;
	private Integer previous;
	private ArrayList<Snippet> results;

	public SnippetList() {
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * @return the next
	 */
	public Integer getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(Integer next) {
		this.next = next;
	}

	/**
	 * @return the previous
	 */
	public Integer getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(Integer previous) {
		this.previous = previous;
	}

	/**
	 * @return the results
	 */
	public ArrayList<Snippet> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(ArrayList<Snippet> results) {
		this.results = results;
	}

	@Override
	public String toString() {
		return this.getCount().toString();
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
