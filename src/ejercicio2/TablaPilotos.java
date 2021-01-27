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

public class TablaPilotos {
    public static void main(String[] args) {
        //insertarPiloto();
        //eliminarPiloto();
        modificarPiloto();
    }
    
    public static void insertarPiloto(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            String id_piloto="13";
            String nombre_piloto="SEBASTIAN";
            String apellido_piloto="VETTEL";
            String id_escud="6";
            
            String sql=String.format("INSERT INTO pilotos VALUES (%s,'%s','%s','%s')"
            ,id_piloto,nombre_piloto,apellido_piloto,id_escud);
            
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
    
    public static void eliminarPiloto(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            String idPiloto="13";
            
            String sql=String.format("DELETE FROM pilotos WHERE id_piloto=%s",idPiloto);
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
    
    public static void modificarPiloto(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            //"UPDATE escuderia SET nombre_escud='TORO ROSSO' WHERE id_escud='6'"

            
            String sql=String.format("UPDATE pilotos SET id_piloto='18',nombre_piloto='LANCE', apellido_piloto='STROLL' WHERE id_piloto='13' ");
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
