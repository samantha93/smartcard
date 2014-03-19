package efrei.asyria.m1a.smartcard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import efrei.asyria.m1a.asynchronous.HttpGetRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnexionActivity extends Activity {

	private EditText etLogin;
	private EditText etPwd;
	private Button validButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);
		etLogin = (EditText) findViewById(R.id.etLogin);
		etPwd = (EditText) findViewById(R.id.etPwd);
		validButton = (Button) findViewById(R.id.buttonValidConnexion);
		validButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://dev.smart-card.fr/login?username="+etLogin.getText()+"&password="+etPwd.getText();
				new HttpGetRequest(ConnexionActivity.this).execute(url);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
