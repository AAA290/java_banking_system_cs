import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class graphic implements ActionListener{  //整个客户端页面的构造与实现的类
   Socket socket; 
   DataInputStream in;
   DataOutputStream out;
   client c;    //客户对象，存储/修改客户信息

   JFrame now;  //记录当前所在界面

   //查询界面
   JFrame f_inquery;
   JButton inquery;  
   JTextArea rs;

   //登录界面
   JFrame f_login;
   JButton login,register;
   JTextField user,password;

   //注册界面（待完成）

   //功能选择界面（待完成）

   //修改信息界面（待完成）

   //取款界面（待完成）

   //转账界面（待完成）

   //返回按钮
   JButton back_1;  //返回登录界面（新建的登录界面，相当于重新开始一个客户的操作）

   //前进按钮
   JButton next_1;  //转到查询界面

   public graphic(){     //构造方法
      //----------------------------------------------------------------------------------//
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
      //-----------------------------------以上为与远程服务器连接--------------------------//
      c=new client();  //当新建一个GUI界面时，新建一个客户对象，即当前操作该GUI界面的客户
      frame_login();  //进入登录界面
   }

//----------------------------------------------以下为界面创建--------------------------------------------//   

   public void frame_login(){      //登录界面
      f_login=new JFrame();
      user=new JTextField("name",12);
      password=new JTextField("password",12);
      login=new JButton("登录");
      register=new JButton("注册");
      next_1=new JButton("下一步");  
      Box  boxV1=Box.createVerticalBox();
      boxV1.add(new JLabel("用户名"));
      boxV1.add(new JLabel("密码"));
      Box boxV2=Box.createVerticalBox();
      boxV2.add(user);
      boxV2.add(password);
      Box baseBox=Box.createHorizontalBox();
      baseBox.add(boxV1);
      baseBox.add(boxV2);
      Container con=f_login.getContentPane();
      con.setLayout(new FlowLayout());
      con.add(login);
      con.add(register);
      con.add(next_1);
      con.add(baseBox);
      login.addActionListener(this);
      register.addActionListener(this);
      next_1.addActionListener(this);
      f_login.setBounds(100,100,360,300);
      f_login.setVisible(true);
      f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_login;
   }

   public void frame_register(){   //注册界面（待完成）

   }

   public void frame_function(){  //功能选择界面(待完成)

   }

   public void frame_modify(){  //修改信息界面（待完成）

   }

   public void frame_takemoney(){  //取款界面（待完成）

   }

   public void frame_transfer(){  //转账界面（待完成）

   }

   public void frame_inquery(){     //查询界面
      f_inquery=new JFrame();
      inquery=new JButton("查询账户余额");
      rs=new JTextArea(2,12);
      Container con=f_inquery.getContentPane();
      con.setLayout(new FlowLayout());
      con.add(inquery);
      con.add(new JScrollPane(rs));
      inquery.addActionListener(this);
      f_inquery.setBounds(100,100,360,300);
      f_inquery.setVisible(true);
      f_inquery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_inquery;
   }

//--------------------------------------以下为事件监听----------------------------------------//

   public void actionPerformed(ActionEvent e){    //按钮事件监听
      if(e.getSource()==inquery)  act_inquery_money();
      if(e.getSource()==login) act_login();
      if(e.getSource()==register) frame_switch_to_register();
      if(e.getSource()==back_1) frame_switch_to_login();
      if(e.getSource()==next_1) frame_switch_to_inquery();
   }

//------------------------------------------以下为事件实现-------------------------------------//

   public void act_login(){  //登录实现
      System.out.println("正在登录...");
      String s_user=user.getText(),
              s_password=password.getText();
      c.setName(s_user);
      c.setPassword(s_password);
      System.out.println("此时的用户为： 用户名为"+s_user+",密码为"+s_password+"的用户");
      try{
        out.writeUTF("query");
        String sql="select count(*) from users where name='"+s_user+"' and password='"+s_password+"';";
        out.writeUTF(sql);
      }
      catch(IOException ex){
        ex.printStackTrace();
      }
      try{
          int count=Integer.parseInt(in.readUTF());
          if(count!=0) System.out.println("登录成功");
          else System.out.println("不存在该用户或用户名密码不一致！");
      }
      catch(IOException ee){
        ee.printStackTrace();
    }
   }
 
   public void act_inquery_money(){   //查询实现
      System.out.println("正在查询中...");
      System.out.println("此时的用户为： 用户名为"+c.getName()+",密码为"+c.getPassword()+"的用户");
      try{
        out.writeUTF("query");
        String sql="select money from users where name='"+c.getName()+"' and password='"+c.getPassword()+"';";
        out.writeUTF(sql);
      }
      catch(IOException ex){
        ex.printStackTrace();
      }
      try{
          Double money=Double.parseDouble(in.readUTF());
          rs.setText("余额为:"+money);
          System.out.println("余额为:"+money);
      }
      catch(IOException ee){
        ee.printStackTrace();
        rs.setText("出现错误");
      }
  }

//--------------------------------------以下为界面转换实现(整体待优化)----------------------------//
 //代码相似度高，最好能修改后实现代码复用，减少方法的数量

  //从现在的页面跳转到查询页面(待优化)
  public void frame_switch_to_inquery(){
     now.setVisible(false);
     frame_inquery();
  }

  //从现在的页面跳转到登录页面（待优化）
  public void frame_switch_to_login(){
    now.setVisible(false);
    frame_login();
 }

  //从现在的页面跳转到注册界面(待优化)
  public void frame_switch_to_register(){  
     now.setVisible(false);
     frame_register();
  }

//------------------------------------------以下为创建实例-------------------------------------//

   public static void main(String[] args){
      graphic g=new graphic();
   }
}
