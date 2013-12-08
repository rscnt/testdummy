package com.example.volleyresttest.http;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * 
 * @author _r
 * @see "<a href='http://en.wikipedia.org/wiki/Singleton_pattern'> Singleton </a>"
 */
public class SnippetSingleton {

	private static SnippetSingleton snptSngltnInstnc = null;
	private RequestQueue rqstQueue;
	private SnippetApi snptApi;

	private SnippetSingleton(Context cntxt) {
		rqstQueue = Volley.newRequestQueue(cntxt);
		snptApi = new SnippetApi(rqstQueue);
	}

	public static void init(Context cntxt) {
		if (null == snptSngltnInstnc) {
			snptSngltnInstnc = new SnippetSingleton(cntxt);
		} else {
			throw new IllegalStateException(
					"There's already a SnippetSingleton");
		}
	}

	public static SnippetSingleton getInstance() {
		if (null == snptSngltnInstnc) {
			throw new IllegalStateException("There's no SnippetSingleton");
		}
		return snptSngltnInstnc;
	}

	public RequestQueue getRequestQueue() {
		return this.rqstQueue;
	}

	public SnippetApi getSnippetApi() {
		return snptApi;
	}
	
}
