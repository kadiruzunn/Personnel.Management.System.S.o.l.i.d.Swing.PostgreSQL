package com.pms.dataaccesslayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pms.model.Cinsiyet;
import com.pms.model.Personel;

import java.lang.NumberFormatException;


public class PersonelDAL implements IDAL<Personel> {
	
	private final static String CINSIYET_ERKEK = "ERKEK";
	private final static String ENGELLI = "Engelli";
	private static int idCounter = 1; // 1 den başlayıp birer birer artacak
	
	// Singleton Design Pattern 1. Adım
	private PersonelDAL() {
		// TODO Auto-generated constructor stub
	}
	
	// Singleton Design Pattern 2. Adım
	private static PersonelDAL personelDAL;
	
	// Singleton Design Pattern 3. Adım
	public static PersonelDAL getInstance() {
		
		if(personelDAL == null) {
			personelDAL = new PersonelDAL();
		}
		
		return personelDAL;
	}
	
	private static final String PERSONEL_VERILERI_DOSYA_ADI = "personeller.csv";
	
	public List<Personel> listele() throws FileNotFoundException{
		List<Personel> liste = new ArrayList<>();
		

			File file = new File(PERSONEL_VERILERI_DOSYA_ADI);
			Scanner myReader = new Scanner(file);

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] arr = data.split(";");

				Personel personel = new Personel();
				personel.setId(Integer.valueOf(arr[0]));
				personel.setAd(arr[1]);
				personel.setSoyad(arr[2]);
				personel.setDogumYeri(arr[3]);
				personel.setDogumTarihi(LocalDate.parse(arr[4]));
				personel.setMaas(Double.parseDouble(arr[5].replace(",",".")));
				personel.setCinsiyet( CINSIYET_ERKEK.equals(arr[6]) ? Cinsiyet.ERKEK: Cinsiyet.KADIN);
				personel.setEngelliMi(ENGELLI.equals(arr[7]));
				
				liste.add(personel);

			}
			myReader.close();
		

		return liste;
//		// Mock data
//		Personel p1 = new Personel("Ali", "Veli", "Ankara", LocalDate.of(2000, 1, 20), 10000, false, Cinsiyet.ERKEK);
//		Personel p2 = new Personel("Fatma", "Şen", "İzmir", LocalDate.of(2003, 2, 15), 20000, true, Cinsiyet.KADIN);
//		Personel p3 = new Personel("Tansel", "Gür", "Van", LocalDate.of(2005, 3, 13), 15000, false, Cinsiyet.ERKEK);
//		Personel p4 = new Personel("Ayşe", "Tan", "Bolu", LocalDate.of(2000, 6, 20), 12000, false, Cinsiyet.KADIN);
//		
//		liste.add(p1);
//		liste.add(p2);
//		liste.add(p3);
//		liste.add(p4);
		
//		return liste;
	}
	
	public void ekle(Personel personel ) throws FileNotFoundException {
		
		List<Personel> personelListesi = listele();

		if (personelListesi.size() > 0) {
			int sonIndex = personelListesi.size() - 1;
			
			Personel listedekiSonOgrenci = personelListesi.get(sonIndex);
			int sonId = listedekiSonOgrenci.getId();
			idCounter = sonId + 1;
		} 

		personel.setId(idCounter);
		
		try {
			FileWriter myWriter = new FileWriter("personeller.csv", Charset.forName("UTF8"),true);
			myWriter.write(personel.toCSVString());
			myWriter.close();
			System.out.println("kaydedildi");
		} catch (IOException e) {
			System.out.println("");
			e.printStackTrace();
		}
		
		
	}

	public void sil(int silinecekId) {
		// TODO Auto-generated method stub
		
	}

	public void guncelle(Personel secilenPersonel) throws FileNotFoundException {
		List<Personel> liste = listele();

		int guncellenecekIndex = -1;
		
		for (int i = 0; i < liste.size(); i++) {
			if(secilenPersonel.equals(liste.get(i))) {
			//if (liste.get(i).getId() == obj.getId()) {
				guncellenecekIndex = i;
				break;
			}
		}
		
		if(guncellenecekIndex != -1) {
			
		//	liste.set(guncellenecekIndex, obj);
			
			//dosyadakiVerileriSil();

			//dosyayaTopluVeriEkle(liste);
		}
		
	}

}
