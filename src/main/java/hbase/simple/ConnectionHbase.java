package hbase.simple;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import java.io.IOException;

/**
 * Author: PHONGKH
 * B1: Khởi tạo configuaration
 * B2: Add file cấu hình của hbase client
 * B3: Connect
 */

public class ConnectionHbase {
    private  Connection connection;

  public   ConnectionHbase(){
        try {
            Configuration conf = HBaseConfiguration.create();
            conf.addResource("hbase-site.xml");

           this.connection = ConnectionFactory.createConnection(conf);




        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public Connection getConnection() {
        return connection;
    }
}
