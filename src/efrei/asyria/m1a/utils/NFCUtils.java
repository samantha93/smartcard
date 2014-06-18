package efrei.asyria.m1a.utils;

import java.nio.charset.Charset;
import java.util.Locale;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;

public class NFCUtils {

	public static NdefRecord createRecord(String text, Locale locale, boolean encode) {
		byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII"));
		Charset utfEncoding = encode ? Charset.forName("UTF-8") : Charset.forName("UTF-16");
		byte[] textBytes = text.getBytes(utfEncoding);
		int utfBit = encode ? 0 : (1 << 7);
		char status = (char) (utfBit + langBytes.length);
		byte[] data = new byte[langBytes.length + textBytes.length + 1];
		data[0] = (byte) status;
		System.arraycopy(langBytes, 0, data, 1, langBytes.length);
		System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
		return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
	}
	
	public static NdefRecord createMimeRecord(String mimeType, byte[] payload) {
		byte[] mimeBytes = mimeType.getBytes(Charset.forName("US-ASCII"));
		NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0], payload);
		return mimeRecord;
	}
	
	public static NdefMessage sendCard(String idCard)
	{
		NdefRecord[] records = null; 
		records = new NdefRecord[1];
		records[0] = createMimeRecord("application/vnd.efrei.asyria.m1a.smartcard.beam", idCard.getBytes());

		return new NdefMessage(records);
	}
}
