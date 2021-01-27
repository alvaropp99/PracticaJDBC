/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class CrearTablas {
	public static void main(String[] args) {
		crearTablasMySQL();
	}
	
	public static void crearTablasMySQL() {
		File scriptFile=new File("E:\\2DAM\\Acceso a Datos\\Practica ADT\\CrearTablas.sql");
		System.out.println("--------------------------------------------");
		System.out.println("\n\nFichero de consulta: "+scriptFile.getName());
		System.out.println("Conviertiendo el fichero a cadena...");
		
		BufferedReader entrada=null;
		try {
			entrada=new BufferedReader(new FileReader(scriptFile));
		}catch(FileNotFoundException e) {
			System.out.println("ERROR NO ENCUENTRA el fichero: "+e.getMessage());
			e.printStackTrace();
		}
		
		String linea=null;
		StringBuilder stringBuilder=new StringBuilder();
		String salto= System.getProperty("line.separator");
		try {
			while((linea=entrada.readLine())!=null) {
				stringBuilder.append(linea);
				stringBuilder.append(salto);
			}
		}catch(IOException e) {
			System.out.println("ERROR de E/S, al operar con el fichero: "+e.getMessage());
			e.printStackTrace();
		}
		
		String consulta=stringBuilder.toString();
		
		System.out.println(consulta);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("ERROR en el Driver: "+e.getMessage());
		}
		
		try {
			Connection connMysql=(Connection)DriverManager
					.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
			Statement sents = connMysql.createStatement();
			int res = sents.executeUpdate(consulta);
			System.out.println("Script creado con éxito, res = " + res);
			connMysql.close();
			sents.close();
			
			
		}catch(SQLException e) {
			System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
