package com.example.volleyresttest.activities;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.volleyresttest.R;
import com.example.volleyresttest.http.SnippetSingleton;
import com.example.volleyresttest.models.Snippet;
import com.example.volleyresttest.models.SnippetList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity implements Response.ErrorListener,
		Listener<SnippetList> {

	TextView txtVwTitle;
	ListView lstVwSnpts;
	ArrayAdapter<Snippet> snptsArrayAdapter;
	SnippetList snptLst = new SnippetList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onStart() {
		super.onStart();
		// TODO: Replace the SnippetSingleton with a Service {{{
		SnippetSingleton.init(this);
		SnippetSingleton.getInstance().getSnippetApi()
				.getListSnippets(this, this);
		// }}}
	}

	private void initUI() {
		txtVwTitle = (TextView) this.findViewById(R.id.textViewSniptTitle);
		lstVwSnpts = (ListView) this.findViewById(R.id.listViewSnippets);
		snptsArrayAdapter = new ArrayAdapter<Snippet>(this,
				android.R.layout.simple_list_item_1);
		lstVwSnpts.setAdapter(snptsArrayAdapter);
		lstVwSnpts.setOnItemClickListener(new OnItemClickListener() {

			// [parentView, view, position, id]
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Snippet snpt = snptsArrayAdapter.getItem(position);
				Log.d("Main Activity ListView Click : ", snpt.toJson());
			}

		});
	}

	@Override
	public void onResponse(SnippetList snippetList) {
		// TODO: Implement next and previous
		for (Snippet snpt : snippetList.getResults()) {
			snptsArrayAdapter.add(snpt);
		}
	}

	@Override
	public void onErrorResponse(VolleyError volleyError) {
		Log.d("MainActivity onErrorResponse",
				String.valueOf(volleyError.networkResponse.statusCode));
	}

}
