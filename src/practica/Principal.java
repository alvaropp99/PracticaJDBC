/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Principal {
    
    static Scanner sc=new Scanner(System.in);
    
    public static void main(String[] args) {
       int o;
       do{ 
            
            System.out.println("Pulsa uno de los siguientes números para realizar la opción deseada.\n");
            System.out.println("1. Crear Tablas/Insertar datos/Borrar Base de datos.\n"
                    + "2. Modificar Tabla Escudería/Pilotos.\n"
                    + "3. Consulta Tablas.\n"
                    + "4. Procedimiento Almacenado.\n"
                    + "5. Crear XML Escuderia/Pilotos\n"
                    + "6. Leer e insertar XML tabla Escudería\n"
                    + "7. Salir");

            o=sc.nextInt();

            switch(o){
                case 1:
                    int p;
                    do{
                        System.out.println("1. Crear Tablas \n"
                            + "2. Insertar datos en las tablas\n"
                            + "3. Borrar Base de datos\n"
                            + "4. Atrás");
                        p=sc.nextInt();
                        
                        switch(p){
                            case 1:
                                crearTablasMySQL();
                                break;
                                
                            case 2:
                                aniadirDatosMYsql();
                                break;
                                
                            case 3:
                                borrarDatabase();
                                break;
                                
                            case 4: //Volver al menu principal mediante un proceso haciendo la función de volver atrás
                                
                                try{
                                    Process pro=Runtime.getRuntime().exec("java PracticaADT/practica/Principal");
                                    break;
                                }catch(IOException e){
                                    System.out.println(e.getMessage());
                                }
                                
                                break;
                                
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    }while(p!=4);
                    
                    break;
                    
                case 2:
                    int e;
                    do{
                        System.out.println("1. Insertar Escudería\n"
                                + "2. Eliminar Escudería\n"
                                + "3. Modificar Escudería\n"
                                + "4. Insertar Piloto\n"
                                + "5. Eliminar Piloto\n"
                                + "6. Modificar Piloto\n"
                                + "7. Atras");
                        
                        e=sc.nextInt();
                        switch(e){
                            case 1:
                                insertarEscuderia();
                                break;
                                
                            case 2:
                                eliminarEscuderia();
                                break;
                                
                            case 3:
                                modificarEscuderia();
                                break;
                                
                            case 4:
                                insertarPiloto();
                                break;
                                
                            case 5:
                                eliminarPiloto();
                                break;
                                
                            case 6:
                                modificarPiloto();
                                break;
                                
                            case 7:
                                try{
                                    Process pro=Runtime.getRuntime().exec("java PracticaADT/practica/Principal");
                                    break;
                                }catch(IOException er){
                                    System.out.println(er.getMessage());
                                }
                                break;
                                
                            default:
                                System.out.println("Opción no válida");
                                break;
                                
                        }
                    }while(e!=7);
                    break;
                    
                case 3:
                    int c;
                    do{
                        System.out.println("\n1. Consulta Escuderías\n"
                                + "2. Consulta Pilotos\n"
                                + "3. Atrás");
                        c=sc.nextInt();
                        
                        switch(c){
                            case 1:
                                consultaEscuderia();
                                break;
                            
                            case 2:
                                consultaPilotos();
                                break;
                                
                            case 3:
                                try{
                                    Process pro=Runtime.getRuntime().exec("java PracticaADT/practica/Principal");
                                }catch(IOException er){
                                    System.out.println(er.getMessage());
                                }
                                break;
                                
                            default:
                                System.out.println("Opción no válida");
                                break;
                                
                        }
                    }while(c!=3);
                    break;
                    
                case 4:
                    ejecutarProcedimiento();
                    break;
                    
                case 5:
                    int x;
                    do{
                        System.out.println("\n1. Crear XML Escudería"
                                + "\n2. Crear XML Pilotos"
                                + "\n3. Atrás");
                        
                        x=sc.nextInt();
                        
                        switch(x){
                            case 1:
                                crearXmlEscuderia();
                                break;
                            
                            case 2:
                                crearXmlPilotos();
                                break;
                                
                            case 3:
                                try{
                                    Process pro=Runtime.getRuntime().exec("java PracticaADT/practica/Principal");
                                }catch(IOException er){
                                    System.out.println(er.getMessage());
                                }
                                break;
                                
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                    }while(x!=3);
                    break;
                    
                case 6:
                    insertarXML();
                    break;
                    
                case 7:
                    System.out.println("Fin del Programa");
                    break;
                    
                default:
                    
                    System.out.println("Opción no válida");
                    break;
            }
       }while(o!=7);
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
                        System.out.println("\nTablas creadas con éxito\n");
			
			
		}catch(SQLException e) {
			System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
			e.printStackTrace();
		}
	}
    
    public static void aniadirDatosMYsql() {
		File scriptFile=new File("E:\\2DAM\\Acceso a Datos\\Practica ADT\\InsertarDatos.sql");
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
                        System.out.println("\nDatos introducidos correctamente\n");
			connMysql.close();
			sents.close();
			
			
		}catch(SQLException e) {
			System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
			e.printStackTrace();
		}
	}
    
    public static void borrarDatabase() {
		File scriptFile=new File("E:\\2DAM\\Acceso a Datos\\Practica ADT\\BorrarDatabase.sql");
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
                        System.out.println("\nBase de datos borrada\n");
			
			
		}catch(SQLException e) {
			System.out.println("ERROR AL EJECUTAR EL SCRIPT: " + e.getMessage());
			e.printStackTrace();
		}
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
                System.out.println("Escudería Insertada\n");
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
                System.out.println("Escudería Eliminada");
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
                System.out.println("Escudería Modificada");
            }
            sentencia.close();
            connMysql.close();
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
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
            String id_escud="5";
            
            String sql=String.format("INSERT INTO pilotos VALUES (%s,'%s','%s','%s')"
            ,id_piloto,nombre_piloto,apellido_piloto,id_escud);
            
            System.out.println(sql);
            
            System.out.println("Piloto Insertado\n");
            
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
                System.out.println("Piloto Eliminado\n");
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
                System.out.println("Piloto Modificado");
            }
            sentencia.close();
            connMysql.close();
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
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
    
    public static void ejecutarProcedimiento(){
        
               
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql=(Connection)DriverManager
			.getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true"
							,"root","root");
            
            String dep="3";
            
            String sql="{ ? = call nombre_escuderia(?) }"; 
            
            CallableStatement llamada=connMysql.prepareCall(sql);
            
            llamada.registerOutParameter(1, Types.VARCHAR);
            llamada.setInt(2, Integer.parseInt(dep));
            
            llamada.executeUpdate();
            System.out.println("NOMBRE ESCUDERIA: "+llamada.getString(1));
            llamada.close();
            connMysql.close();
            
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void crearXmlEscuderia() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true",
                             "root", "root");

            com.mysql.jdbc.Statement sentencia = (com.mysql.jdbc.Statement) connMysql.createStatement();

            String consulta = "SELECT * FROM escuderia";

            Statement statement=connMysql.createStatement();
            ResultSet rs=statement.executeQuery(consulta);
            
            Document xmlDoc=buildEscuderiaXML(rs);
            
            File salida=new File("Escuderias.xml");
            printDom(xmlDoc, salida);
            

        } catch(Exception e){
            e.printStackTrace();
        }

    }
    
    public static Document buildEscuderiaXML(ResultSet rs)throws Exception{
        Document xmlDoc=new DocumentImpl();
        
        Element rootElem=xmlDoc.createElement("tabla_escuderias");
        xmlDoc.appendChild(rootElem);
        
        while(rs.next()){
            Element escud=xmlDoc.createElement("Escudería");
            
            Element escudId=xmlDoc.createElement("id_escud");
            Element escudNombre=xmlDoc.createElement("nombre_escud");
            
            escudId.appendChild(xmlDoc.createTextNode(rs.getString("id_escud")));
            escudNombre.appendChild(xmlDoc.createTextNode(rs.getString("nombre_escud")));
            
            escud.appendChild(escudId);
            escud.appendChild(escudNombre);
            
            rootElem.appendChild(escud);
        }
        return xmlDoc;
    }
    
    public static void printDom(Document xmlDoc, File salida)throws Exception{
        OutputFormat outputFormat=new OutputFormat("XML","UTF-8",true);
        FileWriter fileWriter=new FileWriter(salida);
        
        XMLSerializer serializer=new XMLSerializer(fileWriter,outputFormat);
        
        serializer.asDOMSerializer();
        
        serializer.serialize(xmlDoc.getDocumentElement());
    }
    
    public static void crearXmlPilotos() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true",
                             "root", "root");

            com.mysql.jdbc.Statement sentencia = (com.mysql.jdbc.Statement) connMysql.createStatement();

            String consulta = "SELECT * FROM pilotos";

            Statement statement=connMysql.createStatement();
            ResultSet rs=statement.executeQuery(consulta);
            
            Document xmlDoc=buildPilotosXML(rs);
            
            File salida=new File("Pilotos.xml");
            printDom(xmlDoc, salida);
            

        } catch(Exception e){
            e.printStackTrace();
        }

    }
    
    
    public static Document buildPilotosXML(ResultSet rs)throws Exception{
        Document xmlDoc=new DocumentImpl();
        
        Element rootElem=xmlDoc.createElement("tabla_pilotos");
        xmlDoc.appendChild(rootElem);
        
        while(rs.next()){
            Element pilot=xmlDoc.createElement("Piloto");
            
            Element pilotoId=xmlDoc.createElement("id_piloto");
            Element pilotoNombre=xmlDoc.createElement("nombre_piloto");
            Element pilotoApellido=xmlDoc.createElement("apellido_piloto");
            Element escudId=xmlDoc.createElement("id_escud");
            
            pilotoId.appendChild(xmlDoc.createTextNode(rs.getString("id_piloto")));
            pilotoNombre.appendChild(xmlDoc.createTextNode(rs.getString("nombre_piloto")));
            pilotoApellido.appendChild(xmlDoc.createTextNode(rs.getString("apellido_piloto")));
            escudId.appendChild(xmlDoc.createTextNode(rs.getString("id_escud")));
            
            pilot.appendChild(pilotoId);
            pilot.appendChild(pilotoNombre);
            pilot.appendChild(pilotoApellido);
            pilot.appendChild(escudId);
            
            
            rootElem.appendChild(pilot);
        }
        return xmlDoc;
    }
    
    public static void insertarXML() {
        try {
            String escuderia = "Escuderias.xml";

            Class.forName("com.mysql.jdbc.Driver");
            Connection connMysql = (Connection) DriverManager
                    .getConnection("jdbc:mysql://localhost/practica?allowMultiQueries=true",
                             "root", "root");

            com.mysql.jdbc.Statement sentencia = (com.mysql.jdbc.Statement) connMysql.createStatement();

            String consulta = "INSERT INTO escuderia (id_escud,nombre_escud) VALUES(";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File(escuderia));
            document.getDocumentElement().normalize();

            System.out.printf("Elemento raíz: %s %n", document.getDocumentElement().getNodeName());
            NodeList escuderias = document.getElementsByTagName("Escudería");
            System.out.printf("Total Escuderías: %d %n", escuderias.getLength());

            for(int i=0;i<escuderias.getLength();i++){
                Node escud=escuderias.item(i);
                
                if(escud.getNodeType()==Node.ELEMENT_NODE){
                    Element element=(Element) escud;
                    
                    consulta=consulta.concat(element.getElementsByTagName("id_escud").item(0).getTextContent()+", ");
                    System.out.println(element.getElementsByTagName("id_escud").item(0).getTextContent());
                    
                    consulta=consulta.concat("'"+element.getElementsByTagName("nombre_escud").item(0).getTextContent()+"'); ");
                    System.out.println(element.getElementsByTagName("nombre_escud").item(0).getTextContent());
                    
                    System.out.println(consulta);
                    sentencia.executeUpdate(consulta);
                    
                    consulta = "INSERT INTO escuderia (id_escud,nombre_escud) VALUES(";
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}