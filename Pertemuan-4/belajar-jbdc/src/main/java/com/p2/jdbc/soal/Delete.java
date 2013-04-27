package com.p2.jdbc.soal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;

public class Delete 
{
    public static void main( String[] args )
    {
		try{
			//paramenter yang dikirim adalah host,port , nama database ,user,password
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
			String query = "DELETE from pembelian where id_brg = 1";
			PreparedStatement ps = conn.prepareStatement(query);
			int hasil = ps.executeUpdate();
			
			if(hasil == 1){
				System.out.println("Berhasil Didelete");
			}
			else{
				System.out.println("Gagal Didelete");
			}
			
		}catch(SQLException se){
			Logger.getLogger(Delete.class.getName()).log(Level.SEVERE,null,se);
		}
    }
}
