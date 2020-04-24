package hbase.simple;


import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.TableDescriptor;

import java.io.IOException;
import java.util.List;
/**
 * Author:PHONGKH
 * B1: connect hbase
 * B2: connect table
 * B3: Khởi tạo admin --> getList table
 */
public class SelectListTable {
    public static void main(String args[]){
        try {
            ConnectionHbase con = new ConnectionHbase();
            Connection connection = con.getConnection();
            System.out.println("da ket noi");

            Admin admin=connection.getAdmin();
            List<TableDescriptor> tableDescriptor= admin.listTableDescriptors();
            for (TableDescriptor i:tableDescriptor)
                System.out.println(i.getTableName());
            connection.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
