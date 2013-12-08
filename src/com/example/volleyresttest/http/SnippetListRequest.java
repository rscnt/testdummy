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
import com.example.volleyresttest.models.SnippetList;
import com.google.gson.Gson;

public class SnippetListRequest extends JsonRequest<SnippetList> {

	// TODO: DEBUG - REMOVE LATER
	private static final String clsNm = SnippetRequest.class.getCanonicalName();

	private final Listener<SnippetList> snptListLstnr;
	private final Gson gsOnSnpt = new Gson();

	// GET - METHOD
	public SnippetListRequest(Listener<SnippetList> snippetListener,
			ErrorListener errorListener) {
		super(Method.GET, getBaseURL(), null, snippetListener, errorListener);
		this.snptListLstnr = snippetListener;
	}

	@Override
	protected Response<SnippetList> parseNetworkResponse(
			NetworkResponse response) {
		String jsOnStr = new String(response.data);
		// If jsOnStr isEmpty() then it was DELETE
		Log.d(clsNm, "json Response : " + jsOnStr);
		SnippetList snptRspns = gsOnSnpt.fromJson(jsOnStr, SnippetList.class);
		Log.d(clsNm, "Snippet: " + snptRspns.toJson());
		return Response.success(snptRspns, getCacheEntry());
	}

	@Override
	protected void deliverResponse(SnippetList snptLst) {
		snptListLstnr.onResponse(snptLst);
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
