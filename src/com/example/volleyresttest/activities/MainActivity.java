package com.example.volleyresttest.activities;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.volleyresttest.R;
import com.example.volleyresttest.http.SnippetSingleton;
import com.example.volleyresttest.models.Snippet;
import com.example.volleyresttest.models.SnippetList;
import com.example.volleyresttest.activities.SnippetDetailActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements Response.ErrorListener,
		Listener<SnippetList> {

	// Child View Detail Snippet
	Intent snptDtlActvty;
	TextView txtVwTitle;
	ListView lstVwSnpts;
	Button btnPostSnippet;
	ArrayAdapter<Snippet> snptsArrayAdapter;

	SnippetList snptLst = new SnippetList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// TODO: Replace the SnippetSingleton with a Service {{{
		SnippetSingleton.init(this);
		// }}}
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
		SnippetSingleton.getInstance().getSnippetApi()
				.getListSnippets(this, this);
	}

	public void onResume() {
		super.onResume();
		SnippetSingleton.getInstance().getSnippetApi()
				.getListSnippets(this, this);
	}

	private void initUI() {
		txtVwTitle = (TextView) this.findViewById(R.id.textViewSniptTitle);
		lstVwSnpts = (ListView) this.findViewById(R.id.listViewSnippets);
		btnPostSnippet = (Button) this.findViewById(R.id.buttonPOSTSnippet);
		btnPostSnippet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Log.d("Main Activity Add Snippet: ", "");
				snptDtlActvty.putExtra("snippetJSON", new Snippet().toJson());
				startActivity(snptDtlActvty);
			}

		});

		snptsArrayAdapter = new ArrayAdapter<Snippet>(this,
				android.R.layout.simple_list_item_1);
		snptDtlActvty = new Intent(this, SnippetDetailActivity.class);
		lstVwSnpts.setAdapter(snptsArrayAdapter);
		lstVwSnpts.setOnItemClickListener(new OnItemClickListener() {

			// [parentView, view, position, id]
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Snippet snpt = snptsArrayAdapter.getItem(position);
				Log.d("Main Activity ListView Click : ", snpt.toJson());
				snptDtlActvty.putExtra("snippetJSON", snpt.toJson());
				startActivity(snptDtlActvty);
			}

		});
	}

	@Override
	public void onResponse(SnippetList snippetList) {
		// TODO: Implement next and previous
		snptsArrayAdapter.clear();
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
