package ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class ProcedimientoAlmacenado {
    public static void main(String[] args) {
        ejecutarProcedimiento();
    }
    
    public static void ejecutarProcedimiento(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");

            String d="3";
            String sql="{ ? = call selectEscud (?) }";
            
            CallableStatement llamada=connMysql.prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.VARCHAR);
            llamada.setInt(2, Integer.parseInt(d));
            
            llamada.executeUpdate(sql.toString());
            System.out.println("Id Escuderia: "+llamada.getString(1));
            
            llamada.close();
            connMysql.close();
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
