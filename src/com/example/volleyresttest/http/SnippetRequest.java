package com.example.volleyresttest.http;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonRequest;
import com.example.volleyresttest.models.Snippet;
import com.google.gson.Gson;

public class SnippetRequest extends JsonRequest<Snippet> {

	// TODO: DEBUG - REMOVE LATER
	private static final String clsNm = SnippetRequest.class.getCanonicalName();

	private final Listener<Snippet> snptLstnr;
	private final Gson gsOnSnpt = new Gson();

	// GET - METHOD
	public SnippetRequest(Snippet snpt, Boolean delete,
			Listener<Snippet> snippetListener, ErrorListener errorListener) {
		super((delete == true) ? Method.DELETE : Method.GET, snpt.getUrl(), null,
				snippetListener, errorListener);
		this.snptLstnr = snippetListener;
	}

	// POST - METHOD
	public SnippetRequest(Snippet snpt, Listener<Snippet> snippetListener,
			ErrorListener errorListener) {
		super((snpt.getUrl() != null) ? Method.PUT : Method.POST, (snpt
				.getUrl() != null) ? snpt.getUrl() : getBaseURL(), snpt
				.toJson(), snippetListener, errorListener);
		Log.d("SNIPPET REQUEST", snpt.toJson());
		this.snptLstnr = snippetListener;
	}

	@Override
	protected void deliverResponse(Snippet snpt) {
		snptLstnr.onResponse(snpt);
	}

	@Override
	protected Response<Snippet> parseNetworkResponse(NetworkResponse response) {
		String jsOnStr = new String(response.data);
		// If jsOnStr isEmpty() then it was DELETE
		if (jsOnStr == "") {
			Log.d(clsNm, "json Response : " + jsOnStr);
			Snippet snptRspns = gsOnSnpt.fromJson(jsOnStr, Snippet.class);
			Log.d(clsNm, "Snippet: " + snptRspns.toJson());
			return Response.success(snptRspns, getCacheEntry());
		} else {
			return Response.success(new Snippet(), getCacheEntry());
		}
	}

	@Override
	public Map<String, String> getHeaders() throws AuthFailureError {
		HashMap<String, String> headers = new HashMap<String, String>();

		// TODO: Implement OAUTH2
		Log.d(clsNm, "Basic Zm9vOmJhcg==");
		headers.put("Authorization", "Basic Zm9vOmJhcg==");
		headers.put("Content-Type", "application/json");

		return headers;
	}

	private static String getBaseURL() {
		return "http://192.168.1.152/snippets/";
	}

}
