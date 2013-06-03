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
    public static void main( String[] args )
    {
		//inisialisasi tipe data Scanner untuk menginput data ke variabel data
		Scanner data = new Scanner(System.in);
		//variabel untuk pemilihan
		int pilih = 0;
		int pilih2 = 0;
		while(pilih != 4){
			System.out.println("=================");
			System.out.println("MENU PILIHAN");
			System.out.println("1. Tampil");
			System.out.println("2. Input");
			System.out.println("3. Pencarian");
			System.out.println("4. Keluar");
			System.out.print("Masukan Pilihan Anda : ");
			//menginput data ke variabel pilih
			pilih = data.nextInt();
			//untuk inputan digeser ke baris baru agar dapat menginput string dengan spasi
			data.nextLine();
			if(pilih == 1){
				System.out.println("=================");
				try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
					//string untuk query
					String sqlTampil = "SELECT * FROM film ";
					//menyiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//mengeksekusi query
					ResultSet rs = psTampil.executeQuery();
					//menampilkan tabel setelah query dieksekusi
					System.out.println("|\tid\t|\tjudul\t|\ttglRelease\t|\trating\t|\thargaFilm\t|");
					//perulangan untuk menampilkan isi dari tabel yang telah dieksekusi
					while(rs.next()){
						System.out.print("|\t"+rs.getInt("id")+"\t");
						System.out.print("|\t"+rs.getString("judul")+"\t");
						System.out.print("|\t"+rs.getDate("tglRelease")+"\t");
						System.out.print("|\t"+rs.getInt("rating")+"\t");
						System.out.print("|\t"+rs.getDouble("hargaFilm")+"\t|\n");
					}
					//menutup koneksi
					c.close();
				}catch(SQLException se){
					//membuang error sql 
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
				System.out.println("=================");				
			}
			else if(pilih == 2){
				//menginput judul,tanggal,rating,harga
				System.out.println("=================");
				System.out.print("Masukan Judul : ");
				String judul = data.nextLine();
				System.out.print("Masukan Tanggal Release (DD-MM-YYYY): ");
				String tanggal = data.nextLine();
				System.out.print("Masukan Rating : ");
				int rating = data.nextInt();
				System.out.print("Masukan Harga Film : ");
				BigDecimal harga = data.nextBigDecimal();
				
				try{
					//pendeklarasian format date dalam string					
					final String OLD_FORMAT = "dd-MM-yyyy";
					final String NEW_FORMAT = "yyyy-MM-dd";

					//memasukan variabel tanggal ke oldDateString
					String oldDateString = tanggal;
					String newDateString;

					//mengkonversi format dari format lama ke format baru
					SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
					Date d = sdf.parse(oldDateString);
					sdf.applyPattern(NEW_FORMAT);
					newDateString = sdf.format(d);
					
					try{
						//membuat koneksi ke database
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
						//menyiapkan query ke dalam string
						String query = "INSERT INTO film(judul,tglRelease,rating,hargaFilm) values (?,?,"+rating+","+harga+")";
						//menyiapkan query statement untuk dieksekusi
						PreparedStatement ps = conn.prepareStatement(query);
						//penginputan query pada setiap ?  
						ps.setString(1,judul);
						ps.setString(2,newDateString);
						//mengeksekusi statement query
						ps.executeUpdate();
					}catch(SQLException se){
						//menghandle error sql
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
					}
				}catch(ParseException pe){
					//menghandel error tanggal
				}
				System.out.println("=================");
			}
			else if(pilih == 3){
				System.out.println("=================");
				System.out.print("Masukan Judul : ");
				//menginput data yang ingin dicari
				String cari = data.nextLine();
				try{
					//membuat koneksi ke database
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
					//query dalam bentuk string
					String sqlTampil = "SELECT * FROM film ";
					//mempersiapkan query untuk dieksekusi
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					//Hasil eksekusi query dimasukan kedalam variabel rs 
					ResultSet rs = psTampil.executeQuery();
					//menampilkan hasil query
					while(rs.next()){
						if(cari.equals(rs.getString("judul"))){
							System.out.println("=================");
							System.out.println("id : "+rs.getInt("id"));
							System.out.println("judul : "+rs.getString("judul"));
							System.out.println("tanggal release : "+rs.getDate("tglRelease"));
							System.out.println("rating : "+rs.getInt("rating"));
							System.out.println("harga : "+rs.getDouble("hargaFilm"));
							System.out.println("=================");
							do{
								System.out.println("MENU");
								System.out.println("=================");
								System.out.println("1.edit");
								System.out.println("2.hapus");
								System.out.println("3.keluar");
								System.out.println("=================");
								System.out.print("Pilih : ");
								//menginput untuk pilihan ke 2 
								pilih2 = data.nextInt();
								//NextLine agar dapat memasukan String dengan spasi pada inputan berikutnya
								data.nextLine();
								if(pilih2 == 1){
									//memasukan judul,tangal,rating,dan harga yang akan diubah menjadi yg diinput
									System.out.print("Masukan Judul : ");
									String judul = data.nextLine();
									System.out.print("Masukan Tanggal Release (DD-MM-YYYY): ");
									String tanggal = data.nextLine();
									System.out.print("Masukan Rating : ");
									int rating = data.nextInt();
									System.out.print("Masukan Harga Film : ");
									BigDecimal harga = data.nextBigDecimal();
									
									try{					
										
										//pendeklarasian format date dalam string					
										final String OLD_FORMAT = "dd-MM-yyyy";
										final String NEW_FORMAT = "yyyy-MM-dd";

										//memasukan variabel tanggal ke oldDateString
										String oldDateString = tanggal;
										String newDateString;

										//mengkonversi format dari format lama ke format baru
										SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
										Date d = sdf.parse(oldDateString);
										sdf.applyPattern(NEW_FORMAT);
										newDateString = sdf.format(d);
										
										try{
											//membuat koneksi ke database
											Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
											//query dalam string
											String query = "UPDATE film SET judul=?,tglRelease=?,rating=?,hargaFilm=? WHERE id = ?";
											//mempersiapkan statement untuk dieksekusi
											PreparedStatement ps = conn.prepareStatement(query);
											//penginputan query pada setiap ?
											ps.setString(1,judul);
											ps.setDate(2,new java.sql.Date(d.getTime()) );
											ps.setInt(3,rating);
											ps.setBigDecimal(4,harga);
											ps.setInt(5,rs.getInt("id"));
											//mengeksekusi query
											ps.executeUpdate();
										}catch(SQLException se){
											//membuang error sql
											Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
										}
									}catch(ParseException pe){
										//membuang error format date
									}
									System.out.println("=================");
								}
								else if(pilih2 == 2){
									try{
										//membuat koneksi ke database
										Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
										//query dalam string
										String query = "DELETE FROM film WHERE judul = '"+cari+"'";
										//membuat statement untuk dieksekusi
										PreparedStatement ps = conn.prepareStatement(query);
										//menampilkan keterangan data yang dicari sudah dihapus
										System.out.println("Data "+cari+" dalam tabel film berhasil dihapus ");
										//mengeksekusi query
										ps.executeUpdate();
									}catch(SQLException se){
										//membuang error sql
										Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
									}
									System.out.println("=================");
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
		}
		System.out.println("Terima Kasih Sudah Menggunakan Program Ini");
    }
}
