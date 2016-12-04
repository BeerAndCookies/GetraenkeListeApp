package com.example.abdussamed.getraenkelisteapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter != null && nfcAdapter.isEnabled()) {

        } else {
            finish();
        }
    }

    public void scanBarcode(View view) {
    Log.d("test", "button works!");
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }
    public void scanNFC(View view) {
        Log.d("test", "button works!");
        Toast.makeText(this, "Hold your Student Card on you phone, serial number will be shown!",
                Toast.LENGTH_LONG).show();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                Log.i("xZing", "contents: "+contents+" format: "+format); // Handle successful scan
            }
            else if(resultCode == RESULT_CANCELED){ // Handle cancel
                Log.i("xZing", "Cancelled");
            }
        }
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        byte[] tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
        String result=this.ByteArrayToHexString(tagId);
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);
    }

    @Override
    protected void onPause() {
        super.onPause();

        nfcAdapter.disableForegroundDispatch(this);
    }

    String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }


}
