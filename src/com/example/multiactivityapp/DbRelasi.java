package com.example.multiactivityapp;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbRelasi {

	public static class Relasi{
		public int id;
		public String nama;
		public String nim;
		public String alamat;
		public String hape;
	}

	private SQLiteDatabase db;
	private final Context con;
	private final RelasiOpenHelper dbHelper;
	
	public DbRelasi(Context c) {
		// TODO Auto-generated constructor stub
		con = c;
		dbHelper = new RelasiOpenHelper(con, "", null, 0);
	}
	
	public void open(){
		db = dbHelper.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public void insertRelasi(String nama, String nim, String alamat, String hape){
		ContentValues newValues = new ContentValues();
		newValues.put("NAMA", nama);
		newValues.put("NIM", nim);
		newValues.put("ALAMAT", alamat);
		newValues.put("HAPE", hape);
		
		db.insert("RELASI", null, newValues);
	}
	
	public long updateRelasi(int id, String nama, String nim, String alamat, String hape){
		
		ContentValues updateValues = new ContentValues();
		updateValues.put("NAMA", nama);
		updateValues.put("NIM", nim);
		updateValues.put("ALAMAT", alamat);
		updateValues.put("HAPE", hape);
		
		return db.update("RELASI", updateValues, "ID ='"+id+"'", null);
		
	}
	
	public Relasi getRelasi (String id) {
		Cursor cur = null;
		Relasi R = new Relasi();
		
		//kolom yang akan diambil
		String[] COLS = new String[] {"ID", "NAMA", "NIM", "ALAMAT", "HAPE"};
		//query
		cur = db.query("RELASI", COLS, "ID ='"+id+"'", null, null, null, null);

		if (cur.getCount() > 0) { //ada data
			cur.moveToFirst();
			R.id = cur.getInt(0);
			R.nama = cur.getString(1);
			R.nim = cur.getString(2);
			R.alamat = cur.getString(3);
			R.hape = cur.getString(4);
		}
		
		return R;		
	}
	
	public long deleteRelasi(String id){
		
		return db.delete("RELASI", "ID = '"+id+"'", null);
	}
	
	public Vector<Relasi> getRelasi() {
		
		
		Cursor curr = null;
		//Relasi R[];
		
		Vector<Relasi> R = new Vector<DbRelasi.Relasi>();
		
		String[] COLS = new String[] {"ID", "NAMA", "NIM", "ALAMAT", "HAPE"};
		
		curr = db.query("RELASI", COLS,null, null, null, null, null);
		
		if (curr.getCount() > 0) {
			
			int jumlahBaris = curr.getCount();
			
			if (jumlahBaris > 0) {
				curr.moveToFirst();
			}
			
			for (int i =0; i < jumlahBaris; i++) {
				Relasi re = new Relasi();
				re.id = curr.getInt(0);
				re.nama = curr.getString(1);
				re.nim = curr.getString(2);
				re.alamat = curr.getString(3);
				re.hape = curr.getString(4);
				
				R.add(re);
				
				curr.moveToNext();
			}
			
		}
		
		return R;
		
	}
	
}
