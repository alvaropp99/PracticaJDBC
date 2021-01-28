package ejercicio3;

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

public class Consulta {
    public static void main(String[] args) {
        //consultaEscuderia();
        consultaPilotos();
    }
    
    public static void consultaEscuderia(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");

            String sql=String.format("SELECT * FROM escuderia");
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
    
    public static void consultaPilotos(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");

            String sql=String.format("SELECT * FROM pilotos ORDER BY id_escud");
            Statement sentencia=connMysql.createStatement();
            boolean valor=sentencia.execute(sql);
            
            if(valor){
                ResultSet rs=sentencia.getResultSet();
                while(rs.next())
                    System.out.printf("%d,%s,%s,%d %n",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4));
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
