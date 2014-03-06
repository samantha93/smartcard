package efrei.asyria.m1a.smartcard;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.CreateNdefMessageCallback;
import android.nfc.NfcEvent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class BeamActivity extends Activity /* implements CreateNdefMessageCallback */ {
	NfcAdapter mNfcAdapter;
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beam);
		textView = (TextView) findViewById(R.id.tvBeam);
		/*
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (mNfcAdapter == null)
		{
			Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
			finish();
			return;
		}
		
		mNfcAdapter.setNdefPushMessageCallback(this, this);*/
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
/*
	@Override
	protected void onResume() {
		super.onResume();
		
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction()))
		{
			processIntent(getIntent());
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
	}

	@Override
	public NdefMessage createNdefMessage(NfcEvent event) {
		/*String text = ("Beam me up, Android !\n\n Beam Time : " + System.currentTimeMillis());
		
		NdefRecord mimeRecord = new NdefRecord(
				NdefRecord.TNF_MIME_MEDIA, 
				"efrei.asyria.m1a.smartcard".getBytes(),
				new byte[0],
				text.getBytes());
		
		NdefMessage message = new NdefMessage(mimeRecord);
		
		return message;
	}

	private void processIntent(Intent intent) {
		textView = (TextView) findViewById(R.id.tvBeam);
		Parcelable[] rawMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
		
		NdefMessage message = (NdefMessage) rawMessages[0];
		
		textView.setText(new String(message.getRecords()[0].getPayload()));
	}*/
}
