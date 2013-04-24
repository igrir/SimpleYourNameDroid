package com.example.multiactivityapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityDua extends Activity {
	
	int id; //id yang lagi diedit
	
	DataList dataterima;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dua);
		
		
		Intent in = getIntent();
		
		TextView tvNama = (TextView)findViewById(R.id.tvNama);
		TextView tvNim = (TextView)findViewById(R.id.tvNim);
		TextView tvAlamat = (TextView)findViewById(R.id.tvAlamat);
		TextView tvHape = (TextView)findViewById(R.id.tvHape);
		
		dataterima = (DataList)in.getSerializableExtra("datakirim");
		
		
		tvNama.setText(dataterima.judul);
		tvNim.setText(dataterima.keterangan);
		tvAlamat.setText(dataterima.alamat);
		tvHape.setText(dataterima.hape);
		id = dataterima.id;
	}
	
	
	
	@Override
	protected void onStart() {
		super.onStart();
		
		
	
		
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		if ((requestCode == 98) && (resultCode == RESULT_OK)) {
			TextView tvNama = (TextView)findViewById(R.id.tvNama);
			TextView tvNim = (TextView)findViewById(R.id.tvNim);
			TextView tvAlamat = (TextView)findViewById(R.id.tvAlamat);
			TextView tvHape = (TextView)findViewById(R.id.tvHape);
			
			DataList dataterima = (DataList)data.getSerializableExtra("dataterima");
			
			tvNama.setText(dataterima.judul);
			tvNim.setText(dataterima.keterangan);
			tvAlamat.setText(dataterima.alamat);
			tvHape.setText(dataterima.hape);
			
			
			//dikirim ke MainActivity
			Intent i = getIntent();
			
			i.putExtra("dataterima", dataterima);
			
			setResult(RESULT_OK, i);
			finish();
			
			
		}
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.menu_edit:
			Intent i3 = new Intent(getBaseContext(), ActivityTiga.class);
			
			i3.putExtra("datakirim", dataterima);
			
			startActivityForResult(i3, 98);
			return true;
			
		case R.id.menu_delete:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("Apakah yakin akan dihapus?")
			.setPositiveButton("OK", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i = getIntent();
					i.putExtra("id_terima", id);
					setResult(1,i);
					finish();
				}
				
			})
			.setNeutralButton("BATAL", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
			
			AlertDialog alert = builder.create();
			alert.show();
			
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu2, menu);
		return true;	
	}
}
