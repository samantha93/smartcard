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
import java.nio.charset.Charset;

import static android.nfc.NdefRecord.createMime;

public class BeamActivity extends Activity  implements CreateNdefMessageCallback {
    NfcAdapter mNfcAdapter;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beam);
        textView = (TextView) findViewById(R.id.tvBeam);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		/*if (mNfcAdapter == null)
		{
			Toast.makeText(this, "NFC is not available", Toast.LENGTH_LONG).show();
			finish();
			return;
		}*/

        mNfcAdapter.setNdefPushMessageCallback(this, this);
    }
	
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

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
    */
    @Override
    public NdefMessage createNdefMessage(NfcEvent event) {
        // TODO:envoyer nom+adresse+...
        String text = ("Beam me up, Android !\n\n Beam Time : " + System.currentTimeMillis());
        NdefMessage msg = new NdefMessage(
                new NdefRecord[] { createMime(
                        "application/vnd.efrei.asyria.m1a.smartcard.beam", text.getBytes())
                });
        return msg;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
    }

    void processIntent(Intent intent) {
        textView = (TextView) findViewById(R.id.tvBeam);
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        // read a message
        NdefMessage msg = (NdefMessage) rawMsgs[0];
        // record 0 contains the MIME type, record 1 is the AAR, if present
        // TODO: il faudrait appeler une fonction qui va génerer la carte à partir du msg
        textView.setText(new String(msg.getRecords()[0].getPayload()));
    }
}