package com.p2.jdbc.soal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;

public class Update 
{
    public static void main( String[] args )
    {
		try{
			//paramenter yang dikirim adalah host,port , nama database ,user,password
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
			String query = "UPDATE pembelian set id_brg=1,nama_brg='Apel',jumlah_brg="+new Integer(3)+",tgl_beli='2012-04-05',ket='bekas',berat_brg=5 where id_brg = 1";
			PreparedStatement ps = conn.prepareStatement(query);
			int hasil = ps.executeUpdate();
			
			if(hasil == 1){
				System.out.println("Berhasil Diupdate");
			}
			else{
				System.out.println("Gagal Diupdate");
			}
			
		}catch(SQLException se){
			Logger.getLogger(Update.class.getName()).log(Level.SEVERE,null,se);
		}
    }
}
