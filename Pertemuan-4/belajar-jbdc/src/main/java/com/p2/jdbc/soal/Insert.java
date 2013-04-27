package com.p2.jdbc.soal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.math.BigDecimal;

public class Insert 
{
    public static void main( String[] args )
    {
		try{
			//paramenter yang dikirim adalah host,port , nama database ,user,password
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/p2","root","");
			String query = "INSERT INTO pembelian(id_brg,nama_brg,jumlah_brg,tgl_beli,ket,berat_brg) values ( 1 ,'Java',"+new Integer(5)+",'2012-04-04','baru', 3 )";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
		}catch(SQLException se){
			Logger.getLogger(Insert.class.getName()).log(Level.SEVERE,null,se);
		}
    }
}
