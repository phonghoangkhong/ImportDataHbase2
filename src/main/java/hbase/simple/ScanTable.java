package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.IOException;
/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: scantable
 */
public class ScanTable {
    public static void main(String args[]) {
        try {
            ConnectionHbase connectionHbase=new ConnectionHbase();
            Connection connection=connectionHbase.getConnection();
            Table hTable = connection.getTable(TableName.valueOf("default","testhbase3"));

            Scan scan = new Scan();
            scan.setCaching(1000);
            scan.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("level"));


            // Getting the scan result


            ResultScanner scanner = hTable.getScanner(scan);
           long d=System.currentTimeMillis();
            // Reading values from scan result
            for (Result result = scanner.next(); result != null; result = scanner.next())

                System.out.println("Found row : " + result);

            scanner.close();
            connection.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
