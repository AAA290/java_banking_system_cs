import java.io.*;
import java.net.*;

// 服务器
public class server_root {    //用于创建服务器并与客户端相连，实现客户端与服务器数据传输的类
   private db database;
   public server_root(){ 
      this.database=new db();
      ServerSocket server=null;   //服务器套接字
      Socket you=null;  //客户端
         try {  
            server=new ServerSocket(5000); //建立服务器套接字，端口为5000
         }catch(IOException e1){  
            System.out.println("异常，5000已被占用");  
         } 
      while(true) {   //一直在等待客户端，以及进行客户端的操作
         try {  
            System.out.println("正在监听5000端口");
            you=server.accept();              //等待客户机连接
            System.out.println("客户地址ַ:"+you.getInetAddress());
         }catch (IOException e) {  
            System.out.println("正在等待客户");  
         }
         if(you!=null) {  
            new ServerThread(database,you).start();    //如果有客户端连接，则为此客户端新建一个线程，并使线程开始工作
         }else{continue;}
      }
   }

   public static void main(String args[]) {  
     server_root s=new server_root();
   }
}