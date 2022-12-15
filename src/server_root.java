import java.io.*;
import java.net.*;
//import java.util.*;

public class server_root { 
   public static void main(String args[]) {  
      ServerSocket server=null;   //服务器套接字
      //ServerThread thread;  //线程
      Socket you=null;  //客户端
         try {  
            server=new ServerSocket(4300); //建立服务器套接字，端口为33000
         }catch(IOException e1){  
            System.out.println("异常，4300正在监听");  
         } 
      while(true) {   //一直在等待客户端，以及进行客户端的操作
         try {  
            System.out.println("正在监听4300端口");
            you=server.accept();              //等待客户机连接
            System.out.println("客户地址ַ:"+you.getInetAddress());
         }catch (IOException e) {  
            System.out.println("正在等待客户");  
         }
         if(you!=null) {  
            new ServerThread(you).start();    //如果有客户端连接，则为此客户端新建一个线程，并使线程开始工作
         }else{continue;}
      }
   }
}