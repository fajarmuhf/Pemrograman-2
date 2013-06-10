//package / folder java lokasi dimana java diletakan
package com.p2.tugas;

//mengimport library yang dibutuhkan untuk JDBC dengan menu pilihan
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;
import java.util.Scanner;
import java.lang.String;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
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
		while(pilih != 7){
			System.out.println("=================");
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
				TampilMahasiswa();
				System.out.println("=================");				
			}
			else if(pilih == 2){
				System.out.println("=================");
				System.out.print("Masukan nim : ");
				String nim = data.nextLine();
				System.out.print("Masukan Nama : ");
				String nama = data.nextLine();
				System.out.print("Masukan Alamat : ");
				String alamat = data.nextLine();
				
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
					//menampilkan hasil query
					while(rs.next()){
						if(cari.equals(rs.getString("nim"))){
							System.out.println("=================");
							System.out.println("nim : "+rs.getString("nim"));
							System.out.println("nama : "+rs.getString("nama"));
							System.out.println("alamat : "+rs.getString("alamat"));
							System.out.println("semester : "+rs.getString("smster"));
							System.out.println("=================");
							do{
							System.out.println("=================");
							System.out.println("MENU PILIHAN");
							System.out.println("1.Edit Mahasiswa");
							System.out.println("2.Tambah MataKuliah");
							System.out.println("3.Keluar");
							System.out.print("Pilih : ");
							pilih2 = data.nextInt();
							data.nextLine();
							if(pilih2 == 1){
								System.out.println("=================");
								System.out.print("Masukan nim : ");
								String nim = data.nextLine();
								System.out.print("Masukan Nama : ");
								String nama = data.nextLine();
								System.out.print("Masukan Alamat : ");
								String alamat = data.nextLine();
								System.out.print("Masukan Semester : ");
								int semester = data.nextInt();
								
								EditMahasiswa(cari,nim,nama,alamat,semester);
								
							}
							else if(pilih2 == 2){
								System.out.println("=================");
								System.out.println("Pilihan Mata Kuliah");
								TampilPilihanMataKuliah();
								System.out.print("Masukan No Mata Kuliah : ");
								int noMK = data.nextInt();
								InputPilihanMataKuliah(noMK,rs.getInt("id"));
							}
							}while(pilih2 != 3);
						}
					}
					//menutup koneksi database
					c.close();
				}catch(SQLException se){
					//membuang error query
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
			}
			else if(pilih == 4){
				TampilMataKuliah();
			}
			else if(pilih == 5){
				System.out.println("=================");
				System.out.print("Masukan kode : ");
				String kode = data.nextLine();
				System.out.print("Masukan Nama : ");
				String namaMK = data.nextLine();
				System.out.print("Masukan Jumlah SKS : ");
				int jumSks = data.nextInt();
				
				java.util.Date utilDate = new Date();
				java.sql.Date date = new java.sql.Date(utilDate.getTime());
				
				TambahMataKuliah(kode,namaMK,jumSks,date);
				System.out.println("=================");
			}
			else if(pilih == 6){
				TampilMataKuliahAmbil();
			}
		}
		System.out.println("Terima Kasih Sudah Menggunakan Program Ini");
    }
}
