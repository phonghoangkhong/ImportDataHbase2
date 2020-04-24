package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;

/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: delete data by rowkey
 */
public class DeleteData {
    public static void main(String args[]){
        try {
            ConnectionHbase connectionHbase=new ConnectionHbase();
            Connection connection=connectionHbase.getConnection();
            Table hTable= connection.getTable(TableName.valueOf("emp"));
            Delete delete=new Delete(Bytes.toBytes("row1"));
            delete.addColumn(Bytes.toBytes("personal data"), Bytes.toBytes("name"));
            delete.addFamily(Bytes.toBytes("professional data"));
            // deleting the data
            hTable.delete(delete);
            // closing the HTable object
           hTable.close();

            System.out.println("data deleted .... ");
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
