package com.pms.businesslogiclayer;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pms.dataaccesslayer.IDAL;
import com.pms.dataaccesslayer.IDALCountable;
import com.pms.dataaccesslayer.PersonelDAL;
import com.pms.dataaccesslayer.PersonelRepository;
import com.pms.model.Personel;

public class PersonelBLL {
	
	PersonelDAL dal =  PersonelDAL.getInstance();
	
	List<Personel> liste = new ArrayList<>();
	
	IDALCountable repository = PersonelRepository.getInstance(); 
	
	IDAL dbDAL = PersonelRepository.getInstance();
	
	public int getPersonelSayisi() throws SQLException {
		return repository.getCount();
	}
	
	public List<Personel> listele() throws Exception{
		
		
		
		List<Personel> liste = dbDAL.listele();
		
		return liste;
		
	}
	
	public void ekle(Personel personel)  {
		try {
			dbDAL.ekle(personel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void sil(int silinecekId) {
		dal.sil(silinecekId);
		
	}

	public void guncelle(Personel secilenPersonel) throws FileNotFoundException {
		
		dal.guncelle(secilenPersonel);
		
	}

}
