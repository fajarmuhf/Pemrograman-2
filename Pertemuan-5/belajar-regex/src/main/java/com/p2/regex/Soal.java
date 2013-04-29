package com.p2.regex;

import java.util.regex.*;
import java.lang.String;
import java.util.Scanner;

public class Soal 
{
    public static void main( String[] args )
    {
		Scanner data = new Scanner(System.in);
		System.out.println("Masukan Nama Anda");
		String nama = data.nextLine();
		String regexnama = "[A-Za-z]{1}[a-z]{2,}\\s?[A-Za-z]{0,1}[a-z]{0,}\\s?[A-Za-z]{0,1}[a-z]{0,}";
		if(nama.matches(regexnama)){
			System.out.println("Nama Anda Tidak Alay");
		}
		else{
			System.out.println("Nama Anda Alay");	
		}
		
		System.out.println("Masukan Nama Email");
		String email = data.nextLine();
		String regexemail1 = "[A-Za-z0-9]{1,}\\@?[A-Za-z]{1,}.[A-Za-z]{1,}.[A-Za-z]{1,}";
		String regexemail2 = "[A-Za-z0-9]{1,}\\@?[A-Za-z]{1,}.[A-Za-z]{1,}";
		if(email.matches(regexemail1) || email.matches(regexemail2)){
			System.out.println("Email Anda Valid");
		}
		else{
			System.out.println("Email Anda Tidak Valid");	
		}
		
		System.out.println("Masukan Nama Tanggal Lahir");
		String lahir = data.nextLine();
		String regexlahir = "[0-9]{1,2}-[0-9]{1,2}-[0-9]{4,}";
		if(lahir.matches(regexlahir)){
			System.out.println("Tanggal Lahir Valid");
		}
		else{
			System.out.println("Tanggal Lahir Anda Tidak Valid");	
		}
		
		System.out.println("Masukan Nama Situs");
		String Situs = data.nextLine();
		String regexsitus = "[A-Za-z0-9]{1,}.[A-Za-z0-9]{1,}.?[A-Za-z0-9]{0,}";
		if(Situs.matches(regexsitus)){
			System.out.println("Situs Valid");
		}
		else{
			System.out.println("Situs Tidak Valid");	
		}
		
	}
}
