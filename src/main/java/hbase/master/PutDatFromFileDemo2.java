package hbase.master;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: PHONGKH
 * B1: Chạy file HBaseData trong package hbase.data
 * B2: Connection hbase
 * B3: Put data với đa luồng và với batch trong hbase
 */
public class PutDatFromFileDemo2 {

    public static void main(String args[]) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.addResource("hbase-site.xml");

        ExecutorService executorService = Executors.newFixedThreadPool(256); // co the tang tuy vao cpu client
        Connection connection= ConnectionFactory.createConnection(conf);
        String str2;
        long t1=System.currentTimeMillis();

        for (int x = 0; x < 2000; x++) {
            str2 = "file/testhbase";
            str2 += x + ".txt";
            String finalStr =str2;
            int finalX = x;

            executorService.submit(()->{



                HTable hTable = null;
                try {
                    hTable = (HTable) connection.getTable(TableName.valueOf("testhbase3"));

                    String[] str = null;
                    List<Put> list = new ArrayList<>();

                    File file = new File(finalStr);
                    Scanner scanner=new Scanner(new FileInputStream(file));
                    int i = finalX * 50000;
                    int j = 0;

                    String str3[];

                    while (scanner.hasNext()) {
                        String  br =scanner.nextLine();


                        str = br.split(";");

                        Put p = new Put(Bytes.toBytes(String.valueOf(i++)));
                        // put data (column family,column,value)
                        p.addColumn(Bytes.toBytes("cf1"), Bytes.toBytes("level"), Bytes.toBytes(str[0]));
                        p.addColumn(Bytes.toBytes("cf1"),
                                Bytes.toBytes("salary"), Bytes.toBytes(str[1]));

                        p.addColumn(Bytes.toBytes("cf2"),
                                Bytes.toBytes("from_date"), Bytes.toBytes(str[2]));

                        p.addColumn(Bytes.toBytes("cf2"), Bytes.toBytes("to_date"),
                                Bytes.toBytes(str[3]));
                        j++;
                        list.add(p);

                        if (j >= 1000) {
                            hTable.put(list);
                            list.clear();
                            j = 0;
                            Thread.sleep(20000);
                        }


                    }
                    Object[] res = new Object[list.size()];
                    try {
                        hTable.batch(list, res);

                    } catch (RetriesExhaustedException expected) {
                    }

                    hTable.close();


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(finalStr+" "+finalX);
                }


            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){

        }
        connection.close();
        long t2=System.currentTimeMillis();

        System.out.println("successs time "+(t2-t1));



    }




}
