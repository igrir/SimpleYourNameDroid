package com.example.multiactivityapp;

import java.io.Serializable;

public class DataList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1411668254304654017L;
	public int id; //id dari si record
	public String judul;
	public String keterangan;
	public String alamat;
	public String hape;
	
	public DataList(int id, String judul, String keterangan, String alamat, String hape){
		this.id = id;
		this.judul = judul;
		this.keterangan = keterangan;
		this.alamat = alamat;
		this.hape = hape;
	}
}
