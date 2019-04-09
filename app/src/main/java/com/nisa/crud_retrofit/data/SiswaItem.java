package com.nisa.crud_retrofit.data;

public class SiswaItem{


	private String nama;


	private String hp;


	private String id;


	private String alamat;

	public SiswaItem(String id, String nama, String alamat, String hp) {
		this.nama = nama;
		this.hp = hp;
		this.id = id;
		this.alamat = alamat;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setHp(String hp){
		this.hp = hp;
	}

	public String getHp(){
		return hp;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}