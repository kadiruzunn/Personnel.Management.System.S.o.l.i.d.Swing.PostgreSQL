package com.pms.presentationlayer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.pms.businesslogiclayer.PersonelBLL;
import com.pms.model.Cinsiyet;
import com.pms.model.Personel;

public class PersonelYonetimEkrani extends JFrame {

	private static final double MAXIMUM_MAAS = 50000;
	private static final double MINIMUM_MAAS = 8500;
	private static final String MAAS_HATA_MESAJI = "Maaş %.2f ve %.2f aralığında olmalıdır.";
	
	
	private JPanel contentPane;
	private List<Personel> personelListesi;
	private PersonelBLL bll;
	private JTable table;
	private JTextField txtAd;
	private JTextField txtSoyad;
	private JTextField txtDogumTarihi;
	private JTextField txtMaas;
	private JComboBox cmbDogumYeri;
	private JButton btnSil;
	private JButton btnSec;
	private JButton btnGuncelle;
	private JCheckBox chbEngelli;
	private JButton btnEkle;
	private JRadioButton rdbKadin;
	private JRadioButton rdbErkek;
	private JLabel lblPersonelSayisi;
	
	public PersonelYonetimEkrani() {

		bll = new PersonelBLL();
		try {
			personelListesi = bll.listele();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("Personel Yönetim Ekranı");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(new Color(255, 255, 0));
		table.setBounds(10, 0, 1, 1);
		// contentPane.add(table);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 255, 464, 120);
		contentPane.add(scrollPane);

		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table); 

		JLabel lblNewLabel = new JLabel("Ad:");
		lblNewLabel.setBounds(20, 11, 46, 14);
		contentPane.add(lblNewLabel);

		txtAd = new JTextField();
		txtAd.setBounds(91, 11, 122, 20);
		contentPane.add(txtAd);
		txtAd.setColumns(10);

		JLabel lblSoyad = new JLabel("Soyad:");
		lblSoyad.setBounds(20, 48, 46, 14);
		contentPane.add(lblSoyad);

		txtSoyad = new JTextField();
		txtSoyad.setColumns(10);
		txtSoyad.setBounds(91, 48, 122, 20);
		contentPane.add(txtSoyad);

		JLabel lblDoumYeri = new JLabel("Doğum Tarihi:");
		lblDoumYeri.setBounds(20, 138, 76, 14);
		contentPane.add(lblDoumYeri);

		cmbDogumYeri = new JComboBox();
		cmbDogumYeri.setModel(new DefaultComboBoxModel(new String[] { "Ankara", "İzmir", "İstanbul", "Van", "Bolu" }));
		cmbDogumYeri.setBounds(91, 91, 122, 22);
		contentPane.add(cmbDogumYeri);

		JLabel lblDoumYeri_1 = new JLabel("Doğum Yeri:");
		lblDoumYeri_1.setBounds(20, 95, 61, 14);
		contentPane.add(lblDoumYeri_1);

		txtDogumTarihi = new JTextField();
		txtDogumTarihi.setColumns(10);
		txtDogumTarihi.setBounds(91, 135, 122, 20);
		contentPane.add(txtDogumTarihi);

		JLabel lblMaa = new JLabel("Maaş:");
		lblMaa.setBounds(20, 177, 61, 14);
		contentPane.add(lblMaa);

		txtMaas = new JTextField();
		txtMaas.setColumns(10);
		txtMaas.setBounds(91, 174, 122, 20);
		contentPane.add(txtMaas);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personelEkle();
			}
		});
		btnEkle.setBounds(30, 205, 89, 23);
		contentPane.add(btnEkle);

		btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int secilenIndex = table.getSelectedRow();

				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnEkle, "Önce silinecek kaydı seçiniz!");
					return;
				}

				personelSil(secilenIndex);
			}
		});
		btnSil.setBounds(129, 205, 89, 23);
		contentPane.add(btnSil);

		btnSec = new JButton("Kayıt Seç");
		btnSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int secilenIndex = table.getSelectedRow();

				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnEkle, "Önce silinecek kaydı seçiniz!");
					return;
				}
				
				verileriGetir(secilenIndex);
			}

			
		});
		btnSec.setBounds(237, 205, 89, 23);
		contentPane.add(btnSec);
		
		btnGuncelle = new JButton("Güncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int secilenIndex = table.getSelectedRow();

				if (secilenIndex == -1) {
					JOptionPane.showMessageDialog(btnEkle, "Önce silinecek kaydı seçiniz!");
					return;
				}
				
				
				
				veriGuncelle(secilenIndex);
			}

			
		});
		btnGuncelle.setBounds(336, 205, 89, 23);
		contentPane.add(btnGuncelle);
		
		chbEngelli = new JCheckBox("Engelli");
		chbEngelli.setBounds(272, 7, 97, 23);
		contentPane.add(chbEngelli);
		
		rdbKadin = new JRadioButton("Kadın");
		rdbKadin.setBounds(280, 57, 61, 23);
		rdbKadin.setSelected(true);
		contentPane.add(rdbKadin);
		
		rdbErkek = new JRadioButton("Erkek");
		rdbErkek.setBounds(343, 57, 61, 23);
		contentPane.add(rdbErkek);
		
		ButtonGroup btgCinsiyet = new ButtonGroup();
		btgCinsiyet.add(rdbErkek);
		btgCinsiyet.add(rdbKadin);
		
		JLabel lblNewLabel_1 = new JLabel("Cinsiyet");
		lblNewLabel_1.setBounds(280, 37, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblPersonelSayisi = new JLabel("");
		lblPersonelSayisi.setBounds(20, 230, 99, 14);
		contentPane.add(lblPersonelSayisi);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				int selectedIndex = table.getSelectedRow();
				
				if(selectedIndex != -1) {
					
					verileriGetir(selectedIndex);
					
				}
				
			}
		});
		
		personelListesiGoster();
	}

	private void veriGuncelle(int secilenIndex) {
		Personel secilenPersonel = personelListesi.get(secilenIndex);
		
		String ad = txtAd.getText();
		String soyad = txtSoyad.getText();
		String dogumYeri = cmbDogumYeri.getSelectedItem().toString();
		LocalDate dogumTarihi = LocalDate.parse(txtDogumTarihi.getText());
		double maas = Double.parseDouble(txtMaas.getText());
		
		secilenPersonel.setAd(ad);
		secilenPersonel.setSoyad(soyad);
		secilenPersonel.setDogumYeri(dogumYeri);
		secilenPersonel.setDogumTarihi(dogumTarihi);
		secilenPersonel.setMaas(maas);
		
		try {
			bll.guncelle(secilenPersonel);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		personelListesiGoster();
		
	}
	
	private void verileriGetir(int kayitIndex) {
		
		Personel secilenPersonel = personelListesi.get(kayitIndex);
		
		txtAd.setText(secilenPersonel.getAd());
		txtSoyad.setText(secilenPersonel.getSoyad());
		cmbDogumYeri.setSelectedItem(secilenPersonel.getDogumYeri());
		txtDogumTarihi.setText(secilenPersonel.getDogumTarihi().toString());
		txtMaas.setText(String.valueOf(secilenPersonel.getMaas()));
		
	}
	
	private void personelSil(int silinecekIndex) {

		
		
		Personel silinecekPersonel = personelListesi.get(silinecekIndex);
		
		int silinecekId = silinecekPersonel.getId();
		
		bll.sil(silinecekId);
		personelListesiGoster();

	}

	private void personelEkle() {

		String ad = txtAd.getText();
		String soyad = txtSoyad.getText();
		String dogumYeri = cmbDogumYeri.getSelectedItem().toString();
		
		String strDogumTarihi = txtDogumTarihi.getText();
		
		if(strDogumTarihi.isEmpty()) {
			JOptionPane.showMessageDialog(btnEkle, "Doğum Tarihi alanı zorunludur!");
			return;
		}
		
		LocalDate dogumTarihi = LocalDate.parse(txtDogumTarihi.getText());
		double maas = Double.parseDouble(txtMaas.getText());
		

		
		boolean engelliMi = chbEngelli.isSelected();
		
		Cinsiyet cinsiyet = Cinsiyet.KADIN;
		
		if(rdbErkek.isSelected()) {
			cinsiyet = Cinsiyet.ERKEK;
		}

		try {
			Personel yeniPersonel = new Personel(ad, soyad, dogumYeri, dogumTarihi, maas, engelliMi, cinsiyet);
			
			
			try {
				bll.ekle(yeniPersonel);
				personelListesi = bll.listele();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			personelListesiGoster();
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(btnEkle, e.getMessage());
		}
		
	}

	private void personelListesiGoster() {
		
        try {
			lblPersonelSayisi.setText("Kayıt sayısı " + bll.getPersonelSayisi());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Veritabanı hatası oluştu");
			e.printStackTrace();
		}

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("Sıra No");
		model.addColumn("Ad");
		model.addColumn("Soyad");
		model.addColumn("Yaş");
		model.addColumn("Doğum Yeri");
		model.addColumn("Maaş");
		model.addColumn("Engelli");
		model.addColumn("Cinsiyet");

		Object[] row = new Object[8];

		int rowCount = personelListesi.size();

		int i = 1;

		for (Personel p : personelListesi) {
			row[0] = i++;
			row[1] = p.getAd();
			row[2] = p.getSoyad();
			row[3] = p.getYas(); 
			row[4] = p.getDogumYeri();
			row[5] = p.getMaas();
			row[6] = p.isEngelliMi() ? "Evet" : "Hayır";
			row[7] = p.getCinsiyet();
			model.addRow(row);

			
		}

		table.setModel(model);

	}
}
