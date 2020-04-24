package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: get data by rowkey
 */
public class ReadData {
    public static void main(String args[]){
        try {

            ConnectionHbase connectionHbase=new ConnectionHbase();
            Connection connection=connectionHbase.getConnection();
            System.out.println("OK");
            Table hTable= connection.getTable(TableName.valueOf("emp"));
            Get g=new Get(Bytes.toBytes("row1"));
            //read data
            Result result=hTable.get(g);
            // Reading values from Result class object
            byte [] value = result.getValue(Bytes.toBytes("personal data"),Bytes.toBytes("name"));

            byte [] value1 = result.getValue(Bytes.toBytes("personal data"),Bytes.toBytes("city"));

            // Printing the values
            String name = Bytes.toString(value);
            String city = Bytes.toString(value1);

            System.out.println("name: " + name + " city: " + city);
             hTable.close();
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
