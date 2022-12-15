import java.sql.*;

public class db{
  Connection con;
  Statement state;
    public db(){
      String DBDriver="com.mysql.cj.jdbc.Driver";  //驱动类类名
      try{
        Class.forName(DBDriver);     //加载驱动类
        String url="jdbc:mysql://localhost/bank";  //连接名为bank的数据库
        String user="root";  
        String password="root";
        System.out.println("Connect to db...");
        con=DriverManager.getConnection(url,user,password);
        state=con.createStatement();
        //state.execute("create table users");
      } 
      catch(ClassNotFoundException e){
          System.out.println("ClassNotFoundException :"+e.getMessage());
      }
      catch(SQLException ex){
          System.out.println("SQLException :"+ex.getMessage());
      }
      System.out.println("Connect successfully.");
    }

    public synchronized void db_execute(String sql){
      try{
        state.execute(sql);
      }
      catch(SQLException ex){
        System.out.println("SQLException :"+ex.getMessage());
      }
      catch(Exception e){
        System.out.println("Exception :"+e.getMessage());
      }
    }

    public synchronized String db_query(String sql){
      String res="";
      try{
        ResultSet rs=state.executeQuery(sql);
        //while(rs.next()) res = rs.getString(col);
        rs.next();
        res=rs.getString(1);
      }
      catch(SQLException ex){
        System.out.println("SQLException :"+ex.getMessage());
      }
      catch(Exception e){
        System.out.println("Exception :"+e.getMessage());
      }
      return res;
    }
     
    public static void main(String[] args){
       db database=new db();
       
    }
}