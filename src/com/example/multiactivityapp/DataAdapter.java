package com.example.multiactivityapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class DataAdapter extends ArrayAdapter<DataList> {
	private ArrayList<DataList> alData;
	
	public DataAdapter(Context con, int textViewResourceId, ArrayList<DataList> alData){
		super(con, textViewResourceId, alData);
		this.alData = alData;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		if (v == null) {
			LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = li.inflate(R.layout.row, null);
		}
		
		DataList dl = alData.get(position);
		
		if (dl != null) {
			TextView tvJudul = (TextView) v.findViewById(R.id.tvJudul);
//			TextView tvKeterangan = (TextView) v.findViewById(R.id.tvKeterangan);
			tvJudul.setText(dl.judul);
//			tvKeterangan.setText(dl.keterangan);
		}
		//return super.getView(position, convertView, parent);
		
		return v;
	}
}
