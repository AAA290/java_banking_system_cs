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
   JTextField user;
   JPasswordField password;  //密码框
   //JLabel info;

   //注册界面（待完成）
    
   //管理员的功能选择界面（待完成）

   //管理员销户界面（待完成）

   //管理员找回密码界面（待完成）

   //客户的功能选择界面（待完成）

   //修改信息界面（待完成）

   //取款界面（待完成）

   //存款界面（待完成）

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
      f_login=new JFrame("登录");
      JPanel p=new JPanel();
      user=new JTextField("name");
      user.setPreferredSize(new Dimension(200,30));
      password=new JPasswordField("password");
      password.setPreferredSize(new Dimension(200,30));
      JLabel l1=new JLabel("用户名");
      l1.setPreferredSize(new Dimension(50,30));
      JLabel l2=new JLabel("密码");
      l2.setPreferredSize(new Dimension(20,30));
      password.setEchoChar('*');    //用*遮掩密码
      login=new JButton("登录");
      register=new JButton("注册");
      next_1=new JButton("下一步");
      //info=new JLabel("正在登录..."); 
      //info.setVisible(false); 
      Box  boxV1=Box.createVerticalBox();
      boxV1.add(l1);
      boxV1.add(Box.createVerticalStrut(10));
      boxV1.add(l2);
      Box boxV2=Box.createVerticalBox();
      boxV2.add(user);
      boxV2.add(Box.createVerticalStrut(10));
      boxV2.add(password);
      Box HBox3=Box.createHorizontalBox();
      HBox3.add(login);
      HBox3.add(Box.createHorizontalStrut(5));
      HBox3.add(register);
      Box baseBox=Box.createHorizontalBox();
      baseBox.add(boxV1);
      baseBox.add(Box.createHorizontalStrut(3));
      baseBox.add(boxV2);
      Box boxV3=Box.createVerticalBox();
      boxV3.add(baseBox);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(HBox3);
      //boxV3.add(Box.createVerticalStrut(3));
      //boxV3.add(info);
      p.add(boxV3);
      f_login.add(p);
      f_login.setLayout(null);
      p.setBounds(7, 55, 360, 200);
      login.addActionListener(this);
      register.addActionListener(this);
      f_login.setBounds(450,200,360,300);
      f_login.setVisible(true);
      f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_login;
   }

   public void frame_register(){   //注册界面（待完成）

   }

   public void frame_function_manager(){   //管理员的功能选择界面（待完成）

   }   

   public void frame_delete_manager(){   //管理员销户界面（待完成）
   
   }

   public void frame_findpass_manager(){   //管理员找回密码界面（待完成）
   
   }

   public void frame_function(){  //客户功能选择界面(待完成)

   }

   public void frame_modify(){  //修改信息界面（待完成）

   }

   public void frame_takemoney(){  //取款界面（待完成）

   }

   public void frame_putmoney(){   //存款界面（待完成）

   }

   public void frame_transfer(){  //转账界面（待完成）

   }

   public void frame_inquery(){     //查询界面
      f_inquery=new JFrame("查询存款");
      f_inquery.setLocationRelativeTo(null);
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
      if(e.getSource()==next_1) frame_switch_to_function();
   }

//------------------------------------------以下为事件实现-------------------------------------//

   public void act_login(){  //登录实现
      System.out.println("正在登录...");
      String s_user=user.getText(),
             s_password=new String(password.getPassword());
      if(!c.setName(s_user)){
         JOptionPane.showMessageDialog(null, "用户名格式错误，用户名不得大于10个汉字字符！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
      }
      else if(!c.setPassword(s_password)){
         JOptionPane.showMessageDialog(null, "密码格式错误，密码不得少于4位！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
      }
      else{
         //info.setVisible(true);
         //System.out.println("此时的用户为： 用户名为"+s_user+",密码为"+s_password+"的用户");
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
            if(count!=0) {
               System.out.println("登录成功");
               //info.setText("登录成功");

               JFrame f_1=new JFrame("登录成功");
               f_1.setLayout(null);
               JLabel message=new JLabel();
               message.setText(c.getName()+",您已成功登录，欢迎使用飞马银行系统！");
               message.setVisible(true);
               JPanel p=new JPanel();
               p.add(message);
               p.add(next_1);
               f_1.add(p);
               p.setBounds(0, 40, 300, 100);
               next_1.addActionListener(this);
               next_1.setEnabled(true);
               f_1.setBounds(100,100,300,200);
               f_1.setVisible(true);
               f_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               now=f_1;
            }
            else{ 
            //System.out.println("不存在该用户或用户名密码不一致！\n请重新输入");
            JOptionPane.showMessageDialog(null, "不存在该用户或用户名密码不一致！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
            //info.setText("登录中...");
            //info.setVisible(false);
            }
         }
         catch(IOException ee){
            ee.printStackTrace();
         }
      }
   }

   public void act_register(){   //注册实现（待完成）

   }

   public void act_function_manager(){   //管理员的功能选择实现（待完成）

   }   

   public void act_delete_manager(){   //管理员销户实现（待完成）
   
   }

   public void act_findpass_manager(){   //管理员找回密码实现（待完成）
   
   }

   public void act_function(){  //客户功能选择实现(待完成)

   }

   public void act_modify(){  //修改信息实现（待完成）

   }

   public void act_takemoney(){  //取款实现（待完成）

   }

   public void act_putmoney(){   //存款实现（待完成）

   }

   public void act_transfer(){  //转账实现（待完成）

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

  //从现在的页面跳转到客户功能选择界面(待优化)
  public void frame_switch_to_function(){  
      now.setVisible(false);
      frame_function();
  }
   
  //从现在的页面跳转到管理员功能选择界面(待优化)
  public void frame_switch_to_function_manager(){  
      now.setVisible(false);
      frame_function_manager();
  }

  //转到存款界面(待完成)
  public void frame_switch_to_putmoney(){  

  }

  //转到取款界面(待完成)
  public void frame_switch_to_takemoney(){

  }

  //转到转账界面(待完成)
  public void frame_switch_to_transfer(){

  }

  //转到信息修改界面(待完成)
  public void frame_switch_to_modify(){

  }

  //转到管理员找回密码界面(待完成)
  public void frame_switch_to_findpass(){

  }

  //转到管理员销户界面(待完成)
  public void frame_switch_to_delete(){

  }

  //从现在的页面跳转到查询页面(待优化)
  public void frame_switch_to_inquery(){
      now.setVisible(false);
      frame_inquery();
  }
  
//------------------------------------------以下为创建实例-------------------------------------//

   public static void main(String[] args){
      graphic g=new graphic();
   }
}
