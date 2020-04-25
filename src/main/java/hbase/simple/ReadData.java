package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
import java.util.Random;

/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: get data by rowkey
 */
public class ReadData {
    public static void main(String args[]){
        try {
             Random random=new Random();
            while (true) {
                ConnectionHbase connectionHbase = new ConnectionHbase();
                Connection connection = connectionHbase.getConnection();
                Table hTable = connection.getTable(TableName.valueOf("testhbase2"));
                long d=System.currentTimeMillis();
                for (int i = 0; i < 1000000; i++) {
                    int rowkey=random.nextInt(100000000);
                    Get g = new Get(Bytes.toBytes(rowkey));
                    //read data
                    Result result = hTable.get(g);
                    // Reading values from Result class object
                    byte[] value = result.getValue(Bytes.toBytes("cf1"), Bytes.toBytes("level"));

                    byte[] value1 = result.getValue(Bytes.toBytes("cf2"), Bytes.toBytes("from_date"));

                    // Printing the values
                    String level = Bytes.toString(value);
                    String from_date= Bytes.toString(value1);


                }
                hTable.close();
                connection.close();
                System.out.println(System.currentTimeMillis()-d);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
