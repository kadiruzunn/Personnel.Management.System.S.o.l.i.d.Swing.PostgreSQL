package com.pms.model;

import java.time.LocalDate;

public class Personel {
	private static final double MAXIMUM_MAAS = 50000;
	private static final double MINIMUM_MAAS = 8500;
	private static final String MAAS_HATA_MESAJI = "Maaş %.2f ve %.2f aralığında olmalıdır.";
	
	private int id;
	private String ad;
	private String soyad;
	private String dogumYeri;
	private LocalDate dogumTarihi;
	private double maas;
	private boolean engelliMi;
	private Cinsiyet cinsiyet;
	
	public boolean isEngelliMi() {
		return engelliMi;
	}

	public void setEngelliMi(boolean engelliMi) {
		this.engelliMi = engelliMi;
	}

	public Personel() {
		// TODO Auto-generated constructor stub
	}

	public Personel(String ad, String soyad, String dogumYeri, LocalDate dogumTarihi, 
			double maas, boolean engelliMİ, Cinsiyet cinsiyet) {
		this.ad = ad;
		this.soyad = soyad;
		this.dogumYeri = dogumYeri;
		this.dogumTarihi = dogumTarihi;
		if(maas > MAXIMUM_MAAS || maas < MINIMUM_MAAS) {
			throw new IllegalArgumentException(String.format(MAAS_HATA_MESAJI, MINIMUM_MAAS, MAXIMUM_MAAS)); // IllegalArgumentException fırlatılması programcıya bırakılmıştır.
		}
		this.maas = maas;
		this.engelliMi = engelliMİ;
		this.cinsiyet = cinsiyet;
	}
	
	public Cinsiyet getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(Cinsiyet cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public int getYas() {
		return LocalDate.now().getYear() - this.dogumTarihi.getYear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getDogumYeri() {
		return dogumYeri;
	}

	public void setDogumYeri(String dogumYeri) {
		this.dogumYeri = dogumYeri;
	}

	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public double getMaas() {
		return maas;
	}

	public void setMaas(double maas) {
		if(maas > MAXIMUM_MAAS || maas < MINIMUM_MAAS) {
			throw new IllegalArgumentException(MAAS_HATA_MESAJI); 
		}
		
		this.maas = maas;
	}
	
	public String toCSVString() {
		return String.format("%d;%s;%s;%s;%s;%.2f;%s;%s;\r",
				id, ad,soyad,dogumYeri, dogumTarihi.toString(),maas,
				cinsiyet, engelliMi == true ? "Engelli":"Değil");
	}
	
	

}
