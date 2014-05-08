package efrei.asyria.m1a.asynchronous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
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
	List<NameValuePair> postParam;

	public HttpGetRequest(String u/*, List<NameValuePair> pp*/) {
		url = u;
		//postParam = pp;
	}

	@Override
	protected String doInBackground(String... params) {
		String url = this.url;
		BufferedReader inBuffer = null;
		String result = "fail";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			
			/*if (postParam != null) {
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParam);
				
				((HttpResponse) request).setEntity(formEntity);
			}*/
			
			HttpResponse response = httpClient.execute(request);
			inBuffer = new BufferedReader(new InputStreamReader(response
					.getEntity().getContent()));

			StringBuffer buffer = new StringBuffer("");
			String line = "";
			String newLine = System.getProperty("line.separator");
			while ((line = inBuffer.readLine()) != null) {
				buffer.append(line + newLine);
			}
			inBuffer.close();

			result = buffer.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inBuffer != null) {
				try {
					inBuffer.close();
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
	}

	@Override
	protected void onPostExecute(String page) {
	}
}
