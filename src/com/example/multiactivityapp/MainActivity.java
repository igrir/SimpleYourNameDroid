package com.example.multiactivityapp;

import java.util.ArrayList;
import java.util.Vector;

import com.example.multiactivityapp.DbRelasi.Relasi;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public ArrayList<DataList> alData = new ArrayList<DataList>();
	
	//data yang ditampung dalam vector
	public Vector<Relasi> vData; 
	
	DataAdapter adapter;
	
	public MainActivity() {
		// TODO Auto-generated constructor stub
		
		//isi data, untuk data dummy
		alData.add(new DataList(1, "Giri", "1000231", "Jl. G. Bromo 1 No.14", "085321"));
		alData.add(new DataList(2, "Hasan", "2000232", "Gerlong 1", "08142"));
		alData.add(new DataList(3, "Kholis", "3000233", "Gerlong 2", "08413"));
		alData.add(new DataList(4, "Fakhrul", "4000234", "Gerlong 3", "08512"));
		alData.add(new DataList(5, "Herdi", "5000235", "Gerlong 4", "082132"));
	}
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		updateDataList();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		updateDataList();
		
		ListView lv = (ListView)findViewById(R.id.lvMahasiswa);
		
		adapter = new DataAdapter(this, R.layout.row, alData);
		
		lv.setAdapter(adapter);
		lv.setClickable(true);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View viewRow, int posisi,
					long id) {
				// TODO Auto-generated method stub
				TextView tv = (TextView) viewRow.findViewById(R.id.tvJudul);
				tv.setTextColor(Color.RED);
				
				Intent iActDua = new Intent(getApplicationContext(), ActivityDua.class);
				
				DataList dataKirim = alData.get(posisi);
				
				iActDua.putExtra("datakirim", dataKirim);
				
				
				startActivityForResult(iActDua, 98);
				
			}
			
		});
		
		
		
	}
	
	//update tampilan list
	private void updateDataList(){
		//menghapus ArrayList yang sudah ada agar diganti isinya
		alData.clear();

		
		//ambil data dari database
		vData = fetchData();
		
		if (vData.size() > 0) {
			for (int i = 0; i <vData.size();i++) {
				int id = vData.get(i).id;
				String nama = vData.get(i).nama;
				String nim = vData.get(i).nim;
				String alamat = vData.get(i).alamat;
				String hape = vData.get(i).hape;
				
				alData.add(new DataList(id, nama, nim, alamat, hape));
				System.out.println("NAMA:" + nama);
			}
		}
		
		
		
		// Update data yang ada
		ListView lv = (ListView)findViewById(R.id.lvMahasiswa);		
		lv.setAdapter(adapter);
		
	}
	
	//mengambil data
	private Vector<Relasi> fetchData(){
		//testing mengakses data
		
		DbRelasi db = new DbRelasi(this);
		db.open();
		
		//mendapatkan semua data dalam tabel
		Vector<Relasi> data = db.getRelasi();
		
		db.close();
		
		return data;
	}
	
	
	public void buttonCari(View v){
		EditText tv = (EditText) findViewById(R.id.etCari);
		
		Intent iActDua = new Intent(getApplicationContext(), ActivityDua.class);
		
		DbRelasi db = new DbRelasi(this);
		db.open();
		
		String NIM = tv.getText().toString();
		
		//mendapatkan semua data dalam tabel
		Relasi data = db.getRelasiByNIM(NIM);
		
		db.close();
	
		
		if (data.nim != null) {
			
			DataList dataKirim = new DataList(data.id, data.nama, data.nim, data.alamat, data.hape);
			
			iActDua.putExtra("datakirim", dataKirim);
			
			startActivityForResult(iActDua, 98);
		}else{
			Toast t = Toast.makeText(this, "Tidak ditemukan NIM tersebut", Toast.LENGTH_SHORT);
			t.show();
		}
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		Toast t;
		switch(item.getItemId()){
		
		case R.id.menu_tambah:
			//pilih menu tambah
			Intent iActivityTambah = new Intent(this, ActivityTambah.class);
			startActivityForResult(iActivityTambah, 90);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		
		//update
		if ((requestCode == 98) && (resultCode == RESULT_OK)) {
			
			DataList dataterima = (DataList)data.getSerializableExtra("dataterima");
			
			//proses update di database
			DbRelasi dbrelasi = new DbRelasi(this);
			dbrelasi.open();
			dbrelasi.updateRelasi(dataterima.id, dataterima.judul, dataterima.keterangan, dataterima.alamat, dataterima.hape);
			dbrelasi.close();

			//update array di tampilan
			adapter.notifyDataSetChanged();
			updateDataList();
			
			
 
		//add
		}else if ((requestCode == 90) && (resultCode == RESULT_OK)) {
			DataList dataterima = (DataList)data.getSerializableExtra("dataterima");
			
			
			if (dataterima != null) {
				//tambah data
				
				DbRelasi dbrelasi = new DbRelasi(this);
				dbrelasi.open();
				dbrelasi.insertRelasi(dataterima.judul, dataterima.keterangan, dataterima.alamat, dataterima.hape);
				dbrelasi.close();
			}
			
			//update array di tampilan
			adapter.notifyDataSetChanged();
			updateDataList();	
			
		//delete
		}else if ((requestCode == 98) && (resultCode == 1)) {
			System.out.println("Menghapus");
			int id_terima = data.getIntExtra("id_terima", -1);
			
			String id_str = String.valueOf(id_terima);
			
			if (id_terima != -1) {
				DbRelasi dbrelasi = new DbRelasi(this);
				dbrelasi.open();
				dbrelasi.deleteRelasi(id_str);
				dbrelasi.close();
			}
			
			//update array di tampilan
			adapter.notifyDataSetChanged();
			updateDataList();	
		}
	}
}
