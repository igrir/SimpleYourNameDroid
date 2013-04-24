package com.example.multiactivityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityTiga extends Activity {
	
	int id;
	DataList dataterima;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//memilih view yang mana
		setContentView(R.layout.activity_tiga);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		Intent in = getIntent();

		EditText etNama = (EditText) findViewById(R.id.etNama3);
		EditText etNim = (EditText) findViewById(R.id.etNim3);
		EditText etAlamat = (EditText) findViewById(R.id.etAlamat3);
		EditText etHape = (EditText) findViewById(R.id.etHape3);
		
		dataterima = (DataList)in.getSerializableExtra("datakirim");

		etNama.setText(dataterima.judul);
		etNim.setText(dataterima.keterangan);
		etAlamat.setText(dataterima.alamat);
		etHape.setText(dataterima.hape);		
		id = dataterima.id;
	}
	
	public void buttonUpdate(View v){
		
		//Intent i = new Intent(getApplicationContext(), MainActivity.class);
		Intent i = getIntent();
		
		EditText etNama = (EditText)findViewById(R.id.etNama3);
		EditText etNim = (EditText)findViewById(R.id.etNim3);
		EditText etAlamat = (EditText)findViewById(R.id.etAlamat3);
		EditText etHape = (EditText)findViewById(R.id.etHape3);
		
		
		String strNama = etNama.getText().toString();
		String strNim = etNim.getText().toString();
		String strAlamat = etAlamat.getText().toString();
		String strHape = etHape.getText().toString();
		
		dataterima.judul = strNama;
		dataterima.keterangan = strNim;
		dataterima.alamat = strAlamat;
		dataterima.hape = strHape;
		dataterima.id = id;
		
		i.putExtra("dataterima", dataterima);
	
		setResult(RESULT_OK, i);
		finish();
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
}
