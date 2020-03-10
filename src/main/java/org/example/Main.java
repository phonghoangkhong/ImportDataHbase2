package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;

import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.mapreduce.LoadIncrementalHFiles;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Main {
    public static void main(String[] args) throws Exception {


        Configuration conf = HBaseConfiguration.create();
        conf.addResource("hbase-site.xml");

        // Create the job
        Job job = new Job(conf, "HBase Bulk Import Example");

        job.setJarByClass(App.class);
        job.setMapperClass(App.class);
        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(KeyValue.class);

        job.setInputFormatClass(TextInputFormat.class);

        // Get the table
        Connection conn= ConnectionFactory.createConnection(conf);
        HTableDescriptor table=new HTableDescriptor(TableName.valueOf("testImport"));

        // Auto configure partitioner and reducer
        HFileOutputFormat2.configureIncrementalLoadMap(job,table);

        // Save output path and input path
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));


        job.waitForCompletion(true);

//        // Load generated HFiles into table
//        HTable hTable= (HTable) conn.getTable(TableName.valueOf("testImport"));
//        LoadIncrementalHFiles loader = new LoadIncrementalHFiles(conf);
//        RegionLocator regionLocator=conn.getRegionLocator(TableName.valueOf("testImport"));
//        loader.doBulkLoad(new Path(args[1]),conn.getAdmin(),hTable,regionLocator);
    }
}
