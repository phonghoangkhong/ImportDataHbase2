package hbase.data;


import org.apache.commons.net.ntp.TimeStamp;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author :PHONGKH
 * Sinh 100 triệu row đa luồng
 */
public class HbaseData { public static void main(String args[]){
    ExecutorService executorService= Executors.newFixedThreadPool(10);
    String str="";
    for(int i=0;i<2000;i++){
        str="file/testhbase";
        str+=String.valueOf(i)+".txt";
        Runnable runnable=new Runnable2(str);
        executorService.submit(runnable);

    }
    executorService.shutdown();

}
    public static class Runnable2 implements Runnable{
        private String nameFile=null;
        Runnable2(String nameFile){
            this.nameFile=nameFile;
        }


        @Override
        public void run() {
            try{
                OutputStream out =new FileOutputStream(nameFile);

                String a="";
                long i=0;
                Random random=new Random();
                int intRamdom;
                long timeMillis=System.currentTimeMillis();
                TimeStamp timeStamp=null;
                String str=null;
                while(i<50000){
                    str="";
                    str+=String.valueOf(i);
                    str+=";";
                    intRamdom =random.nextInt(10000);
                    str+=String.valueOf(intRamdom);
                    str+=";";
                    timeStamp=new TimeStamp(timeMillis+i*10000);
                    str+=timeStamp.toDateString();
                    str+=";";
                    timeStamp=new TimeStamp(timeMillis+i*100000000);
                    str+=timeStamp.toDateString();
                    str+="\n";
                    out.write(str.getBytes());
                    i++;

                }
                out.close();



            }catch(FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
