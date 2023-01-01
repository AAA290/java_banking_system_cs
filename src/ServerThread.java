import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//不需要再更改了
public class ServerThread extends Thread{   //用于服务端与数据库传输数据的类
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
         String s_command=in.readUTF();
         String s_sql=in.readUTF();
         if(s_command.equals("query")) out.writeUTF(database.db_query(s_sql));
         if(s_command.equals("execute")) out.writeUTF(database.db_execute(s_sql));
         if(s_command.equals("query_m"))
            for(int i=0;i<=7;i++) out.writeUTF(database.db_query_m(s_sql)[i]);
       }
       catch(IOException e){
          System.out.println("客户离开");
          break;
       }
      }
    }

}
