package com.example.volleyresttest.activities;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.example.volleyresttest.R;
import com.example.volleyresttest.http.SnippetSingleton;
import com.example.volleyresttest.models.Snippet;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class SnippetDetailActivity extends Activity implements
		Response.ErrorListener, Listener<Snippet> {

	Snippet snpt;
	Gson gson = new Gson();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_snippet_detail);
		if (null == savedInstanceState) {
			Bundle extras = getIntent().getExtras();
			if (extras != null) {
				snpt = gson.fromJson(extras.getString("snippetJSON"),
						Snippet.class);
			} else {
				snpt = new Snippet();
			}
		} else {
			snpt = gson.fromJson(savedInstanceState.getString("snippetJSON"),
					Snippet.class);
		}
		initUI();
	}

	public void onStart() {
		super.onStart();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			snpt = gson
					.fromJson(extras.getString("snippetJSON"), Snippet.class);
		} else {
			snpt = new Snippet();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.snippet_detail, menu);
		return true;
	}

	private void initUI() {

		final EditText editTxtTitle = (EditText) findViewById(R.id.editTextTitle);
		final EditText editTxtCode = (EditText) findViewById(R.id.editTextCode);
		final LinearLayout lnrLyr = (LinearLayout) findViewById(R.id.lnrLayout);

		final SnippetDetailActivity that = this;
		Button btnSendRqst = (Button) findViewById(R.id.buttonSendSnippet);

		editTxtTitle.setText(snpt.getTitle());
		editTxtCode.setText(snpt.getCode());

		if (!isNewSnippet()) {
			Button btnDelete = new Button(this);
			btnDelete.setText("Delete Snippet " + snpt.getTitle());
			btnDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Toast toast = Toast.makeText(that,
							"DELETED: " + snpt.toString(), Toast.LENGTH_SHORT);
					SnippetSingleton.getInstance().getSnippetApi()
							.deleteSingleSnippet(snpt, that, that);
					toast.show();
				}
			});
			lnrLyr.addView(btnDelete);
		}

		btnSendRqst.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				snpt.setTitle(editTxtTitle.getText().toString());
				snpt.setCode(editTxtCode.getText().toString());

				if (isNewSnippet()) {
					Toast toast = Toast.makeText(that,
							"POST: " + snpt.toString(), Toast.LENGTH_SHORT);
					toast.show();
					SnippetSingleton.getInstance().getSnippetApi()
							.postSingleSnippet(snpt, that, that);
				} else {
					Toast toast = Toast.makeText(that,
							"PUT: " + snpt.toString(), Toast.LENGTH_SHORT);
					toast.show();
					SnippetSingleton.getInstance().getSnippetApi()
							.putSingleSnippet(snpt, that, that);
				}

			}
		});
	}

	private Boolean isNewSnippet() {
		if (snpt.getUrl() == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void onResponse(Snippet snippet) {
		Log.d("Snippet DETAIL RESPONSE", snippet.toJson());
	}

	@Override
	public void onErrorResponse(VolleyError volleyError) {
		Toast toast = Toast.makeText(this, "Error: "
				+ volleyError.networkResponse.statusCode, Toast.LENGTH_SHORT);
		toast.show();
		volleyError.printStackTrace();
	}
}
