package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: put data vào column với rowkey được khởi tạo
 */
public class UpdateData {
    public static void main(String args[]){
        try {
            ConnectionHbase connectionHbase=new ConnectionHbase();
            Connection connection=connectionHbase.getConnection();
            Table hTable= connection.getTable(TableName.valueOf("emp"));
            Put p = new Put(Bytes.toBytes("row1"));

            p.addColumn(Bytes.toBytes("personal data"),Bytes.toBytes("city"),Bytes.toBytes("HaNoi"));

            hTable.put(p);
            System.out.println("data updated");
            hTable.close();
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
