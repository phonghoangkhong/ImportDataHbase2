package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import java.io.IOException;
/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: check table đã được enable chưa nếu chưa enable by admin
 */
public class EnablingTable {
    public static void main(String args[]){
        try {
            ConnectionHbase con = new ConnectionHbase();
            Connection connection = con.getConnection();
            Admin admin=connection.getAdmin();
            Boolean b=admin.isTableEnabled(TableName.valueOf("testhbase3"));
            if(!b){
                admin.enableTable(TableName.valueOf("testhbase3"));
                System.out.println("Table enabled");
            }
            connection.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
