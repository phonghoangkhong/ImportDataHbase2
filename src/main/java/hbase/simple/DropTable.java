package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import java.io.IOException;
/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: disable table--> delete table by admin
 */
public class DropTable {
    public static void main(String args[]){
        try {
            ConnectionHbase con = new ConnectionHbase();
            Connection connection = con.getConnection();
            Admin admin=connection.getAdmin();
            admin.disableTable(TableName.valueOf("testhbase3"));
            admin.deleteTable(TableName.valueOf("testhbase3"));
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
