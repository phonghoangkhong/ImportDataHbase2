package hbase.simple;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class CreateTable2 {
    public static void main(String args[])  {
try {
    ConnectionHbase con = new ConnectionHbase();
    Connection connection = con.getConnection();
    Admin admin = connection.getAdmin();
    System.out.println("ok");
    TableName tableName = TableName.valueOf("test");
    TableDescriptor tableDescriptor = TableDescriptorBuilder
            .newBuilder(tableName)
            .setValue("SPLITALGO", "DecimalStringSplit")
            .setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("f1")).setCompressionType(Compression.Algorithm.SNAPPY).build())
            .build();
    admin.createTable(tableDescriptor);
}catch (Exception e){
    e.printStackTrace();
}
    }
}
