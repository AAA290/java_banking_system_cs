import java.sql.*;

//不需要再更改了
public class db{     //用于数据库相关操作的类
  Connection con;
  Statement state;
    public db(){
      String DBDriver="com.mysql.cj.jdbc.Driver";  //驱动类类名
      try{
        Class.forName(DBDriver);     //加载驱动类
        String url="jdbc:mysql://localhost/bank";  //连接名为bank的数据库
        String user="root";  
        String password="lzd17752";
        System.out.println("Connect to db...");
        con=DriverManager.getConnection(url,user,password);
        state=con.createStatement();
        db_initial();     //初始化
      } 
      catch(ClassNotFoundException e){
          System.out.println("ClassNotFoundException :"+e.getMessage());
      }
      catch(SQLException ex){
          System.out.println("SQLException :"+ex.getMessage());
      }
      System.out.println("Connect successfully.");  //成功连接
    }

    public synchronized String db_execute(String sql){     //mysql直接执行sql语句,返回是否执行成功
      try{
        state.execute(sql);
        return "true";
      }
      catch(SQLException ex){
        System.out.println("SQLException :"+ex.getMessage());
        return "false";
      }
      catch(Exception e){
        System.out.println("Exception :"+e.getMessage());
        return "false";
      }
    }

    public synchronized String db_query(String sql){  //mysql执行sql语句后，返回一个值，格式为String
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

    public synchronized String[] db_query_m(String sql){  //mysql执行sql语句后，返回一个值，格式为String[]
      String[] res=new String[8];
      try{  
        ResultSet rs=state.executeQuery(sql);
        rs.next();
        for(int i=0;i<=6;i++){
          res[i]= rs.getString(i+1);
        }
        res[7]=String.valueOf(rs.getInt(8));
      }
      catch(SQLException ex){
        System.out.println("SQLException :"+ex.getMessage());
      }
      catch(Exception e){
        System.out.println("Exception :"+e.getMessage());
      }
      return res;
    }
     
    public synchronized void db_initial(){  //对bank数据库进行初始化
      System.out.println("数据库正在初始化...");
      try{
      state.execute("drop table if exists users;");  //如果原来存在该表，先删除原有的users表
      state.execute("create table users("                    //创建users表
      +"bank_ID varchar(10) not null unique,"                //银行ID
      +"name varchar(10) not null default 'name',"           //姓名
      +"password varchar(10) not null default 'password',"   //密码
      +"identify_ID varchar(12) not null default 'identyid' unique,"            //身份证号（学号）
      +"tel varchar(11) default 'tel' not null,"                           //电话号码
      +"gender varchar(1) not null default 'F',"             //性别
      +"birth date not null default '2022-12-15',"           //出生日期
      +"money double not null default 0,"                    //金额
      +"primary key(bank_ID));"
      );
      /*
      state.execute("alter table users add constraint ck_tel check(REGEXP_like(tel,'[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' and len(tel)=11));");  
      //限制电话号码必须是数字且必须是11位
      考虑到限制条件应该在客户端GUI输入时就进行检查反馈，可以在此处不添加约束条件
      */
      state.execute("insert into users(bank_ID,name,password) values('0000000000','manager','feimabank');");
      state.execute("insert into users values('1234567891','小红','AAA01','111111111111','12345678901','F','2022-12-15',80.0);");  
      state.execute("insert into users values('1234567892','小明','BBB02','222222222222','12345678901','M','2022-12-15',70.0);");
      state.execute("insert into users values('1234567893','钮祜禄·甄嬛','CCC03','333333333333','12345678901','F','2022-12-15',10000.0);");
      state.execute("insert into users values('1234567894','乾隆帝爱新觉罗·弘历','abc00','567890123456','12345678901','M','2022-12-15',1000.0);");
      //初始状态向表中存入4组数据
      }
      catch(SQLException ex){
        System.out.println("SQLException :"+ex.getMessage());
      }
      catch(Exception e){
        System.out.println("Exception :"+e.getMessage());
      }
      System.out.println("数据库初始化完成");
    }

    public static void main(String[] args){
       db database=new db();
    }
}