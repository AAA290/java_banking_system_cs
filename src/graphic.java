import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class graphic implements ActionListener{  //整个客户端页面的构造与实现的类
   //private actions ac;
   //Socket socket; 
   private DataInputStream in;
   private DataOutputStream out;
   private client c,s;    //客户对象，存储/修改客户信息

   JFrame now;  //记录当前所在界面

   //查询界面
   JFrame f_inquery;
   //JButton inquery;  
   JLabel rs;
   //JTextArea rs;

   //登录界面
   JFrame f_login;
   JButton login,register,find;
   //JTextField user;  //建议还是使用bank_ID登录，而不是姓名
   JTextField bankid;
   JPasswordField password;  //密码框

   //注册界面（待完成）
   JFrame f_enroll;
   JButton e1;
   //JTextField f1,f2,f4,f5,f6,f7,f8;
   JTextField f1,f2,f4,f5,f6,f7;
   JPasswordField f3;
    
   //管理员的功能选择界面（待完成）
   JFrame f_manager;
   JButton m1,m2,m3,m4,m5,m6;

   //管理员excel批量开户界面
   JFrame f_excelreadin;
   JTextField filepath;
   JButton readin;

   //管理员销户界面（待完成）
   JFrame f_closing;
   JButton c1;
   JTextField d1;
   JPasswordField d2;

   //管理员找回密码界面（待完成）
   JFrame f_findpass_manager;
   JButton m2_find;
   JTextField m_id,m_rep;  //建议还是使用bank_ID登录，而不是姓名

   //用户找回密码界面（待完成）
   JFrame f_find_user;
   JButton u_find,m_find,get_rp;
   JTextField u_tel;  //建议还是使用bank_ID登录，而不是姓名
   JPasswordField re_password;

   //重置密码界面（待完成）
   JFrame f_reset_password;
   JButton to_rp;
   JPasswordField password1;  //密码框
   JPasswordField password2;

   //客户的功能选择界面（待完成）
   JFrame f_user;
   JButton u1,u2,u3,u4,u5,u6;

   //修改信息界面
   JFrame f_modify;
   JButton modify;
   JTextField m_name,m_pass,m_tel,m_gender,m_birth;

   //取款界面（待完成）
   JFrame f_takemoney;
   JButton take;
   JTextField t_take;

   //存款界面
   JFrame f_putmoney;
   JButton save;
   JTextField t_save;

   //转账界面
   JFrame f_transfer;
   JButton transfer,yes;
   JTextField t_bankid,t_money;

   //提交销户申请的按钮
   JButton afdel;
   //返回按钮
   JButton back_1;  //返回登录界面（新建的登录界面，相当于重新开始一个客户的操作）

   //前进按钮
   JButton next_1;  //转到查询界面

   //退出按钮（线程结束）
   JButton exit_1;

   public graphic(){     //构造方法
      String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
      try{UIManager.setLookAndFeel(lookAndFeel);}catch(Exception e){e.printStackTrace();}
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
      s=new client();
      //ac=new actions(c,s);
      frame_login();  //进入登录界面
   }

//----------------------------------------------以下为界面创建--------------------------------------------//   

   public void frame_login(){      //登录界面
      f_login=new JFrame("登录");
      JPanel p=new JPanel();
      bankid=new JTextField();
      bankid.setPreferredSize(new Dimension(200,30));
      password=new JPasswordField();
      password.setPreferredSize(new Dimension(200,30));
      JLabel l1=new JLabel("账号");
      l1.setPreferredSize(new Dimension(50,30));
      JLabel l2=new JLabel("密码");
      l2.setPreferredSize(new Dimension(20,30));
      password.setEchoChar('*');    //用*遮掩密码
      find=new JButton("忘记密码");
      login=new JButton("登录");
      register=new JButton("注册");
      next_1=new JButton("下一步");
      //info=new JLabel("正在登录..."); 
      //info.setVisible(false); 
      Box  boxV1=Box.createVerticalBox();
      boxV1.add(l1);
      boxV1.add(Box.createVerticalStrut(15));
      boxV1.add(l2);
      Box boxV2=Box.createVerticalBox();
      boxV2.add(bankid);
      boxV2.add(Box.createVerticalStrut(10));
      boxV2.add(password);
      Box HBox3=Box.createHorizontalBox();
      HBox3.add(find);
      HBox3.add(Box.createHorizontalStrut(10));
      HBox3.add(login);
      HBox3.add(Box.createHorizontalStrut(10));
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
      find.addActionListener(this);
      login.addActionListener(this);
      register.addActionListener(this);
      f_login.setBounds(450,200,360,300);
      f_login.setVisible(true);
      f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_login;
   }

   public void frame_register(){   //注册界面
      f_enroll = new JFrame("注册");
      JPanel jd=new JPanel();
      f1 = new JTextField("Silver card number"); 
      f2 = new JTextField("name");
      f3 = new JPasswordField("password");
      f4 = new JTextField("identify_id");
      f5 = new JTextField("phone");
      f6 = new JTextField("sex");
      f7 = new JTextField("birthday");
      //f8 = new JTextField("money");
   
      f1.setPreferredSize(new Dimension(200,30));
      f2.setPreferredSize(new Dimension(200,30));
      f3.setPreferredSize(new Dimension(200,30));
      f4.setPreferredSize(new Dimension(200,30));
      f5.setPreferredSize(new Dimension(200,30));
      f6.setPreferredSize(new Dimension(200,30));
      f7.setPreferredSize(new Dimension(200,30));
      //f8.setPreferredSize(new Dimension(200,30));
      f3.setEchoChar('*');    //用*遮掩密码

      // JLabel j1=new JLabel("银行卡号");
      // j1.setPreferredSize(new Dimension(60,30));
      JLabel j2=new JLabel("姓名");
      j2.setPreferredSize(new Dimension(60,30));
      JLabel j3=new JLabel("密码");
      j3.setPreferredSize(new Dimension(60,30));
      JLabel j4=new JLabel("身份证号");
      j4.setPreferredSize(new Dimension(60,30));
      JLabel j5=new JLabel("手机号");
      j5.setPreferredSize(new Dimension(60,30));
      JLabel j6=new JLabel("性别");
      j6.setPreferredSize(new Dimension(60,30));
      JLabel j7=new JLabel("生日");
      j7.setPreferredSize(new Dimension(60,30));
      // JLabel j8=new JLabel("存钱");
      // j8.setPreferredSize(new Dimension(60,30));

      //JLabel a=new JLabel("1",5);
      e1=new JButton("完成");
      back_1=new JButton("取消");
      Box a1 = Box.createHorizontalBox();
      a1.add(new JLabel("银行卡号",SwingConstants.CENTER));  //可以考虑使用匿名内部类，缩减代码
      //(需要解决的问题：匿名内部类无法设置大小，可以考虑UIManager)
      a1.add(Box.createHorizontalStrut(3));
      a1.add(f1);
      Box a2 = Box.createHorizontalBox();
      a2.add(j2);
      a2.add(Box.createHorizontalStrut(3));
      a2.add(f2);
      Box a3 = Box.createHorizontalBox();
      a3.add(j3);
      a3.add(Box.createHorizontalStrut(3));
      a3.add(f3);
      Box a4 = Box.createHorizontalBox();
      a4.add(j4);
      a4.add(Box.createHorizontalStrut(3));
      a4.add(f4);
      Box a5 = Box.createHorizontalBox();
      a5.add(j5);
      a5.add(Box.createHorizontalStrut(3));
      a5.add(f5);
      Box a6 = Box.createHorizontalBox();
      a6.add(j6);
      a6.add(Box.createHorizontalStrut(3));
      a6.add(f6);
      Box a7 = Box.createHorizontalBox();
      a7.add(j7);
      a7.add(Box.createHorizontalStrut(3));
      a7.add(f7);
      // Box a8 = Box.createHorizontalBox();
      // a8.add(j8);
      // a8.add(Box.createHorizontalStrut(3));
      // a8.add(f8);
      Box b1 = Box.createHorizontalBox();
      b1.add(e1);
      b1.add(Box.createHorizontalStrut(3));
      b1.add(back_1);
      Box A = Box.createVerticalBox();
      A.add(a1);
      A.add(Box.createVerticalStrut(10));
      A.add(a2);
      A.add(Box.createVerticalStrut(10));
      A.add(a3);
      A.add(Box.createVerticalStrut(10));
      A.add(a4);
      A.add(Box.createVerticalStrut(10));
      A.add(a5);
      A.add(Box.createVerticalStrut(10));
      A.add(a6);
      A.add(Box.createVerticalStrut(10));
      A.add(a7);
      // A.add(Box.createVerticalStrut(10));
      // A.add(a8);
      A.add(Box.createVerticalStrut(10));
      A.add(b1);

      jd.add(A);
      f_enroll.add(jd);
      f_enroll.setLayout(null);
      jd.setBounds(7, 55, 400, 500);
      e1.addActionListener(this);
      back_1.addActionListener(this);
      f_enroll.setBounds(400,180,500,525);
      f_enroll.setVisible(true);
      f_enroll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_enroll;
   }

   public void frame_function_manager(){   //管理员的功能选择界面
      f_manager = new JFrame("管理员操作台");
      JPanel jm = new JPanel();
      m1 = new JButton("修改信息");
      m2 = new JButton("开户");
      m3 = new JButton("销户");
      m4 = new JButton("导入信息");
      m5 = new JButton("导出信息");
      m6 = new JButton("生成年报");
      back_1 = new JButton("退出");
      Box M = Box.createVerticalBox();
      M.add(m1);
      M.add(Box.createVerticalStrut(10));
      M.add(m2);
      M.add(Box.createVerticalStrut(10));
      M.add(m3);
      M.add(Box.createVerticalStrut(10));
      M.add(m4);
      M.add(Box.createVerticalStrut(10));
      M.add(m5);
      M.add(Box.createVerticalStrut(10));
      M.add(m6);
      M.add(Box.createVerticalStrut(10));
      M.add(back_1);
      jm.add(M);
      f_manager.add(jm);
      f_manager.setLayout(null);
      jm.setBounds(7, 55, 400, 500);
      m1.addActionListener(this);
      m2.addActionListener(this);
      m3.addActionListener(this);
      m4.addActionListener(this);
      m5.addActionListener(this);
      m6.addActionListener(this);
      back_1.addActionListener(this);
      f_manager.setBounds(400,180,500,525);
      f_manager.setVisible(true);
      f_manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_manager;
   }

  public void frame_excelreadin(){
      f_excelreadin=new JFrame("批量开户");
      filepath=new JTextField();
      filepath.setPreferredSize(new Dimension(100,30));
      readin=new JButton("确定");
      next_1=new JButton("返回");
      
      Box HB=Box.createHorizontalBox();
      HB.add(new JLabel("excel文件路径"));
      HB.add(Box.createHorizontalStrut(15));
      HB.add(filepath);
      Box HB1=Box.createHorizontalBox();
      HB1.add(readin);
      HB1.add(Box.createHorizontalStrut(15));
      HB1.add(next_1);
      Box bv=Box.createVerticalBox();
      bv.add(Box.createVerticalStrut(15));
      bv.add(HB);
      bv.add(Box.createVerticalStrut(20));
      bv.add(HB1);
      JPanel p=new JPanel();
      p.add(bv);
      f_excelreadin.add(p);
      f_excelreadin.setLayout(null);
      p.setBounds(5, 55, 360, 200);
      f_excelreadin.setBounds(430,200,360,300);
      f_excelreadin.setVisible(true);
      f_excelreadin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      readin.addActionListener(this);
      next_1.addActionListener(this);

      now=f_excelreadin;
  }

   public void frame_delete_manager(){   //管理员销户界面
      f_closing = new JFrame("管理员销户");
      JPanel jc = new JPanel();
      d1 = new JTextField("name");
      d2 = new JPasswordField("password");
      d1.setPreferredSize(new Dimension(200,30));
      d2.setPreferredSize(new Dimension(200,30));
      d2.setEchoChar('*');
      JLabel g1=new JLabel("姓名");
      g1.setPreferredSize(new Dimension(60,30));
      JLabel g2=new JLabel("密码");
      g2.setPreferredSize(new Dimension(60,30));
      c1 = new JButton("完成");
      back_1 = new JButton("退出");
      Box a1 = Box.createHorizontalBox();
      a1.add(g1);
      a1.add(Box.createHorizontalStrut(3));
      a1.add(d1);
      Box a2 = Box.createHorizontalBox();
      a2.add(g2);
      a2.add(Box.createHorizontalStrut(3));
      a2.add(d2);
      Box a3 = Box.createVerticalBox();
      a3.add(a1);
      a3.add(Box.createVerticalStrut(10));
      a3.add(a2);
      Box C = Box.createHorizontalBox();
      C.add(c1);
      C.add(Box.createHorizontalStrut(3));
      C.add(back_1);
      Box a4 = Box.createVerticalBox();
      a4.add(a3);
      a4.add(Box.createVerticalStrut(10));
      a4.add(C);
      jc.add(a4);
      f_closing.add(jc);
      f_closing.setLayout(null);
      jc.setBounds(7, 55, 400, 500);
      c1.addActionListener(this);
      back_1.addActionListener(this);
      f_closing.setBounds(400,180,500,525);
      f_closing.setVisible(true);
      f_closing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_closing;
   }

   public void frame_findpass_manager(){   //管理员找回密码界面（待完成）
      f_findpass_manager=new JFrame("用户找回密码");
      JPanel p=new JPanel();
      m_id=new JTextField("");
      m_rep=new JTextField("已完成输入");
      m_id.setPreferredSize(new Dimension(200,30));
      m_rep.setPreferredSize(new Dimension(200,30));
      JLabel fm1=new JLabel("管理员ID");
      fm1.setPreferredSize(new Dimension(65,30));
      JLabel fm2=new JLabel("请用工作邮箱发送您的管理员ID至邮箱feima@ja.com获取验证码");
      JLabel fm3=new JLabel("验证码");
      fm3.setPreferredSize(new Dimension(65,30));
      m2_find=new JButton("找回");
      back_1=new JButton("返回");
      Box boxV1=Box.createHorizontalBox();
      boxV1.add(fm1);
      boxV1.add(Box.createHorizontalStrut(10));
      boxV1.add(m_id);
      Box boxV2=Box.createHorizontalBox();
      boxV2.add(fm3);
      boxV2.add(Box.createHorizontalStrut(10));
      boxV2.add(m_rep);
      Box HBox3=Box.createHorizontalBox();
      HBox3.add(m2_find);
      HBox3.add(Box.createHorizontalStrut(30));
      HBox3.add(back_1);
      Box boxV3=Box.createVerticalBox();
      boxV3.add(boxV1);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(fm2);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(boxV2);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(HBox3);
      p.add(boxV3);
      f_findpass_manager.add(p);
      f_findpass_manager.setLayout(null);
      p.setBounds(7, 55, 550, 200);
      m2_find.addActionListener(this);
      back_1.addActionListener(this);
      f_findpass_manager.setBounds(450,200,590,300);
      f_findpass_manager.setVisible(true);
      f_findpass_manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_findpass_manager;
   }

   public void frame_find_user(){  // 用户找回密码界面1
      f_find_user=new JFrame("用户找回密码");
      JPanel p=new JPanel();
      u_tel=new JTextField("");
      u_tel.setPreferredSize(new Dimension(200,30));
      re_password=new JPasswordField("");
      re_password.setPreferredSize(new Dimension(80,30));
      JLabel fl1=new JLabel("电话号");
      fl1.setPreferredSize(new Dimension(45,30));
      JLabel fl2=new JLabel("验证码");
      fl2.setPreferredSize(new Dimension(45,30));
      get_rp=new JButton("获取验证码");
      m_find=new JButton("管理员找回密码");
      u_find=new JButton("找回");
      back_1=new JButton("返回");
      Box boxV1=Box.createHorizontalBox();
      boxV1.add(fl1);
      boxV1.add(Box.createHorizontalStrut(10));
      boxV1.add(u_tel);
      Box boxV2=Box.createHorizontalBox();
      boxV2.add(fl2);
      boxV2.add(Box.createHorizontalStrut(10));
      boxV2.add(re_password);
      boxV2.add(Box.createHorizontalStrut(10));
      boxV2.add(get_rp);
      Box HBox3=Box.createHorizontalBox();
      HBox3.add(m_find);
      HBox3.add(Box.createHorizontalStrut(10));
      HBox3.add(u_find);
      HBox3.add(Box.createHorizontalStrut(10));
      HBox3.add(back_1);
      Box boxV3=Box.createVerticalBox();
      boxV3.add(boxV1);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(boxV2);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(HBox3);
      p.add(boxV3);
      f_find_user.add(p);
      f_find_user.setLayout(null);
      p.setBounds(7, 55, 360, 200);
      get_rp.addActionListener(null);
      m_find.addActionListener(this);
      u_find.addActionListener(this);
      back_1.addActionListener(this);
      f_find_user.setBounds(450,200,440,300);
      f_find_user.setVisible(true);
      f_find_user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_find_user;
   }

   public void frame_reset_password() {   // 用户找回密码界面2--重置页面
      f_reset_password=new JFrame("重置密码");
      JPanel p=new JPanel();
      password1=new JPasswordField("");
      password1.setPreferredSize(new Dimension(200,30));
      password2=new JPasswordField("");
      password2.setPreferredSize(new Dimension(150,30));
      JLabel rl1=new JLabel("新密码");
      rl1.setPreferredSize(new Dimension(55,30));
      JLabel rl2=new JLabel("再次输入");
      rl2.setPreferredSize(new Dimension(55,30));
      password.setEchoChar('*');    //用*遮掩密码
      to_rp=new JButton("确认");
      back_1=new JButton("返回");
      Box boxV1=Box.createHorizontalBox();
      boxV1.add(rl1);
      boxV1.add(Box.createHorizontalStrut(10));
      boxV1.add(password1);
      Box boxV2=Box.createHorizontalBox();
      boxV2.add(rl2);
      boxV2.add(Box.createHorizontalStrut(10));
      boxV2.add(password2);
      Box HBox3=Box.createHorizontalBox();
      HBox3.add(to_rp);
      HBox3.add(Box.createHorizontalStrut(15));
      HBox3.add(back_1);
      Box boxV3=Box.createVerticalBox();
      boxV3.add(boxV1);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(boxV2);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(HBox3);
      p.add(boxV3);
      f_reset_password.add(p);
      f_reset_password.setLayout(null);
      p.setBounds(7, 55, 360, 200);
      to_rp.addActionListener(this);
      back_1.addActionListener(this);
      f_reset_password.setBounds(450,200,400,300);
      f_reset_password.setVisible(true);
      f_reset_password.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_reset_password;
   }

   public void frame_function(){  //客户功能选择界面(待完成)
      f_user = new JFrame("用户操作台");
      JPanel ju = new JPanel();
      u1 = new JButton("修改个人信息");
      u2 = new JButton("查询存款");
      u3 = new JButton("存款");
      u4 = new JButton("取款");
      u5 = new JButton("转账");
      u6 = new JButton("申请销户");
      back_1 = new JButton("退出");
      JLabel ul1=new JLabel("欢迎使用飞马银行系统");
      JLabel ul2=new JLabel("请选择您要进行的业务");
      ul1.setPreferredSize(new Dimension(150,70));
      ul2.setPreferredSize(new Dimension(150,70));
      Box UB1 = Box.createVerticalBox();
      UB1.add(u1);
      UB1.add(Box.createVerticalStrut(20));
      UB1.add(u2);
      UB1.add(Box.createVerticalStrut(20));
      UB1.add(u3);
      UB1.add(Box.createVerticalStrut(20));
      UB1.add(u4);
      UB1.add(Box.createVerticalStrut(30));
      Box UB2 = Box.createVerticalBox();
      UB2.add(Box.createVerticalStrut(50));
      UB2.add(ul1);
      UB2.add(Box.createVerticalStrut(20));
      UB2.add(ul2);
      Box UB3 = Box.createVerticalBox();
      UB3.add(u5);
      UB3.add(Box.createVerticalStrut(20));
      UB3.add(u6);
      UB3.add(Box.createVerticalStrut(20));
      UB3.add(back_1);
      UB3.add(Box.createVerticalStrut(70));
      ju.add(UB1);
      ju.add(Box.createHorizontalStrut(40));
      ju.add(UB2);
      ju.add(Box.createHorizontalStrut(35));
      ju.add(UB3);
      f_user.add(ju);
      f_user.setLayout(null);
      ju.setBounds(7, 35, 500, 300);
      u1.addActionListener(this);
      u2.addActionListener(this);
      u3.addActionListener(this);
      u4.addActionListener(this);
      u5.addActionListener(this);
      u6.addActionListener(this);
      back_1.addActionListener(this);
      f_user.setBounds(430,200,540,330);
      f_user.setVisible(true);
      f_user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_user;
   }

   public void frame_modify(){  //修改信息界面
      f_modify=new JFrame("修改信息");
      modify=new JButton("确认修改");
      next_1=new JButton(" 返回 ");
      
      m_name=new JTextField();
      m_name.setPreferredSize(new Dimension(50, 10));
      m_tel=new JTextField();
      m_tel.setPreferredSize(new Dimension(50, 10));
      m_pass=new JTextField();
      m_pass.setPreferredSize(new Dimension(50, 10));
      m_birth=new JTextField();
      m_birth.setPreferredSize(new Dimension(50, 10));
      m_gender=new JTextField();
      m_gender.setPreferredSize(new Dimension(50, 10));
      m_name.setText(c.getName());
      m_pass.setText(c.getPassword());
      m_tel.setText(c.getTel());
      m_gender.setText(String.valueOf(c.getGender()));
      m_birth.setText(c.getBirth());

      Box bh1= Box.createHorizontalBox();
      bh1.add(new JLabel("姓名  "));
      bh1.add(Box.createHorizontalStrut(3));
      bh1.add(m_name);
      Box bh2= Box.createHorizontalBox();
      bh2.add(new JLabel("密码  "));
      bh2.add(Box.createHorizontalStrut(3));
      bh2.add(m_pass);
      Box bh3= Box.createHorizontalBox();
      bh3.add(new JLabel("电话号码"));
      bh3.add(Box.createHorizontalStrut(3));
      bh3.add(m_tel);
      Box bh4= Box.createHorizontalBox();
      bh4.add(new JLabel("性别  "));
      bh4.add(Box.createHorizontalStrut(3));
      bh4.add(m_gender);
      Box bh5= Box.createHorizontalBox();
      bh5.add(new JLabel("出生日期"));
      bh5.add(Box.createHorizontalStrut(3));
      bh5.add(m_birth);
      Box bh6= Box.createHorizontalBox();
      bh6.add(modify);
      bh6.add(Box.createHorizontalStrut(3));
      bh6.add(next_1);

      Box bv = Box.createVerticalBox();
      bv.add(bh1);
      bv.add(Box.createVerticalStrut(10));
      bv.add(bh2);
      bv.add(Box.createVerticalStrut(10));
      bv.add(bh3);
      bv.add(Box.createVerticalStrut(10));
      bv.add(bh4);
      bv.add(Box.createVerticalStrut(10));
      bv.add(bh5);
      bv.add(Box.createVerticalStrut(10));
      bv.add(bh6);

      JPanel p=new JPanel();
      p.add(bv);
      f_modify.add(p);
      f_modify.setLayout(null);
      p.setBounds(2, 40, 360, 200);
      modify.addActionListener(this);
      next_1.addActionListener(this);
      f_modify.setBounds(430,200,360,300);
      f_modify.setVisible(true);
      f_modify.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_modify;
   }

   public void frame_takemoney(){  //取款界面
      f_takemoney=new JFrame("取款");
      take=new JButton("确认");
      next_1=new JButton("返回");
      JLabel l_take=new JLabel("取款数额:");
      t_take=new JTextField();
      t_take.setPreferredSize(new Dimension(100,30));
      Box HBox1=Box.createHorizontalBox();
      HBox1.add(l_take);
      HBox1.add(Box.createHorizontalStrut(20));
      HBox1.add(t_take);
      Box HBox2=Box.createHorizontalBox();
      HBox2.add(take);
      HBox2.add(Box.createHorizontalStrut(20));
      HBox2.add(next_1);
      Box boxTV=Box.createVerticalBox();
      boxTV.add(Box.createVerticalStrut(15));
      boxTV.add(HBox1);
      boxTV.add(Box.createVerticalStrut(30));
      boxTV.add(HBox2);
      JPanel p=new JPanel();
      p.add(boxTV);
      f_takemoney.add(p);
      f_takemoney.setLayout(null);
      p.setBounds(7, 55, 360, 200);
      take.addActionListener(this);
      next_1.addActionListener(this);
      f_takemoney.setBounds(430,200,360,300);
      f_takemoney.setVisible(true);
      f_takemoney.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_takemoney;
   }

   public void frame_putmoney(){   //存款界面（待完成）
     f_putmoney=new JFrame("存款");
     save=new JButton("确认");
     next_1=new JButton("返回");
     JLabel l_save=new JLabel("存款数额:");
     t_save=new JTextField();
     t_save.setPreferredSize(new Dimension(100, 30));
     Box HBox=Box.createHorizontalBox();
     HBox.add(l_save);
     HBox.add(Box.createHorizontalStrut(20));
     HBox.add(t_save);
     Box HBox2=Box.createHorizontalBox();
     HBox2.add(save);
     HBox2.add(Box.createHorizontalStrut(20));
     HBox2.add(next_1);

     Box boxV=Box.createVerticalBox();
     boxV.add(Box.createVerticalStrut(15));
     boxV.add(HBox);
     boxV.add(Box.createVerticalStrut(30));
     boxV.add(HBox2);
     JPanel p=new JPanel();
     p.add(boxV);
     f_putmoney.add(p);
     f_putmoney.setLayout(null);
     p.setBounds(2, 55, 360, 200);
     save.addActionListener(this);
     next_1.addActionListener(this);
     f_putmoney.setBounds(430,200,360,300);
     f_putmoney.setVisible(true);
     f_putmoney.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     now=f_putmoney;
   }

   public void frame_transfer(){  //转账界面
      f_transfer=new JFrame("转账");
      transfer=new JButton("确认转账");
      next_1=new JButton(" 返回 ");
      t_money=new JTextField();
      t_bankid=new JTextField();
      t_money.setPreferredSize(new Dimension(100, 30));
      t_bankid.setPreferredSize(new Dimension(100, 30));
      Box HBox=Box.createHorizontalBox();
      HBox.add(new JLabel("收款人账号"));
      HBox.add(Box.createHorizontalStrut(10));
      HBox.add(t_bankid);
      Box hb1=Box.createHorizontalBox();
      hb1.add(new JLabel("转账金额  "));
      hb1.add(Box.createHorizontalStrut(10));
      hb1.add(t_money);
      Box hb2=Box.createHorizontalBox();
      hb2.add(transfer);
      hb2.add(Box.createHorizontalStrut(15));
      hb2.add(next_1);
      Box boxV=Box.createVerticalBox();
      //boxV.add(Box.createVerticalStrut(15));
      boxV.add(HBox);
      boxV.add(Box.createVerticalStrut(15));
      boxV.add(hb1);
      boxV.add(Box.createVerticalStrut(30));
      boxV.add(hb2);
      JPanel p=new JPanel();
      p.add(boxV);
      f_transfer.add(p);
      f_transfer.setLayout(null);
      p.setBounds(0, 55, 360, 200);
      transfer.addActionListener(this);
      next_1.addActionListener(this);
      f_transfer.setBounds(430,200,360,300);
      f_transfer.setVisible(true);
      f_transfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_transfer;
   }

   public void frame_inquery(){     //查询界面
      f_inquery=new JFrame("查询余额");
      f_inquery.setLocationRelativeTo(null);
      //inquery=new JButton("查询账户余额");
      //rs=new JTextArea(2,12);
      next_1=new JButton("返回");
      rs=new JLabel("当前您的账户余额为："+c.getMoney());
      Box bv=Box.createVerticalBox();
      bv.add(rs);
      bv.add(Box.createVerticalStrut(15));
      bv.add(next_1);
      JPanel p=new JPanel();
      p.add(bv);
      f_inquery.add(p);
      f_inquery.setLayout(null);
      p.setBounds(5, 55, 360, 200);
      f_inquery.setBounds(430,200,360,300);
      f_inquery.setVisible(true);
      f_inquery.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      next_1.addActionListener(this);

      now=f_inquery;
   }

   public void frame_apdelect(){  //用户申请销户
      JFrame f_ad=new JFrame("申请销户");
      f_ad.setLayout(null);
      JLabel message_ad1=new JLabel();
      message_ad1.setText("请您再次确认提交销户申请");
      message_ad1.setVisible(true);
      afdel = new JButton("我已确认申请销户");
      next_1 = new JButton("返回");
      Box bad = Box.createHorizontalBox();
      bad.add(afdel);
      bad.add(Box.createHorizontalStrut(20));
      bad.add(next_1);
      JPanel p=new JPanel();
      p.add(message_ad1);
      p.add(Box.createVerticalStrut(40));
      p.add(bad);
      f_ad.add(p);
      p.setBounds(0, 40, 300, 100);
      afdel.addActionListener(this);
      next_1.addActionListener(this);
      next_1.setEnabled(true);
      f_ad.setBounds(480,210,300,200);
      f_ad.setVisible(true);
      f_ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_ad;
   }


//--------------------------------------以下为事件监听----------------------------------------//

   public void actionPerformed(ActionEvent e){    //按钮事件监听
      //if(e.getSource()==inquery)  act_inquery_money();
      if(e.getSource()==find) act_find_user();
      if(e.getSource()==u_find) act_reset_password();
      if(e.getSource()==to_rp) act_rel_rpassword();
      if(e.getSource()==m_find) act_findpass_manager();
      if(e.getSource()==m2_find) act_rel_rmanager();
      if(e.getSource()==login) act_login();
      if(e.getSource()==register) frame_switch_to_register();
      if(e.getSource()==take) act_takemoney();
      if(e.getSource()==save) act_putmoney();
      if(e.getSource()==modify) act_modify();
      if(e.getSource()==transfer) act_transfer();
      if(e.getSource()==yes) real_transfer();
      if(e.getSource()==readin) act_excelreadin();
      if(e.getSource()==back_1) frame_switch_to_login();
      if(e.getSource()==next_1) frame_switch_to_function();
      if(e.getSource()==e1) act_register(f2.getText(),new String(f3.getPassword()),f4.getText(),f5.getText(),f6.getText(),f7.getText());
      if(e.getSource()==m1) frame_switch_to_modify();
      if(e.getSource()==m2) frame_switch_to_register();
      if(e.getSource()==m3) frame_switch_to_delete();
      if(e.getSource()==m4) frame_switch_to_excelreadin();
      if(e.getSource()==m5) act_function_manager();
      if(e.getSource()==m6) act_generatepdf();
      if(e.getSource()==c1) act_delete_manager();
      if(e.getSource()==u1) frame_switch_to_modify();
      if(e.getSource()==u2) frame_switch_to_inquery();
      if(e.getSource()==u3) frame_switch_to_putmoney();
      if(e.getSource()==u4) frame_switch_to_takemoney();
      if(e.getSource()==u5) frame_switch_to_transfer();
      if(e.getSource()==u6) frame_switch_to_apdelect();
      if(e.getSource()==afdel) re_apdelect();
      if(e.getSource()==exit_1) System.exit(0);
   }

//------------------------------------------以下为事件实现-------------------------------------//


   public void act_login(){  //登录实现
      System.out.println("正在登录...");
      String s_bankid=bankid.getText(),
              s_password=new String(password.getPassword());
      if(!c.setBank_ID(s_bankid)){
         JOptionPane.showMessageDialog(null, "银行账号格式错误\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
      }
      if(!c.setPassword(s_password)){
         JOptionPane.showMessageDialog(null, "密码格式错误，密码不得少于4位！\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
      }
      if(c.setBank_ID(s_bankid)&&c.setPassword(s_password)){
         try{
            out.writeUTF("count");
            String sql="select count(*) from users where bank_ID='"+s_bankid+"' and password='"+s_password+"';";
            out.writeUTF(sql);
         }
         catch(IOException ex){
            ex.printStackTrace();
         }
         try{
            int count=in.readInt();
            if(count!=0) {
               if(!c.getBank_ID().equals("1000000000")){  //客户登录
                  System.out.println("客户"+c.getBank_ID()+"登录成功");

                  out.writeUTF("query_m");  //登录时把所有数据存入c中
                  out.writeUTF("select * from users where bank_ID="+c.getBank_ID()+";");  
                  in.readUTF();
                  c.setName(in.readUTF());
                  in.readUTF();
                  c.setIdentify_ID(in.readUTF());
                  c.setTel(in.readUTF());
                  c.setGender(in.readUTF().charAt(0));
                  c.setBirth(in.readUTF());
                  c.setMoney(Double.parseDouble(in.readUTF()));
                  c.setXiaohu(true);

                  JOptionPane.showMessageDialog(null, "客户"+c.getBank_ID()+",您已成功登录！\n欢迎使用飞马银行系统！","提示",2); 
                  frame_switch_to_function();
               }
               else{  //管理员登录
                  System.out.println("管理员登录成功");
                  JOptionPane.showMessageDialog(null, "管理员，欢迎登录！","提示",2); 
                  frame_switch_to_function_manager();
               }
            }
            else{
               JOptionPane.showMessageDialog(null, "不存在该用户或账号密码不一致！\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
            }
         }
         catch(IOException ee){
            ee.printStackTrace();
         }
      }
   }

    //注册实现
   public void act_register(String sname,String spassword,String sshenfen,String stel,String ssex,String sbirth){  
      System.out.println("正在注册...");
      //String sid = f1.getText();
      // String sname = f2.getText();
      // String spassword =new String(f3.getPassword());
		// String sshenfen = f4.getText();
      // String stel = f5.getText();
      // String ssex = f6.getText();
      // String sbirth = f7.getText();
	//	String  money = f8.getText();
      //  if(!c.setBank_ID(sid)){
      //       JOptionPane.showMessageDialog(null, "银行卡号输入格式错误！","提示",JOptionPane.ERROR_MESSAGE);       
      //    }
      if(!c.setName(sname)){
            JOptionPane.showMessageDialog(null, "用户名输入格式错误！","提示",JOptionPane.ERROR_MESSAGE); 
         }
      if(!c.setIdentify_ID(sshenfen)){
         JOptionPane.showMessageDialog(null, "身份证输入格式错误！","提示",JOptionPane.ERROR_MESSAGE); 
      }  
      if(!c.setPassword(spassword)){
            JOptionPane.showMessageDialog(null, "密码输入格式错误！","提示",JOptionPane.ERROR_MESSAGE); 
         }
      if(!c.setTel(stel)){
            JOptionPane.showMessageDialog(null, "电话输入格式错误！","提示",JOptionPane.ERROR_MESSAGE); 
         }
      if(!c.setBirth(sbirth)){
            JOptionPane.showMessageDialog(null, "生日输入格式错误！","提示",JOptionPane.ERROR_MESSAGE); 
         }
      if(c.setName(sname)&&c.setIdentify_ID(sshenfen)&&c.setPassword(spassword)&&c.setTel(stel)&&c.setBirth(sbirth)){ 
         try{
            out.writeUTF("execute");
            String sql=" insert into users(name,password,identify_ID,tel,gender,birth)values('"+sname+"','"+spassword+"','"+sshenfen+"','"+stel+"','"+ssex.charAt(0)+"'','"+sbirth+"');";
            out.writeUTF(sql);
            JOptionPane.showMessageDialog(null, "注册成功！","提示",JOptionPane.ERROR_MESSAGE);
            c.setMoney(2000.0);  //新客户送2000   
            out.writeUTF("query");
            out.writeUTF("select bank_ID from users where identify_ID='"+sshenfen+"';");
            c.setBank_ID(in.readUTF());
         }catch(IOException ex){
               ex.printStackTrace();
         }
      }
   }

   public void act_function_manager(){   //管理员的功能选择实现（待完成）

   }

   public void act_delete_manager(){   //管理员销户实现   //需要改
      System.out.println("正在销户...");
      String sname=d1.getText(),        //改成bank_ID
      spassword=new String(d2.getPassword());
         //先判断一下是否存在该人
      try{
            out.writeUTF("execute");
            String sql2="delete from users where name='"+sname+"' and password='"+spassword+"';";
            out.writeUTF(sql2);
            System.out.println("删除成功！");
            JOptionPane.showMessageDialog(null, "销户成功！","提示",JOptionPane.ERROR_MESSAGE); 
         } 
      catch(IOException ex){
         ex.printStackTrace();
      }
   }

   public void act_findpass_manager(){   //管理员找回密码实现（待完成）
      now.setVisible(false);
      frame_findpass_manager();
   }

   public void act_rel_rmanager(){
      now.setVisible(false);
      JFrame f_rm=new JFrame("重置成功");
      f_rm.setLayout(null);
      JLabel rmessage=new JLabel();
      rmessage.setText(c.getName()+",您已成功重置密码，密码为");
      rmessage.setVisible(true);
      back_1=new JButton("进入登录界面");
      JPanel p=new JPanel();
      p.add(rmessage);
      p.add(back_1);
      f_rm.add(p);
      p.setBounds(40, 40, 300, 100);
      back_1.addActionListener(this);
      back_1.setEnabled(true);
      f_rm.setBounds(500,250,400,200);
      f_rm.setVisible(true);
      f_rm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_rm;
   }

   public void act_find_user(){   //用户找回密码
      now.setVisible(false);
      frame_find_user();
   }

   public void act_reset_password(){  //重置密码
      now.setVisible(false);
      frame_reset_password();
   }

   public void act_rel_rpassword(){   //真正实现重置密码
      now.setVisible(false);
      JFrame f_ru=new JFrame("重置成功");
      f_ru.setLayout(null);
      JLabel rmessage=new JLabel();
      rmessage.setText(c.getName()+",您已成功重置密码，欢迎使用飞马银行系统！");
      rmessage.setVisible(true);
      back_1=new JButton("进入登录界面");
      JPanel p=new JPanel();
      p.add(rmessage);
      p.add(back_1);
      f_ru.add(p);
      p.setBounds(40, 40, 300, 100);
      back_1.addActionListener(this);
      back_1.setEnabled(true);
      f_ru.setBounds(500,250,400,200);
      f_ru.setVisible(true);
      f_ru.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_ru;
   }

   public void act_modify(){  //修改信息实现  //没有考虑输入不符合格式的情况
      System.out.println("正在修改信息中...");
      try {
         out.writeUTF("execute");
         String sql="update users set name ='"+m_name.getText()+"' where bank_ID="+c.getBank_ID()+";";
         if(!m_name.getText().equals(c.getName())){
            sql="update users set name ='"+m_name.getText()+"' where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            c.setName(m_name.getText());
            JOptionPane.showMessageDialog(null, "姓名修改成功","提示",2);
         }
         if(!m_pass.getText().equals(c.getPassword())){
            sql="update users set password ='"+m_pass.getText()+"' where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            c.setPassword(m_pass.getText());
            JOptionPane.showMessageDialog(null, "密码修改成功","提示",2);
         }
         if(!m_tel.getText().equals(c.getTel())){
            sql="update users set tel ='"+m_tel.getText()+"' where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            c.setTel(m_tel.getText());
            JOptionPane.showMessageDialog(null, "电话号码修改成功","提示",2);
         }
         if(!m_gender.getText().equals(String.valueOf(c.getGender()))){
            sql="update users set gender ='"+m_gender.getText()+"' where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            c.setGender(m_gender.getText().charAt(0));
            JOptionPane.showMessageDialog(null, "性别修改成功","提示",2);
         }
         if(!m_birth.getText().equals(c.getBirth())){
            sql="update users set birth ='"+m_birth.getText()+"' where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            c.setBirth(m_birth.getText());
            JOptionPane.showMessageDialog(null, "出生日期修改成功","提示",2);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void act_takemoney(){  //取款实现
      System.out.println("正在取款中...");
      try{
         if(!c.setMoney(c.getMoney()-Double.parseDouble(t_take.getText()))){
            JOptionPane.showMessageDialog(null, "取款失败，您的账户余额不足！\n当前账户余额为："+c.getMoney(),"提示",0);
            frame_switch_to_takemoney();
         }
         else{
            out.writeUTF("execute");
            String sql="update users set money = "+c.getMoney()+" where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            JOptionPane.showMessageDialog(null, "取款成功，当前您的账户余额为："+c.getMoney(),"提示",2);
            frame_switch_to_function();
         }
      }
      catch(IOException ex){
         ex.printStackTrace();
      }
   }

   public void act_putmoney(){   //存款实现
      System.out.println("正在存款中...");
      System.out.println("此时的用户为： 用户名为"+c.getName()+",密码为"+c.getPassword()+"的用户");
      try{
        c.setMoney(c.getMoney()+Double.parseDouble(t_save.getText()));
        out.writeUTF("execute");
        String sql="update users set money = "+c.getMoney()+" where bank_ID="+c.getBank_ID()+";";
        out.writeUTF(sql);
        JOptionPane.showMessageDialog(null, "存款成功，当前您的账户余额为："+c.getMoney(),"提示",2);
        frame_switch_to_function();
       }
      catch(IOException ex){
        ex.printStackTrace();
      }
   }

   public void act_transfer(){  //转账实现   //暂时没考虑转账的客户余额不足的问题
      System.out.println("正在转账中...");
      if(!s.setBank_ID(t_bankid.getText())){
         JOptionPane.showMessageDialog(null, "银行账号格式错误\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
      }
      else{
         try{
            out.writeUTF("count");
            String sql="select count(*) from users where bank_ID="+s.getBank_ID()+";";
            out.writeUTF(sql);
            int count=in.readInt();
            if(count==0) JOptionPane.showMessageDialog(null, "不存在该用户！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE); 
            else{
               //s.setBank_ID(t_bankid.getText());
               out.writeUTF("query");
               sql="select name from users where bank_ID="+s.getBank_ID()+";";
               out.writeUTF(sql);
               s.setName(in.readUTF());

               out.writeUTF("query");
               sql="select money from users where bank_ID="+s.getBank_ID()+";";
               out.writeUTF(sql);
               s.setMoney(Double.parseDouble(in.readUTF()));

               JFrame f_1=new JFrame("提示");
               f_1.setLayout(null);
               yes=new JButton("确认");
               JLabel message1=new JLabel();
               message1.setText("当前收款人姓名为"+s.getName()+",转账金额为"+t_money.getText());
               //JLabel message2=new JLabel("请在核实正确后点击"确认"进行转账");
               Box vb = Box.createVerticalBox();
               vb.add(message1);
               vb.add(Box.createVerticalStrut(10));
               vb.add(new JLabel("请在确认正确后进行转账"));
               Box HBox=Box.createHorizontalBox();
               HBox.add(yes);
               HBox.add(Box.createHorizontalStrut(5));
               HBox.add(next_1);
               //message.setVisible(true);
               JPanel p=new JPanel();
               p.add(vb);
               next_1.setText("取消");
               p.add(HBox);
               f_1.add(p);
               p.setBounds(0, 40, 300, 100);
               yes.addActionListener(this);
               next_1.addActionListener(this);
               f_1.setBounds(430,200,360,200);
               f_1.setVisible(true);
               f_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               now=f_1;
               //JOptionPane.showMessageDialog(null, "当前收款人姓名为"+in.readUTF()+",转账金额为"+t_money.getText()+"\n请在确认正确后进行转账","提示",JOptionPane.ERROR_MESSAGE);
            }
         }
         catch(IOException ex){
            ex.printStackTrace();
         }
      }
   }

   public void real_transfer(){  
       try {
         Double money=Double.parseDouble(t_money.getText());
         if(!c.setMoney(c.getMoney()-money)){
            JOptionPane.showMessageDialog(null, "取款失败，您的账户余额不足！\n当前账户余额为："+c.getMoney(),"提示",0);
            frame_switch_to_transfer();
         }
         else{
            s.setMoney(s.getMoney()+money);
            out.writeUTF("execute");
            String sql="update users set money = "+s.getMoney()+" where bank_ID="+s.getBank_ID()+";";
            out.writeUTF(sql);

            out.writeUTF("execute");
            sql="update users set money = "+c.getMoney()+" where bank_ID="+c.getBank_ID()+";"; 
            out.writeUTF(sql);
            JOptionPane.showMessageDialog(null, "转账成功，当前您的账户余额为："+c.getMoney(),"提示",2);
         }
       } catch (IOException e) {
         e.printStackTrace();
       }
       f_transfer.setVisible(false);
       frame_switch_to_function();
   }

   public void re_apdelect() {   //确认并完成申请销户实现
      now.setVisible(false);
      JFrame f_1=new JFrame("完成申请销户");
      f_1.setLayout(null);
      JLabel message_afd=new JLabel();
      message_afd.setText("已为您申请销户");
      message_afd.setVisible(true);
      back_1 = new JButton("退出");
      JPanel p=new JPanel();
      p.add(message_afd);
      p.add(back_1);
      f_1.add(p);
      p.setBounds(0, 40, 300, 100);
      back_1.addActionListener(this);
      back_1.setEnabled(true);
      f_1.setBounds(500,250,300,200);
      f_1.setVisible(true);
      f_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_1;
   }

   public void act_generatepdf(){  //生成pdf报表
     try {
         BaseFont bfComic = BaseFont.createFont("c://windows//fonts//SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
         Font font = new Font(bfComic,14);
         Font tfont = new Font(bfComic,25);
         Document d=new Document();
         String filename=".\\报表.pdf";
         PdfWriter.getInstance(d, new FileOutputStream(filename));
         d.open();
         Paragraph title=new Paragraph("飞马银行年度报表",tfont);
         title.setAlignment(Element.ALIGN_CENTER);
         d.add(title);
         d.add(new Paragraph(" "));
         out.writeUTF("count");
         out.writeUTF("select count(*) from users;");
         int num=in.readInt();
         d.add(new Paragraph("目前飞马银行中的账户总数："+(num-1),font));  //减去管理员
         out.writeUTF("last_year_num");
         d.add(new Paragraph("今年在飞马银行开户的新客户总数："+(num-1-in.readInt()),font));
         out.writeUTF("query");
         out.writeUTF("select sum(money) from users;");
         d.add(new Paragraph("目前飞马银行总存储金额："+Double.parseDouble(in.readUTF()),font));
         d.close();
         JOptionPane.showMessageDialog(null, "报表生成成功！","提示",2);
     } catch (IOException e) {
         System.out.println("IOException: ");
         e.printStackTrace();
     } 
     catch (DocumentException e){
         System.out.println("DocumentException: ");
         e.printStackTrace();
     }
   }

   public void act_excelreadin(){  //根据excel文件批量开户
      String filename=filepath.getText();
      if(!new File(filename).exists()) JOptionPane.showMessageDialog(null, "该文件不存在！","警告",0);
      else{
         if(filename.endsWith(".xlsx")){  //判断文件类型是不是.xlsx文件
            try {
               XSSFWorkbook xssfWb = new XSSFWorkbook(new FileInputStream(filename));
               for(int s = 0;s<xssfWb.getNumberOfSheets();s++) {
                  XSSFSheet sheet = xssfWb.getSheetAt(s);
                  int rownum = sheet.getLastRowNum();
                  for (int r = 1; r <=rownum; r++) {     //第0行为标题行，不读取
                     Date date = sheet.getRow(r).getCell(5).getDateCellValue();
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     String birth = sdf.format(date);
                     act_register(sheet.getRow(r).getCell(0).getStringCellValue(),
                        sheet.getRow(r).getCell(1).getStringCellValue(),
                        sheet.getRow(r).getCell(2).getStringCellValue(),
                        sheet.getRow(r).getCell(3).getStringCellValue(),
                        sheet.getRow(r).getCell(4).getStringCellValue(),
                        birth);
                  }
               }
            } catch (IOException e) {
               System.out.println("IOException：");
               e.printStackTrace();
            }
         }
         else{  //是.xls文件
            try {
               HSSFWorkbook hssfWb = new HSSFWorkbook(new FileInputStream(filename));
               for(int s = 0;s<hssfWb.getNumberOfSheets();s++) {
                  HSSFSheet sheet = hssfWb.getSheetAt(s);
                  int rownum = sheet.getLastRowNum();
                  for (int r = 1; r <= rownum; r++) {     //第0行为标题行，不读取
                     Date date = sheet.getRow(r).getCell(5).getDateCellValue();
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     String birth = sdf.format(date);
                     act_register(sheet.getRow(r).getCell(0).getStringCellValue(),
                        sheet.getRow(r).getCell(1).getStringCellValue(),
                        sheet.getRow(r).getCell(2).getStringCellValue(),
                        sheet.getRow(r).getCell(3).getStringCellValue(),
                        sheet.getRow(r).getCell(4).getStringCellValue(),
                        birth);
                  }
               }
           } catch (IOException e) {
               System.out.println("IOException：");
               e.printStackTrace();
           }
         }
         frame_switch_to_function_manager();
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
      now.setVisible(false);
      frame_putmoney();
  }

   //转到取款界面(待完成)
   public void frame_switch_to_takemoney(){
      now.setVisible(false);
      frame_takemoney();
   }

   //转到转账界面(待完成)
   public void frame_switch_to_transfer(){
      now.setVisible(false);
      frame_transfer();
   }

   //转到信息修改界面(待完成)
   public void frame_switch_to_modify(){
      now.setVisible(false);
      frame_modify();
   }

   //转到管理员找回密码界面
   public void frame_switch_to_findpass(){
      now.setVisible(false);
      frame_findpass_manager();
   }

   //转到管理员excel文件批量开户界面
   public void frame_switch_to_excelreadin(){
      now.setVisible(false);
      frame_excelreadin();
   } 

   //转到管理员销户界面(待完成)
   public void frame_switch_to_delete(){
      now.setVisible(false);
      frame_delete_manager();
   }

  //从现在的页面跳转到查询页面(待优化)
  public void frame_switch_to_inquery(){
      now.setVisible(false);
      frame_inquery();
  }

   //从现在的页面跳转到余额查询页面
   public void frame_switch_to_balance(){
      now.setVisible(false);
      frame_inquery();
   }

   //从现在的页面跳转到申请销户页面
   public void frame_switch_to_apdelect(){
      now.setVisible(false);
      frame_apdelect();
   }


//------------------------------------------以下为创建实例-------------------------------------//

   public static void main(String[] args){
      graphic g=new graphic();
   }
}
