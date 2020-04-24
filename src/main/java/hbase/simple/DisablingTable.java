package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import java.io.IOException;

/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: disable table by admin( nếu chưa disable)
 */
public class DisablingTable {
    public static void main(String args[]){
        try {
            ConnectionHbase con = new ConnectionHbase();
            Connection connection = con.getConnection();
            Admin admin=connection.getAdmin();
            Boolean b=admin.isTableDisabled(TableName.valueOf("emp"));
             if(!b){
                 admin.disableTable(TableName.valueOf("emp"));
                 System.out.println("Table disabled");
             }
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
