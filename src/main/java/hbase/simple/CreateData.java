package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
/**
 * Author:PHONGKH
 * B1: Connect Hbase
 * B2: Kết nối tới table
 * B3: put data vào column với rowkey được khởi tạo
 */
import java.io.IOException;

public class CreateData {
    public static void main(String args[]){
        try {
            ConnectionHbase connectionHbase=new ConnectionHbase();
            Connection connection=connectionHbase.getConnection();
            Admin admin=connection.getAdmin();
            Table hTable= connection.getTable(TableName.valueOf("emp"));
            Put p = new Put(Bytes.toBytes("row1"));
            // put data (column family,column,value)
            p.addColumn(Bytes.toBytes("personal data"),
                    Bytes.toBytes("name"),Bytes.toBytes("raju2"));

            p.addColumn(Bytes.toBytes("personal data"),
                    Bytes.toBytes("city"),Bytes.toBytes("hyderabad2"));

            p.addColumn(Bytes.toBytes("professional data"),Bytes.toBytes("designation"),
                    Bytes.toBytes("manager2"));

            p.addColumn(Bytes.toBytes("professional data"),Bytes.toBytes("salary"),
                    Bytes.toBytes("500001"));

            hTable.put(p);
            hTable.close();
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
