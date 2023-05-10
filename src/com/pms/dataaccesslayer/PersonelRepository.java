package com.pms.dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.pms.model.Cinsiyet;
import com.pms.model.Personel;

public class PersonelRepository implements IDAL<Personel>, IDALCountable {
	
	private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/PersonelYonetimDB" ;
	private static final String DB_USERNAME = "postgres";
	private static final String DB_PASSWORD = "123";
	private static final String COUNT_QUERY = "SELECT COUNT(*) FROM personeller";
	private static final String LIST_QUERY = "SELECT * FROM personeller";
	private final static int CINSIYET_ERKEK = 1;
	private final static String ENGELLI = "Engelli";
	private static final String INSERT_STATEMENT = "INSERT INTO public.personeller("
			+ "ad, soyad, dogum_yeri, dogum_tarihi, maas, engelli_mi, cinsiyet) ";
			
	
	private PersonelRepository() {
		// TODO Auto-generated constructor stub
	}

	private static PersonelRepository obj = null;
	
	public static PersonelRepository getInstance() {
		if(obj == null) {
			obj = new PersonelRepository();
		}
		
		return obj;
	}
	
	public int getCount() throws SQLException {
		int personelSayisi = 0;
		
		Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
		Statement countQuery = conn.createStatement();
		
		ResultSet rs = countQuery.executeQuery(COUNT_QUERY);
		
		while(rs.next()) {
			personelSayisi = rs.getInt(1);
		}
		
		return personelSayisi;
	}
	
	@Override
	public List<Personel> listele() throws SQLException {
		
		List<Personel> liste = new ArrayList<>();
		
		Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
		Statement listeQuery = conn.createStatement();
		
		ResultSet rs = listeQuery.executeQuery(LIST_QUERY);
		
		while(rs.next()) {
			
			Personel personel = new Personel();
			
			personel.setId(rs.getInt(1));
			personel.setAd(rs.getString(2));
			personel.setSoyad(rs.getString(3));
			
			personel.setDogumYeri(rs.getString(4));
			personel.setDogumTarihi(rs.getDate(5).toLocalDate());
			personel.setMaas(rs.getDouble(6));
			personel.setEngelliMi(rs.getBoolean(7));
			personel.setCinsiyet( CINSIYET_ERKEK == rs.getInt(8) ? Cinsiyet.ERKEK: Cinsiyet.KADIN);
			
			
			liste.add(personel);
		}
		
		return liste;
	}

	@Override
	public void ekle(Personel p) {
		
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
			Statement insertStatement = conn.createStatement();
			
			String values = String.format(" VALUES('%s','%s','%s','%s',%.2f,%b,%d)", 
					p.getAd(),p.getSoyad(),p.getDogumYeri(),p.getDogumTarihi().toString(), 
					p.getMaas(), p.isEngelliMi(), (p.getCinsiyet() == Cinsiyet.ERKEK ? 1 : 0));
			
			values = values.replace(",00", ".00");
			
			insertStatement.executeUpdate(INSERT_STATEMENT + values);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sil(int silinecekId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guncelle(Personel secilenId) {
		// TODO Auto-generated method stub
		
	}

}
