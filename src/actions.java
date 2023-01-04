import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class actions extends JFrame{
    private client c,s;    //客户对象，存储/修改客户信息
    private DataInputStream in;
    private DataOutputStream out;

    public actions(client c,client s){
      this.c=c;
      this.s=s;
      Socket socket=new Socket();
      try{
        InetAddress address =InetAddress.getByName("127.0.0.1");
        InetSocketAddress socketAddress=new InetSocketAddress(address, 4300);
        socket.connect(socketAddress);
        in =new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
      }
      catch(Exception eex){
        eex.printStackTrace();
      }  
    }

   

}
