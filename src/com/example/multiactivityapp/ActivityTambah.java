package com.example.multiactivityapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityTambah extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambah);
	}
	
	public void buttonTambah(View v){
		Intent i = getIntent();
		
		//id diisi 0 dulu, tapi tidak dimasukkan ke dalam database
		//karena nanti otomatis diincrement
		int id = 0;
		
		EditText etNama = (EditText)findViewById(R.id.etNamaTambah);
		EditText etNim = (EditText)findViewById(R.id.etNimTambah);
		EditText etAlamat = (EditText)findViewById(R.id.etAlamatTambah);
		EditText etHape = (EditText)findViewById(R.id.etHapeTambah);
		
		
		String strNama = etNama.getText().toString();
		String strNim = etNim.getText().toString();
		String strAlamat = etAlamat.getText().toString();
		String strHape = etHape.getText().toString();
		
		
		DataList datakirim = new DataList(id, strNama, strNim, strAlamat, strHape);
		
		i.putExtra("dataterima", datakirim);
		
		setResult(RESULT_OK, i);
		finish();
		
	}
	
}
