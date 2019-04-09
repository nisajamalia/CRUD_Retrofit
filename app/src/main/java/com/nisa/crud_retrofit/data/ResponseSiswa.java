package com.nisa.crud_retrofit.data;

import java.util.List;



public class ResponseSiswa{


	private List<SiswaItem> siswa;

	public ResponseSiswa(List<SiswaItem> siswa) {
		this.siswa = siswa;
	}

	private String pesan;


	private boolean sukses;

	public void setSiswa(List<SiswaItem> siswa){
		this.siswa = siswa;
	}

	public List<SiswaItem> getSiswa(){
		return siswa;
	}

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}