package hbase.simple;


import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:PHONGKH
 * B1: Connect hbase
 * B2: Tạo listcolumnfamily --> add vào table
 * B3: tạo table với admin
 */
public class CreateTable {
    public static void main(String args[]){
        try {
            ConnectionHbase con = new ConnectionHbase();
            Connection connection = con.getConnection();

            TableDescriptorBuilder table = TableDescriptorBuilder.newBuilder(TableName.valueOf("testhbase3"));
            //list column family

            ColumnFamilyDescriptor col1 = ColumnFamilyDescriptorBuilder.of("cf1");
            ColumnFamilyDescriptor col2 = ColumnFamilyDescriptorBuilder.of("cf2");
            List<ColumnFamilyDescriptor> listColumnFamily = new ArrayList<>();
            listColumnFamily.add(col1);
            listColumnFamily.add(col2);
            // set table by list column family
            table.setColumnFamilies(listColumnFamily);
            Admin admin = connection.getAdmin();
            // create table
            admin.createTable(table.build());
            connection.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
