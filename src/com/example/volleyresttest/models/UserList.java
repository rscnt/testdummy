package com.example.volleyresttest.models;

import java.util.ArrayList;

public class UserList {

	private Integer count;
	private Integer next;
	private Integer previous;
	private ArrayList<User> results;

	public UserList() {
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
	public ArrayList<User> getResults() {
		return results;
	}

	/**
	 * @param results
	 *            the results to set
	 */
	public void setResults(ArrayList<User> results) {
		this.results = results;
	}

}
