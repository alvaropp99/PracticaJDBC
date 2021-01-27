package ejercicio2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;




public class TablaEscuderia {
    
    
    public static void main(String[] args) {
        //insertarEscuderia();
        //eliminarEscuderia();
        modificarEscuderia();
    }
    
    public static void insertarEscuderia(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            String idEscuderia="6";
            String nombreEscuderia="ASTON MARTIN";
            
            String sql=String.format("INSERT INTO escuderia VALUES (%s,'%s')"
            ,idEscuderia,nombreEscuderia);
            
            System.out.println(sql);
            
            Statement sentencia=connMysql.createStatement();
            int filas=0;
            
            try{
                filas=sentencia.executeUpdate(sql.toString());
                System.out.println("Filas afectadas: "+filas);
            }catch(SQLException e){
                System.out.printf("HA OCURRIDO UNA EXCEPCIÓN:%n"); 
		System.out.printf("Mensaje   : %s %n", e.getMessage()); 
		System.out.printf("SQL estado: %s %n", e.getSQLState()); 
		System.out.printf("Cód error : %s %n", e.getErrorCode());
            }
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void eliminarEscuderia(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            String idEscud="6";
            
            String sql=String.format("DELETE FROM escuderia WHERE id_escud=%s",idEscud);
            Statement sentencia=connMysql.createStatement();
            boolean valor=sentencia.execute(sql);
            
            if(valor){
                ResultSet rs=sentencia.getResultSet();
                while(rs.next())
                    System.out.printf("%d,%s %n",rs.getInt(1),rs.getString(2));
                rs.close();
            }else{
                int f=sentencia.getUpdateCount();
                System.out.printf("Filas afectadas:%d %n",f);
            }
            sentencia.close();
            connMysql.close();
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void modificarEscuderia(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            //"UPDATE escuderia SET nombre_escud='TORO ROSSO' WHERE id_escud='6'"

            
            String sql=String.format("UPDATE escuderia SET nombre_escud='TORO ROSSO' WHERE id_escud='6' ");
            Statement sentencia=connMysql.createStatement();
            boolean valor=sentencia.execute(sql);
            
            if(valor){
                ResultSet rs=sentencia.getResultSet();
                while(rs.next())
                    System.out.printf("%d,%s %n",rs.getInt(1),rs.getString(2));
                rs.close();
            }else{
                int f=sentencia.getUpdateCount();
                System.out.printf("Filas afectadas:%d %n",f);
            }
            sentencia.close();
            connMysql.close();
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
}
