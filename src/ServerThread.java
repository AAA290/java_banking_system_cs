import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread{
    db database;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    public ServerThread(Socket socket){
       this.database=new db();
       this.socket=socket;
       try{
          in=new DataInputStream(socket.getInputStream());
          out=new DataOutputStream(socket.getOutputStream());
       }
       catch(Exception e){
          e.printStackTrace();
       }
    }

    public void run(){
      while(true){  
       try{
         String s_user=in.readUTF(),
                s_password=in.readUTF();
         System.out.println("ServerThread正在run...");
         //database=new db();
         Double money=inquery_money(s_user,s_password);
         out.writeUTF(Double.toString(money));
       }
       catch(IOException e){
          System.out.println("客户离开");
          break;
       }
      }
    }

    public synchronized double inquery_money(String name,String password){
       Double money;
       String sql;
       sql="select money from users where name='"+name+"' and password='"+password+"';";
       money=Double.parseDouble(database.db_query(sql));
       return money;
    }
}
