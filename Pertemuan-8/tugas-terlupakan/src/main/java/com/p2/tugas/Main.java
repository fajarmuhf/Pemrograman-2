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

public class Main 
{
    public static void main( String[] args )
    {
		Scanner data = new Scanner(System.in);
		int pilih = 0;
		while(pilih != 3){
			System.out.println("=================");
			System.out.println("MENU PILIHAN");
			System.out.println("1. Tampil");
			System.out.println("2. Input");
			System.out.println("3. Keluar");
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
				System.out.print("Masukan Tanggal Release (YYYY-MM-DD): ");
				String tanggal = data.nextLine();
				System.out.print("Masukan Rating : ");
				int rating = data.nextInt();
				System.out.print("Masukan Harga Film : ");
				BigDecimal harga = data.nextBigDecimal();
				try{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tugas_terlupakan","root","");
					String query = "INSERT INTO film(judul,tglRelease,rating,hargaFilm) values (?,?,"+rating+","+harga+")";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1,judul);
					ps.setString(2,tanggal);
					ps.executeUpdate();
				}catch(SQLException se){
					Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,se);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Terima Kasih Sudah Menggunakan Program Ini");
    }
}
