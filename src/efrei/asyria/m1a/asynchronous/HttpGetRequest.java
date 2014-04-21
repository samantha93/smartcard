package efrei.asyria.m1a.asynchronous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import efrei.asyria.m1a.smartcard.ConnectionActivity;
import efrei.asyria.m1a.smartcard.HomeActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class HttpGetRequest extends AsyncTask<String, Void, String> {
	private String url;
	private String result = "fail";
	private ProgressDialog progressDialog;

	public HttpGetRequest(ProgressDialog p) {
		progressDialog = p;
		progressDialog.setIndeterminate(false);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		this.url = params[0];
		return GetSomething();
	}

	final String GetSomething() {
		String url = this.url;
		BufferedReader inStream = null;
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpRequest = new HttpGet(url);
			HttpResponse response = httpClient.execute(httpRequest);
			inStream = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));

			StringBuffer buffer = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = inStream.readLine()) != null) {
				buffer.append(line + NL);
			}
			inStream.close();

			result = buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(result);


		return result;
	}

	@Override
	protected void onPreExecute() {
		progressDialog.show();
	}

	@Override
	protected void onPostExecute(String page) {
		progressDialog.dismiss();
	}
}
