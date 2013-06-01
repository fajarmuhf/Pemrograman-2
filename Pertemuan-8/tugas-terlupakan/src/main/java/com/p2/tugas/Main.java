package com.p2.tugas;

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
		Scanner data = new Scanner(System.in);
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
			pilih = data.nextInt();
			data.nextLine();
			if(pilih == 1){
				System.out.println("=================");
				try{
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
					String sqlTampil = "SELECT * FROM film ";
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					ResultSet rs = psTampil.executeQuery();
					System.out.println("|\tid\t|\tjudul\t|\ttglRelease\t|\trating\t|\thargaFilm\t|");
					while(rs.next()){
						System.out.print("|\t"+rs.getInt("id")+"\t");
						System.out.print("|\t"+rs.getString("judul")+"\t");
						System.out.print("|\t"+rs.getDate("tglRelease")+"\t");
						System.out.print("|\t"+rs.getInt("rating")+"\t");
						System.out.print("|\t"+rs.getDouble("hargaFilm")+"\t|\n");
					}
					c.close();
				}catch(SQLException se){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
				System.out.println("=================");				
			}
			else if(pilih == 2){
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
					final String OLD_FORMAT = "dd-MM-yyyy";
					final String NEW_FORMAT = "yyyy-MM-dd";

					String oldDateString = tanggal;
					String newDateString;

					SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
					Date d = sdf.parse(oldDateString);
					sdf.applyPattern(NEW_FORMAT);
					newDateString = sdf.format(d);
					
					try{
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
						String query = "INSERT INTO film(judul,tglRelease,rating,hargaFilm) values (?,?,"+rating+","+harga+")";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1,judul);
						ps.setString(2,newDateString);
						ps.executeUpdate();
					}catch(SQLException se){
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
					}
				}catch(ParseException pe){
					
				}
				System.out.println("=================");
			}
			else if(pilih == 3){
				System.out.println("=================");
				System.out.print("Masukan Judul : ");
				String cari = data.nextLine();
				try{
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
					String sqlTampil = "SELECT * FROM film ";
					PreparedStatement psTampil = c.prepareStatement(sqlTampil);
					ResultSet rs = psTampil.executeQuery();
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
								pilih2 = data.nextInt();
								data.nextLine();
								if(pilih2 == 1){
									System.out.print("Masukan Judul : ");
									String judul = data.nextLine();
									System.out.print("Masukan Tanggal Release (DD-MM-YYYY): ");
									String tanggal = data.nextLine();
									System.out.print("Masukan Rating : ");
									int rating = data.nextInt();
									System.out.print("Masukan Harga Film : ");
									BigDecimal harga = data.nextBigDecimal();
									
									try{					
										final String OLD_FORMAT = "dd-MM-yyyy";
										final String NEW_FORMAT = "yyyy-MM-dd";

										String oldDateString = tanggal;
										String newDateString;

										SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
										Date d = sdf.parse(oldDateString);
										sdf.applyPattern(NEW_FORMAT);
										newDateString = sdf.format(d);
										
										try{
											Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
											String query = "UPDATE film SET judul='"+judul+"',tglRelease='"+newDateString+"',rating="+rating+",hargaFilm="+harga+" WHERE judul = '"+cari+"'";
											PreparedStatement ps = conn.prepareStatement(query);
											ps.executeUpdate();
										}catch(SQLException se){
											Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
										}
									}catch(ParseException pe){
										
									}
									System.out.println("=================");
								}
								else if(pilih2 == 2){
									try{
										Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
										String query = "DELETE FROM film WHERE judul = '"+cari+"'";
										PreparedStatement ps = conn.prepareStatement(query);
										System.out.println("Data "+cari+" dalam tabel film berhasil dihapus ");
										ps.executeUpdate();
									}catch(SQLException se){
										Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
									}
									System.out.println("=================");
								}
							}while(pilih2 != 3);
						}
					}
					c.close();
				}catch(SQLException se){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
			}
		}
		System.out.println("Terima Kasih Sudah Menggunakan Program Ini");
    }
}
