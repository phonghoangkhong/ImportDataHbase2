package hbase.master;


import hbase.simple.ConnectionHbase;
import org.apache.hadoop.hbase.CompareOperator;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScanByFilter {
    public static void main(String[] args) throws IOException {
        ConnectionHbase connectionHbase=new ConnectionHbase();
        Connection connection=connectionHbase.getConnection();
        Table hTable = connection.getTable(TableName.valueOf("default","testhbase3"));


        List<Filter> filters = new ArrayList<Filter>();

        SingleColumnValueFilter filter = new SingleColumnValueExcludeFilter(
                Bytes.toBytes("cf1"),
                Bytes.toBytes("salary"),
                CompareOperator.LESS_OR_EQUAL,
                Bytes.toBytes(9342)
        );


        filters.add(filter);

        long d=System.currentTimeMillis();
        Scan scan = new Scan();
        FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ONE,filters);
        scan.setFilter(list);
        ResultScanner scanner =hTable.getScanner(scan);
        long d2=System.currentTimeMillis();
        System.out.println(d2-d);
        int i=0;
        for (Result result : scanner) {

            System.out.println("getRow:"+Bytes.toString(result.getRow()));

        }

        hTable.close();
       connection.close();

    }

}

