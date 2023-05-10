package com.pms.dataaccesslayer;

import java.io.FileNotFoundException;
import java.util.List;

import com.pms.model.Personel;

public interface IDAL<T> { // CRUD

	List<T> listele() throws Exception; // public abstract

	void ekle(T t) throws Exception;

	void sil(int silinecekId);

	void guncelle(T secilenId) throws Exception;

}
