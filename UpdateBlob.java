import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleDriver;
import oracle.jdbc.OracleResultSet;
import oracle.sql.*;

public class UpdateBlob {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        ResultSet rs = null;
        try {

            DriverManager.registerDriver(new OracleDriver());
            conn = DriverManager.getConnection( "jdbc:oracle:thin:@//172.31.34.115:1521/DCMKRPDB.proddbsb.prodvcn.oraclevcn.com", "dmkr_asline",  "Asline12" );
            
            if ( conn == null)
              return;

            System.out.println( "Thanks" );
            PreparedStatement pstmt =  conn.prepareStatement("update PUBS set PUBOUTBLOB = ?, PUBOUTPUTSIZE = ? where PUB_id = " + args[1] );
            File blob = new File(args[0]);
            FileInputStream in = new FileInputStream(blob);

            // the cast to int is necessary because with JDBC 4 there is 
            // also a version of this method with a (int, long) 
            // but that is not implemented by Oracle
            pstmt.setBinaryStream(1, in, (int)blob.length()); 
			pstmt.setInt( 2, (int) in.getChannel().size() );

            pstmt.executeUpdate();
            // conn.commit();
            pstmt.close();
            conn.close();
        }
        catch( SQLException ex )
        {
           ex.printStackTrace();
        }
   }

}