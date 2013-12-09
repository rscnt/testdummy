package com.example.volleyresttest.http;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.example.volleyresttest.models.Snippet;
import com.example.volleyresttest.models.SnippetList;

public class SnippetApi {

	private final RequestQueue rqstQueue;

	public SnippetApi(RequestQueue rqstQueue) {
		this.rqstQueue = rqstQueue;
	}

	/**
	 * @return the rqstQ
	 */
	public RequestQueue getRqstQ() {
		return rqstQueue;
	}

	@SuppressWarnings("unchecked")
	public Request<Snippet> getListSnippets(Listener<SnippetList> snptLstnr,
			ErrorListener rrLstnr) {
		Request<SnippetList> rqstSnpt = new SnippetListRequest(snptLstnr,
				rrLstnr);
		return rqstQueue.add(rqstSnpt);
	}

	@SuppressWarnings("unchecked")
	public Request<Snippet> getSingleSnippet(Snippet snpt,
			Listener<Snippet> snptLstnr, ErrorListener rrLstnr) {
		Request<Snippet> rqstSnpt = new SnippetRequest(snpt, false, snptLstnr,
				rrLstnr);
		return rqstQueue.add(rqstSnpt);
	}

	@SuppressWarnings("unchecked")
	public Request<Snippet> deleteSingleSnippet(Snippet snpt,
			Listener<Snippet> snptLstnr, ErrorListener rrLstnr) {
		Request<Snippet> rqstSnpt = new SnippetRequest(snpt, true, snptLstnr,
				rrLstnr);
		return rqstQueue.add(rqstSnpt);
	}

	@SuppressWarnings("unchecked")
	public Request<Snippet> postSingleSnippet(Snippet snpt,
			Listener<Snippet> snptLstnr, ErrorListener rrLstnr) {
		Request<Snippet> rqstSnpt;
		rqstSnpt = new SnippetRequest(snpt, snptLstnr, rrLstnr);
		return rqstQueue.add(rqstSnpt);
	}

	@SuppressWarnings("unchecked")
	public Request<Snippet> putSingleSnippet(Snippet snpt,
			Listener<Snippet> snptLstnr, ErrorListener rrLstnr) {
		if (snpt.getUrl() == null) {
			throw new IllegalAccessError(
					"You should use postSingleSnippet or Set the ID for this snippet");
		}
		Request<Snippet> rqstSnpt;
		rqstSnpt = new SnippetRequest(snpt, snptLstnr, rrLstnr);
		return rqstQueue.add(rqstSnpt);
	}
}
