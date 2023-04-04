import java.sql.*;

// 数据库
public class db{     //用于数据库相关操作的类
  Connection con;
  Statement state;
    public db(){
      String DBDriver="com.mysql.cj.jdbc.Driver";  //驱动类类名
      try{
        Class.forName(DBDriver);     //加载驱动类
        String url="jdbc:mysql://localhost/bank";  //连接名为bank的数据库
        String user="root";  
        String password="@feimabank";
        System.out.println("Connect to db...");
        con=DriverManager.getConnection(url,user,password);
        state=con.createStatement();
        System.out.println("Connect successfully.");  //成功连接
      } 
      catch(ClassNotFoundException e){
          System.out.println("ClassNotFoundException :"+e.getMessage());
      }
      catch(SQLException ex){
          System.out.println("SQLException :"+ex.getMessage());
      }
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

    public synchronized int db_count(String sql){  //mysql执行sql语句后，返回一个值，格式为String
      try{
        ResultSet rs=state.executeQuery(sql);
        //while(rs.next()) res = rs.getString(col);
        rs.next();
        return rs.getInt(1);
      }
      catch(SQLException ex){
        System.out.println("SQLException :"+ex.getMessage());
        return 0;
      }
      catch(Exception e){
        System.out.println("Exception :"+e.getMessage());
        return 0;
      }
    }

    public synchronized String[] db_query_m(String sql){  //mysql执行sql语句后，返回一个值（包含一位用户的信息），格式为String[]
      String[] res=new String[8];
      try{  
        ResultSet rs=state.executeQuery(sql);
        rs.next();
        res[0]=String.valueOf(rs.getInt(1));
        for(int i=1;i<=6;i++){
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

  public synchronized String[] db_query_m_r(String sql,int r){  //mysql执行sql语句后，返回条件库所有数据存入String[]
    String[] res=new String[r*8+10];                            //实现将筛选后的数据库内容放入数据流中
    try{
      ResultSet rs=state.executeQuery(sql);
      for(int i=0;i<r;i++) {
        rs.next();
        res[i * 8] = String.valueOf(rs.getInt(1));
        for (int j = 1; j <= 6; j++) {
          res[i * 8 + j] = rs.getString(j + 1);
        }
        res[i * 8 + 7] = String.valueOf(rs.getDouble(8));
      }
    }
    catch(SQLException ex){
      System.out.println("SQLException :"+ex.getMessage());
    }
    catch(Exception e){
      System.out.println("Exception :"+e.getMessage());
    }
    return res;
  }

    //用脚本文件进行初始化
    // public synchronized void db_initial(){  //对bank数据库进行初始化  
    //   System.out.println("数据库正在初始化...");
    //   try{
    //   state.execute("drop table if exists users;");  //如果原来存在该表，先删除原有的users表
    //   state.execute("create table users("                    //创建users表
    //   +"bank_ID int(10) unsigned zerofill auto_increment,"                //银行ID,由MySQL自动编号
    //   +"name varchar(10) not null default 'name',"           //姓名
    //   +"password varchar(10) not null default 'password',"   //密码
    //   +"identify_ID varchar(12) not null default 'identyid' unique,"            //身份证号（学号）
    //   +"tel varchar(11) default 'tel' not null,"                           //电话号码
    //   +"gender varchar(1) not null default 'F',"             //性别
    //   +"birth date not null default '2022-12-15',"           //出生日期
    //   +"money double not null default 0,"                    //金额
    //   +"primary key(bank_ID));"
    //   );
    //   /*
    //   state.execute("alter table users add constraint ck_tel check(REGEXP_like(tel,'[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' and len(tel)=11));");  
    //   //限制电话号码必须是数字且必须是11位
    //   考虑到限制条件应该在客户端GUI输入时就进行检查反馈，可以在此处不添加约束条件
    //   */
    //   state.execute("alter table users AUTO_INCREMENT=1000000000;");
    //   state.execute("insert into users(bank_ID,name,password) values(null,'manager','feimabank');");
    //   state.execute("insert into users values(null,'小红','AAA01','111111111111','12345678901','F','2000-12-15',80.0);");
    //   state.execute("insert into users values(null,'小明','BBB02','222222222222','12345678902','M','1975-4-23',70.0);");
    //   state.execute("insert into users values(null,'钮祜禄·甄嬛','CCC03','333333333333','12345678903','F','1988-6-6',10000.0);");
    //   state.execute("insert into users values(null,'乾隆帝爱新觉罗·弘历','abc00','567890123456','12345678904','M','1997-7-2',1000.0);");
    //   //初始状态向表中存入4组数据
    //   }
    //   catch(SQLException ex){
    //     System.out.println("SQLException :"+ex.getMessage());
    //   }
    //   catch(Exception e){
    //     System.out.println("Exception :"+e.getMessage());
    //   }
    //   System.out.println("数据库初始化完成");
    // }
}