//package / folder java lokasi dimana java diletakan
package com.p2.tugas;

//mengimport library yang dibutuhkan untuk JDBC dengan menu pilihan

//untuk Membuat Koneksi
import java.sql.Connection;
//untuk menyambungkan koneksi database ke driver
import java.sql.DriverManager;
//untuk mempersiapkan statement query
import java.sql.PreparedStatement;
//Untuk Menghandle Error Sql
import java.sql.SQLException;
//untuk mengontrol output logging Level
import java.util.logging.Level;
//untuk mengontrol output logging logger
import java.util.logging.Logger;
//untuk membuat type data BigDecimal
import java.math.BigDecimal;
//untuk mengimput Data
import java.util.Scanner;
//untuk membuat type data String
import java.lang.String;
//untuk menampilkan hasil dari query yang diekseksusi
import java.sql.ResultSet;
//untuk membuat format Date
import java.text.SimpleDateFormat;
//untuk membuat type data Date
import java.util.Date;
//untuk menghandle Error Konversi Date
import java.text.ParseException;
//menghendle error kontruktor
import java.lang.reflect.InvocationTargetException;

public class Main 
{
	public static void TampilMahasiswa(){
		try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
					//string untuk query
					String sqlTampil = "SELECT * FROM mahasiswa ";
					//menyiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//mengeksekusi query
					ResultSet rs = psTampil.executeQuery();
					//menampilkan tabel setelah query dieksekusi
					System.out.println("|\tnim\t|\tnama\t|");
					//perulangan untuk menampilkan isi dari tabel yang telah dieksekusi
					while(rs.next()){
						System.out.print("|\t"+rs.getString("nim")+"\t");
						System.out.print("|\t"+rs.getString("nama")+"\t|");
						System.out.println();
					}
					//menutup koneksi
					c.close();
		}catch(SQLException se){
					//membuang error sql 
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void TampilMataKuliahAmbil(){
		try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
					//string untuk query
					String sqlTampil = "SELECT nim,nama,kode, namaMK FROM mahasiswa m, mk_ambil mka, matkul mk WHERE m.id = mka.idMhs AND mk.id = mka.idMk";
					//menyiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//mengeksekusi query
					ResultSet rs = psTampil.executeQuery();
					//menampilkan tabel setelah query dieksekusi
					System.out.println("|\tnim\t|\tnama\t|\tkodeMK\t|\tnamaMK\t|");
					//perulangan untuk menampilkan isi dari tabel yang telah dieksekusi
					while(rs.next()){
						System.out.print("|\t"+rs.getString("nim")+"\t");
						System.out.print("|\t"+rs.getString("nama")+"\t");
						System.out.print("|\t"+rs.getString("kode")+"\t");
						System.out.print("|\t"+rs.getString("namaMK")+"\t|");
						System.out.println();
					}
					//menutup koneksi
					c.close();
		}catch(SQLException se){
					//membuang error sql 
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void InputMahasiswa(String nim,String nama,String alamat,int semester){
		try{
			//membuat koneksi ke database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
			//menyiapkan query ke dalam string
			String query = "INSERT INTO mahasiswa(nim,nama,alamat,smster) values (?,?,?,?)";
			//menyiapkan query statement untuk dieksekusi
			PreparedStatement ps = conn.prepareStatement(query);
			//penginputan query pada setiap ?  
			ps.setString(1,nim);
			ps.setString(2,nama);
			ps.setString(3,alamat);
			ps.setInt(4,semester);
			//mengeksekusi statement query
			ps.executeUpdate();
		}catch(SQLException se){
			//menghandle error sql
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void EditMahasiswa(String nimcari,String nim,String nama,String alamat,int semester){
		try{
			//membuat koneksi ke database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
			//menyiapkan query ke dalam string
			String query = "UPDATE mahasiswa SET nim = ?,nama = ?,alamat = ?,smster = ? WHERE nim = ?";
			//menyiapkan query statement untuk dieksekusi
			PreparedStatement ps = conn.prepareStatement(query);
			//penginputan query pada setiap ?  
			ps.setString(1,nim);
			ps.setString(2,nama);
			ps.setString(3,alamat);
			ps.setInt(4,semester);
			ps.setString(5,nimcari);
			//mengeksekusi statement query
			ps.executeUpdate();
		}catch(SQLException se){
			//menghandle error sql
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void TampilMataKuliah(){
		try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
					//string untuk query
					String sqlTampil = "SELECT * FROM matkul ";
					//menyiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//mengeksekusi query
					ResultSet rs = psTampil.executeQuery();
					//menampilkan tabel setelah query dieksekusi
					System.out.println("|\tkode\t|\tnama\t|\tjumlah sks\t|");
					//perulangan untuk menampilkan isi dari tabel yang telah dieksekusi
					while(rs.next()){
						System.out.print("|\t"+rs.getString("kode")+"\t");
						System.out.print("|\t"+rs.getString("namaMK")+"\t");
						System.out.print("|\t"+rs.getString("jmlSKS")+"\t|");
						System.out.println();
					}
					//menutup koneksi
					c.close();
		}catch(SQLException se){
					//membuang error sql 
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void TampilPilihanMataKuliah(){
		try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
					//string untuk query
					String sqlTampil = "SELECT * FROM matkul ";
					//menyiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//mengeksekusi query
					ResultSet rs = psTampil.executeQuery();
					//perulangan untuk menampilkan isi dari tabel yang telah dieksekusi
					int loop = 1;
					while(rs.next()){
						System.out.print(loop+"."+rs.getString("namaMK")+" - ");
						System.out.print(rs.getString("jmlSKS"));
						System.out.println();
						loop++;
					}
					//menutup koneksi
					c.close();
		}catch(SQLException se){
					//membuang error sql 
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void InputPilihanMataKuliah(int noMK,int idMhs){
		try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
					//string untuk query
					String sqlTampil = "SELECT * FROM matkul ";
					//menyiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//mengeksekusi query
					ResultSet rs = psTampil.executeQuery();
					//perulangan untuk menampilkan isi dari tabel yang telah dieksekusi
					int loop = 1;
					while(rs.next()){
						if(loop == noMK){
							try{
								//membuat koneksi ke database
								Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
								//menyiapkan query ke dalam string
								String query2 = "INSERT INTO mk_ambil(idMhs,idMk) values (?,?)";
								//menyiapkan query statement untuk dieksekusi
								PreparedStatement ps2 = conn2.prepareStatement(query2);
								//penginputan query pada setiap ?  
								ps2.setInt(1,idMhs);
								ps2.setInt(2,rs.getInt("id"));
								//mengeksekusi statement query
								ps2.executeUpdate();
							}catch(SQLException se2){
								//membuang error sql 
								Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se2);
							}
						}
						loop++;
					}
					//menutup koneksi
					c.close();
		}catch(SQLException se){
					//membuang error sql 
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
	public static void TambahMataKuliah(String kode,String namaMK,int jumSks,java.sql.Date date){
		try{
			//membuat koneksi ke database
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
			//menyiapkan query ke dalam string
			String query = "INSERT INTO matkul(kode,namaMK,jmlSKS,tglUas) values (?,?,?,?)";
			//menyiapkan query statement untuk dieksekusi
			PreparedStatement ps = conn.prepareStatement(query);
			//penginputan query pada setiap ?  
			ps.setString(1,kode);
			ps.setString(2,namaMK);
			ps.setInt(3,jumSks);
			ps.setDate(4,date);
			//mengeksekusi statement query
			ps.executeUpdate();
		}catch(SQLException se){
			//menghandle error sql
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
		}
	}
    public static void main( String[] args )
    {
		//inisialisasi tipe data Scanner untuk menginput data ke variabel data
		Scanner data = new Scanner(System.in);
		//variabel untuk pemilihan
		int pilih = 0;
		int pilih2 = 0;
			System.out.println("=================");
			System.out.println("Nama : Fajar Muhammad F");
			System.out.println("Nim : 4511210022");
		while(pilih != 7){
			System.out.println("=================");
			//Menampilkan Keterangan menu pilihan
			System.out.println("MENU PILIHAN");
			System.out.println("1. Tampil Daftar Mahasiswa");
			System.out.println("2. Input Mahasiswa");
			System.out.println("3. Pencarian Mahasiswa");
			System.out.println("4. Tampil Daftar Mata Kuliah");
			System.out.println("5. Tambah Mata Kuliah");
			System.out.println("6. Tampil Daftar Mahasiswa + Mata Kuliah yang diambil");
			System.out.println("7. Keluar");
			System.out.print("Masukan Pilihan Anda : ");
			//menginput data ke variabel pilih
			pilih = data.nextInt();
			//untuk inputan digeser ke baris baru agar dapat menginput string dengan spasi
			data.nextLine();
			if(pilih == 1){
				System.out.println("=================");
				//Memanggil fungsi TampilMahasiswa
				TampilMahasiswa();
				System.out.println("=================");				
			}
			else if(pilih == 2){
				System.out.println("=================");
				System.out.print("Masukan nim : ");
				//Menginput Data dan memasukan ke dalam variabel nim
				String nim = data.nextLine();
				System.out.print("Masukan Nama : ");
				//Menginput Data dan memasukan ke dalam variabel nama
				String nama = data.nextLine();
				System.out.print("Masukan Alamat : ");
				//Menginput Data dan memasukan ke dalam variabel alamat
				String alamat = data.nextLine();
				
				//Memanggil fungsi InputMahasiswa dengan memasukan parameter
				InputMahasiswa(nim,nama,alamat,1);
				
			}
			else if(pilih == 3){
				System.out.println("=================");
				System.out.print("Masukan Nim : ");
				//menginput data yang ingin dicari
				String cari = data.nextLine();
				try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
					//query dalam bentuk string
					String sqlTampil = "SELECT * FROM mahasiswa ";
					//mempersiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//Hasil eksekusi query dimasukan kedalam variabel rs 
					ResultSet rs = psTampil.executeQuery();
					//Membuat variable untuk menghitung pencarian saat tidak bertemu
					int hitungPencarian = 1;
					//menampilkan hasil query
					while(rs.next()){
						//Jika Pencarian Data Ditemukan
						if(cari.equals(rs.getString("nim"))){
							System.out.println("=================");
							System.out.println("Data Ditemukan");
							System.out.println("=================");
							//Menampilkan nim beserta Isi
							System.out.println("nim : "+rs.getString("nim"));
							//Menampilkan nama beserta Isi
							System.out.println("nama : "+rs.getString("nama"));
							//Menampilkan alamat beserta Isi
							System.out.println("alamat : "+rs.getString("alamat"));
							//Menampilkan semester beserta Isi
							System.out.println("semester : "+rs.getString("smster"));
							System.out.println("=================");
							do{
							System.out.println("=================");
							System.out.println("MENU PILIHAN");
							System.out.println("1.Edit Mahasiswa");
							System.out.println("2.Tambah MataKuliah");
							System.out.println("3.Keluar");
							System.out.print("Pilih : ");
							//Memasukan Data dan memasukan ke variabel pilih2
							pilih2 = data.nextInt();
							//untuk inputan digeser ke baris baru agar dapat menginput string dengan spasi
							data.nextLine();
							//Menu Pemilihan Kedua 
							//Jika Pilih2 = 1
							if(pilih2 == 1){
								System.out.println("=================");
								System.out.print("Masukan nim : ");
								//Menginput Data ke dalam variabel nim
								String nim = data.nextLine();
								System.out.print("Masukan Nama : ");
								//Menginput Data ke dalam variabel nama
								String nama = data.nextLine();
								System.out.print("Masukan Alamat : ");
								//Menginput Data ke dalam variabel alamat
								String alamat = data.nextLine();
								System.out.print("Masukan Semester : ");
								//Menginput Data ke dalam variabel semester
								int semester = data.nextInt();
								
								//Memanggila fungsi EditMahasiswa dengan memasukan parameter
								EditMahasiswa(cari,nim,nama,alamat,semester);
								
							}//Jika Pilih2 = 2
							else if(pilih2 == 2){
								System.out.println("=================");
								System.out.println("Pilihan Mata Kuliah");
								//Memanggil fungsi TampilPilihanMataKuliah
								TampilPilihanMataKuliah();
								System.out.print("Masukan No Mata Kuliah : ");
								//Menginput Data dan memasukan ke variabel noMK
								int noMK = data.nextInt();
								//Memanggil fungsi InputPilihanMataKuliah dan memasukan parameternya
								InputPilihanMataKuliah(noMK,rs.getInt("id"));
							}
							}while(pilih2 != 3);
							hitungPencarian = 0;
						}
					}
					//Data Tidak Ditemukan
					if(hitungPencarian == 1){
						System.out.println("=================");
						System.out.println("Data Tidak Ditemukan");
						System.out.println("=================");
					}
					//menutup koneksi database
					c.close();
				}catch(SQLException se){
					//membuang error query
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
			}
			else if(pilih == 4){
				System.out.println("=================");
				//Memanggil Fungsi TampilMataKuliah
				TampilMataKuliah();
				System.out.println("=================");
			}
			else if(pilih == 5){
				System.out.println("=================");
				System.out.print("Masukan kode : ");
				//Menginput Data dan memasukan ke variabel kode
				String kode = data.nextLine();
				System.out.print("Masukan Nama : ");
				//Menginput Data dan memasukan ke variabel namaMK
				String namaMK = data.nextLine();
				System.out.print("Masukan Jumlah SKS : ");
				//Menginput Data dan memasukan ke variabel jumSks
				int jumSks = data.nextInt();
				
				//Membuat Objek Date baru
				Date utilDate = new Date();
				//membuat Objek Date Sql dari Objek Date Java
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				
				//Memanggil FungsiTambahMataKuliah dengan memasukan parameternya
				TambahMataKuliah(kode,namaMK,jumSks,date);
				System.out.println("=================");
			}
			else if(pilih == 6){
				System.out.println("=================");
				//Memanggil Fungsi TampilMataKuliahAmbil
				TampilMataKuliahAmbil();
				System.out.println("=================");
			}
		}
		//Keterangan setelah keluar Program
		System.out.println("Terima Kasih Sudah Menggunakan Program Ini");
    }
}
