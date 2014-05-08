package efrei.asyria.m1a.asynchronous;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;

public class HttpPostRequest extends AsyncTask<String, Void, String> 
{
	String url;
	List<NameValuePair> postParam;
	
	public HttpPostRequest(String u, List<NameValuePair> pp) {
		url = u;
		postParam = pp;
	}
	
	@Override
	protected String doInBackground(String... params) 	
	{
		BufferedReader inBuffer = null;
		String result = "fail";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);

			if (postParam != null) {
				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParam);
				
				request.setEntity(formEntity);
			}
			HttpResponse httpResponse = httpClient.execute(request);
			inBuffer = new BufferedReader(
				new InputStreamReader(
					httpResponse.getEntity().getContent()));

			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String newLine = System.getProperty("line.separator");
			while ((line = inBuffer.readLine()) != null) {
				stringBuffer.append(line + newLine);
			}
			inBuffer.close();

			result = stringBuffer.toString();
			
		} catch(Exception e) {
			// Do something about exceptions
			result = e.getMessage();
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
	
	protected void onPostExecute(String page)
	{    	  	
	}	
}