//package documaker.hellenic; 

import java.util.Properties;

import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleResultSet;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class queryMissingIC {

  public String dbms;
  public String jarFile;
  public String dbName; 
  public String userName;
  public String password;
  public String urlString;

  private String driver;
  private String serverName;
  private int portNumber;
  private Properties prop;

 public static void main(String[] args) {
 
 Connection conn;
 BufferedWriter writer = null;
 
 
         try {
		       conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@//172.31.34.115:1521/DCMKRPDB.proddbsb.prodvcn.oraclevcn.com", "dmkr_asline", "Asline12" );

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
			
			// let's get one blob

Statement stmt = conn.createStatement();	

BufferedReader reader;

	
//	File logFile = new File(args[1]);
//  writer = new BufferedWriter(new FileWriter(logFile));
    
    reader = new BufferedReader(new FileReader(args[0]));
	String szPolicy = szPolicy = reader.readLine();

			while (szPolicy != null) {
				System.out.println(szPolicy);
				// read next line
				szPolicy = reader.readLine();
				if(szPolicy == null )
				{
				   writer.write ("Completed");
				   continue;
				}

                String cmd = "SELECT trn_ID AS recordCount FROM TRNS T WHERE KEYID = '" + szPolicy + "' and TRNCUSSTR005 = 'HL0009' and CREATETIME >= '" + args[1] + "'";
                System.out.println( cmd );
                ResultSet res = stmt.executeQuery (cmd);
				if(!res.next())
				{
                  System.out.println( szPolicy);
				  res.close();
				}
                else
                  res.close();					
			}

			reader.close();
		    System.out.println("Closing");
            		
           //Close writer
	} catch (IOException e) {
			e.printStackTrace();
		} 
          catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } 		
         catch (Exception e) {
            e.printStackTrace();
        } finally {

        }		
  }
  
}
