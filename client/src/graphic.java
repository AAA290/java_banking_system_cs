import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.io.File;


//pdf
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

//excel
import jxl.*;
import jxl.write.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


// GUI界面及功能实现
public class graphic implements ActionListener{  //整个客户端页面的构造与实现的类
   private DataInputStream in;
   private DataOutputStream out;
   private client c,s;    //客户对象，存储/修改客户信息
   private int rp;        //用于修改密码的验证码
   private int kai_hu;    //分辨管理员(1)与用户(0)开户
   JFrame now;  //记录当前所在界面

   //查询界面
   JFrame f_inquery;

   //登录界面
   JFrame f_login;
   JButton login,register,find;
   JTextField bankid;
   JPasswordField password;  //密码框

   //注册界面
   JFrame f_enroll;
   JButton e1;
   JRadioButton JR1,JR2;
   JTextField f1,f2,f4,f5,f7;
   JPasswordField f3;
    
   //管理员的功能选择界面
   JFrame f_manager;
   JButton m2,m3,m4,m5,m6;

   //管理员excel批量开户界面
   JFrame f_excelreadin;
   JTextField filepath;
   JButton readin;

   // 导出信息界面
   JFrame f_expor_tdata;
   JTextField ed1,ed21,ed22,ed3,ed41,ed42,ed5;
   JButton edb;


   //用户找回密码界面
   JFrame f_find_user;
   JButton u_find,get_rp;
   JTextField u_tel,u_tid;
   JPasswordField re_password;

   //重置密码界面
   JFrame f_reset_password;
   JButton to_rp;
   JPasswordField password1;  //密码框
   JPasswordField password2;

   //客户的功能选择界面
   JFrame f_user;
   JButton u1,u2,u3,u4,u5,u6;

   //修改信息界面
   JFrame f_modify;
   JButton modify;
   JRadioButton F,M;
   JTextField m_name,m_pass,m_tel,m_birth;

   //取款界面
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
   JButton afdel_dox,afdel;
   //返回按钮
   JButton back_1;  //返回登录界面（新建的登录界面，相当于重新开始一个客户的操作）

   //前进按钮
   JButton next_1;  //转到用户操作台界面
   JButton next_m;  //转到管理员操作台界面


   //退出按钮（线程结束）
   JButton exit_1;

   public graphic(){     //构造方法
      String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
      try{UIManager.setLookAndFeel(lookAndFeel);}  //设置GUI外观
      catch(Exception e){e.printStackTrace();}
      //----------------------------------------------------------------------------------//
      Socket socket=new Socket();
      try{
        InetAddress address =InetAddress.getByName("120.46.171.200");
        InetSocketAddress socketAddress=new InetSocketAddress(address, 5000);
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
      bankid.setPreferredSize(new Dimension(150,30));
      password=new JPasswordField();
      password.setPreferredSize(new Dimension(150,30));
      JLabel l1=new JLabel("账号");
      l1.setPreferredSize(new Dimension(50,30));
      JLabel l2=new JLabel("密码");
      l2.setPreferredSize(new Dimension(20,30));
      password.setEchoChar('*');    //用*遮掩密码
      find=new JButton("忘记密码");
      login=new JButton("登录");
      register=new JButton("注册");
      next_1=new JButton("下一步");
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
      p.add(boxV3);
      f_login.add(p);
      f_login.setLayout(null);
      p.setBounds(7, 55, 360, 200);
      find.addActionListener(this);
      login.addActionListener(this);
      register.addActionListener(this);
      f_login.setBounds(560,310,360,300);
      f_login.setVisible(true);
      f_login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_login;
   }

   public void frame_register(){   //注册界面
      f_enroll = new JFrame("注册");
      JPanel jd=new JPanel(); 
      f2 = new JTextField();
      f3 = new JPasswordField();
      f4 = new JTextField();
      f5 = new JTextField();
      f7 = new JTextField();
      ButtonGroup JR=new ButtonGroup();
      JR1=new JRadioButton("女");
      JR2=new JRadioButton("男");
      JR1.setSelected(true);
      JR.add(JR1);
      JR.add(JR2);

      f2.setPreferredSize(new Dimension(200,30));
      f3.setPreferredSize(new Dimension(200,30));
      f4.setPreferredSize(new Dimension(200,30));
      f5.setPreferredSize(new Dimension(200,30));
      f7.setPreferredSize(new Dimension(200,30));
      f3.setEchoChar('*');    //用*遮掩密码

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

      e1=new JButton("完成");
      back_1=new JButton("取消");
      next_m=new JButton("取消");
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
      a6.add(Box.createHorizontalStrut(15));
      a6.add(JR1);
      a6.add(Box.createHorizontalStrut(8));
      a6.add(JR2);
      Box a7 = Box.createHorizontalBox();
      a7.add(j7);
      a7.add(Box.createHorizontalStrut(3));
      a7.add(f7);
      Box b1 = Box.createHorizontalBox();
      b1.add(e1);
      b1.add(Box.createHorizontalStrut(20));
      if(kai_hu==0) b1.add(back_1);
      else b1.add(next_m);
      Box A = Box.createVerticalBox();
      A.add(a2);
      A.add(Box.createVerticalStrut(10));
      A.add(a3);
      A.add(Box.createVerticalStrut(10));
      A.add(a4);
      A.add(Box.createVerticalStrut(10));
      A.add(a5);
      A.add(Box.createVerticalStrut(10));
      A.add(a7);
      A.add(Box.createVerticalStrut(10));
      A.add(a6);
      A.add(Box.createVerticalStrut(10));
      A.add(b1);

      jd.add(A);
      f_enroll.add(jd);
      f_enroll.setLayout(null);
      jd.setBounds(7, 55, 400, 500);
      e1.addActionListener(this);
      back_1.addActionListener(this);
      next_m.addActionListener(this);

      JR1.addActionListener(this);
      JR2.addActionListener(this);
      f_enroll.setBounds(520,290,450,450);
      f_enroll.setVisible(true);
      f_enroll.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_enroll;
   }

   public void frame_function_manager(){   //管理员的功能选择界面
      kai_hu=1;
      f_manager = new JFrame("管理员操作台");
      JPanel jm = new JPanel();
      m2 = new JButton("开户");
      m3 = new JButton("销户");
      m4 = new JButton("批量开户");
      m5 = new JButton("导出信息");
      m6 = new JButton("生成年报");
      back_1 = new JButton("退出");
      JLabel ml1=new JLabel("您已进入银行管理员页面");
      JLabel ml2=new JLabel("请选择您要进行的业务");
      ml1.setPreferredSize(new Dimension(150,70));
      ml2.setPreferredSize(new Dimension(150,70));
      Box M1 = Box.createVerticalBox();
      M1.add(m4);
      M1.add(Box.createVerticalStrut(20));
      M1.add(m5);
      M1.add(Box.createVerticalStrut(25));
      M1.add(m6);
      M1.add(Box.createVerticalStrut(45));
      Box M2 = Box.createVerticalBox();
      M2.add(Box.createVerticalStrut(50));
      M2.add(ml1);
      M2.add(Box.createVerticalStrut(20));
      M2.add(ml2);
      Box M3 = Box.createVerticalBox();
      M3.add(m2);
      M3.add(Box.createVerticalStrut(20));
      M3.add(m3);
      M3.add(Box.createVerticalStrut(20));
      M3.add(back_1);
      M3.add(Box.createVerticalStrut(50));
      jm.add(M1);
      jm.add(Box.createHorizontalStrut(40));
      jm.add(M2);
      jm.add(Box.createHorizontalStrut(35));
      jm.add(M3);
      f_manager.add(jm);
      f_manager.setLayout(null);
      jm.setBounds(7, 35, 500, 300);
      m2.addActionListener(this);
      m3.addActionListener(this);
      m4.addActionListener(this);
      m5.addActionListener(this);
      m6.addActionListener(this);
      back_1.addActionListener(this);
      f_manager.setBounds(530,300,540,330);
      f_manager.setVisible(true);
      f_manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now = f_manager;
   }

  public void frame_excelreadin(){    //批量开户
      f_excelreadin=new JFrame("批量开户");
      filepath=new JTextField();
      filepath.setPreferredSize(new Dimension(100,30));
      readin=new JButton("确定");
      next_m=new JButton("返回");
      
      Box HB=Box.createHorizontalBox();
      HB.add(new JLabel("excel文件路径"));
      HB.add(Box.createHorizontalStrut(15));
      HB.add(filepath);
      Box HB1=Box.createHorizontalBox();
      HB1.add(readin);
      HB1.add(Box.createHorizontalStrut(15));
      HB1.add(next_m);
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
      f_excelreadin.setBounds(560,310,360,300);
      f_excelreadin.setVisible(true);
      f_excelreadin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      readin.addActionListener(this);
      next_m.addActionListener(this);

      now=f_excelreadin;
  }

   public void frame_export_data(){   // 导出信息
      f_expor_tdata = new JFrame("导出信息");
      JPanel jd=new JPanel();
      ed1 = new JTextField("无设置");       // 查询维度Bank_ID
      ed21 = new JTextField("1");          //存款
      ed22 = new JTextField("1000000000");
      ed3 = new JTextField("无设置"); //性别
      ed41 = new JTextField("1943-1-1");          //年龄
      ed42 = new JTextField("2004-1-1");
      ed5 = new JTextField("无设置");          //姓名

      ed1.setPreferredSize(new Dimension(200,30));
      ed21.setPreferredSize(new Dimension(80,30));
      ed22.setPreferredSize(new Dimension(80,30));
      ed3.setPreferredSize(new Dimension(200,30));
      ed41.setPreferredSize(new Dimension(80,30));
      ed42.setPreferredSize(new Dimension(80,30));
      ed5.setPreferredSize(new Dimension(200,30));

      JLabel el1=new JLabel("银行账号");
      el1.setPreferredSize(new Dimension(60,30));
      JLabel el2=new JLabel("存款");
      el2.setPreferredSize(new Dimension(60,30));
      JLabel el21=new JLabel("<=存款数额<=");
      el21.setPreferredSize(new Dimension(80,30));
      JLabel el3=new JLabel("性别");
      el3.setPreferredSize(new Dimension(60,30));
      JLabel el4=new JLabel("年龄");
      el4.setPreferredSize(new Dimension(60,30));
      JLabel el41=new JLabel("<=生日范围<=");
      el41.setPreferredSize(new Dimension(80,30));
      JLabel el5=new JLabel("姓名");
      el5.setPreferredSize(new Dimension(60,30));

      edb=new JButton("导出");
      next_m=new JButton("返回");
      Box a1 = Box.createHorizontalBox();
      a1.add(el1);
      a1.add(Box.createHorizontalStrut(3));
      a1.add(ed1);
      Box a2 = Box.createHorizontalBox();
      a2.add(el2);
      a2.add(Box.createHorizontalStrut(3));
      a2.add(ed21);
      a2.add(Box.createHorizontalStrut(3));
      a2.add(el21);
      a2.add(Box.createHorizontalStrut(3));
      a2.add(ed22);
      Box a3 = Box.createHorizontalBox();
      a3.add(el3);
      a3.add(Box.createHorizontalStrut(3));
      a3.add(ed3);
      Box a4 = Box.createHorizontalBox();
      a4.add(el4);
      a4.add(Box.createHorizontalStrut(3));
      a4.add(ed41);
      a4.add(Box.createHorizontalStrut(3));
      a4.add(el41);
      a4.add(Box.createHorizontalStrut(3));
      a4.add(ed42);
      Box a5 = Box.createHorizontalBox();
      a5.add(el5);
      a5.add(Box.createHorizontalStrut(3));
      a5.add(ed5);

      Box b1 = Box.createHorizontalBox();
      b1.add(edb);
      b1.add(Box.createHorizontalStrut(25));
      b1.add(next_m);
      Box A = Box.createVerticalBox();
      A.add(a1);
      A.add(Box.createVerticalStrut(15));
      A.add(a2);
      A.add(Box.createVerticalStrut(15));
      A.add(a3);
      A.add(Box.createVerticalStrut(15));
      A.add(a4);
      A.add(Box.createVerticalStrut(15));
      A.add(a5);
      A.add(Box.createVerticalStrut(25));
      A.add(b1);

      jd.add(A);
      f_expor_tdata.add(jd);
      f_expor_tdata.setLayout(null);
      jd.setBounds(7, 55, 450, 450);
      edb.addActionListener(this);
      next_m.addActionListener(this);
      f_expor_tdata.setBounds(560,310,500,475);
      f_expor_tdata.setVisible(true);
      f_expor_tdata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_expor_tdata;
   }


   public void frame_find_user(){  // 用户找回密码界面1
      f_find_user=new JFrame("用户找回密码");
      JPanel p=new JPanel();
      u_tid=new JTextField("");
      u_tid.setPreferredSize(new Dimension(200,30));
      u_tel=new JTextField("");
      u_tel.setPreferredSize(new Dimension(200,30));
      re_password=new JPasswordField("");
      re_password.setPreferredSize(new Dimension(80,30));
      JLabel fl0=new JLabel("银行ID");
      fl0.setPreferredSize(new Dimension(45,30));
      JLabel fl1=new JLabel("电话号");
      fl1.setPreferredSize(new Dimension(45,30));
      JLabel fl2=new JLabel("验证码");
      fl2.setPreferredSize(new Dimension(45,30));
      get_rp=new JButton("获取验证码");
      u_find=new JButton("找回");
      back_1=new JButton("返回");
      Box boxV0=Box.createHorizontalBox();
      boxV0.add(fl0);
      boxV0.add(Box.createHorizontalStrut(10));
      boxV0.add(u_tid);
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
      HBox3.add(u_find);
      HBox3.add(Box.createHorizontalStrut(30));
      HBox3.add(back_1);
      Box boxV3=Box.createVerticalBox();
      boxV3.add(boxV0);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(boxV1);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(boxV2);
      boxV3.add(Box.createVerticalStrut(15));
      boxV3.add(HBox3);
      p.add(boxV3);
      f_find_user.add(p);
      f_find_user.setLayout(null);
      p.setBounds(7, 55, 360, 200);
      get_rp.addActionListener(this);
      u_find.addActionListener(this);
      back_1.addActionListener(this);
      f_find_user.setBounds(560,310,440,300);
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
      f_reset_password.setBounds(560,310,400,300);
      f_reset_password.setVisible(true);
      f_reset_password.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_reset_password;
   }

   public void frame_function(){  //客户功能选择界面
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
      f_user.setBounds(530,300,540,330);
      f_user.setVisible(true);
      f_user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_user;
   }

   public void frame_modify(){  //修改信息界面
      f_modify=new JFrame("修改信息");
      modify=new JButton("确认修改");
      next_1=new JButton(" 返回 ");

      ButtonGroup JR=new ButtonGroup();
      F=new JRadioButton("女");
      M=new JRadioButton("男");
      JR.add(F);
      JR.add(M);
      m_name=new JTextField();
      m_name.setPreferredSize(new Dimension(150, 30));
      m_tel=new JTextField();
      m_tel.setPreferredSize(new Dimension(150, 30));
      m_pass=new JTextField();
      m_pass.setPreferredSize(new Dimension(150, 30));
      m_birth=new JTextField();
      m_birth.setPreferredSize(new Dimension(150, 30));
      m_name.setText(c.getName());
      m_pass.setText(c.getPassword());
      m_tel.setText(c.getTel());
      if(c.getGender()=='F')  F.setSelected(true);
      else M.setSelected(true);
      m_birth.setText(c.getBirth());

      Box bh1= Box.createHorizontalBox();
      bh1.add(new JLabel("姓名    "));
      bh1.add(Box.createHorizontalStrut(3));
      bh1.add(m_name);
      Box bh2= Box.createHorizontalBox();
      bh2.add(new JLabel("密码    "));
      bh2.add(Box.createHorizontalStrut(3));
      bh2.add(m_pass);
      Box bh3= Box.createHorizontalBox();
      bh3.add(new JLabel("电话号码"));
      bh3.add(Box.createHorizontalStrut(3));
      bh3.add(m_tel);
      Box bh4= Box.createHorizontalBox();
      bh4.add(new JLabel("性别    "));
      bh4.add(Box.createHorizontalStrut(8));
      bh4.add(F);
      bh4.add(Box.createHorizontalStrut(20));
      bh4.add(M);
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
      p.setBounds(2, 40, 360, 400);
      modify.addActionListener(this);
      next_1.addActionListener(this);
      f_modify.setBounds(560,310,360,430);
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
      f_takemoney.setBounds(560,310,360,300);
      f_takemoney.setVisible(true);
      f_takemoney.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_takemoney;
   }

   public void frame_putmoney(){   //存款界面
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
     f_putmoney.setBounds(560,310,360,300);
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
      f_transfer.setBounds(560,310,360,300);
      f_transfer.setVisible(true);
      f_transfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_transfer;
   }

   public void frame_inquery(){     //查询界面
      f_inquery=new JFrame("查询余额");
      f_inquery.setLocationRelativeTo(null);
      next_1=new JButton("返回");
      JLabel rs1=new JLabel("当前您的账户余额为：");
      JLabel rs2=new JLabel("    "+c.getMoney());

      Box bv=Box.createVerticalBox();
      bv.add(rs1);
      bv.add(Box.createVerticalStrut(5));
      bv.add(rs2);
      bv.add(Box.createVerticalStrut(15));
      bv.add(next_1);
      JPanel p=new JPanel();
      p.add(bv);
      f_inquery.add(p);
      f_inquery.setLayout(null);
      p.setBounds(5, 55, 360, 200);
      f_inquery.setBounds(560,310,350,250);
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
      afdel_dox = new JButton("《飞马银行用户销户文件》");
      afdel = new JButton("我已阅读并确认销户");
      next_1 = new JButton("返回");
      Box apall = Box.createVerticalBox();
      apall.add(message_ad1);
      apall.add(Box.createVerticalStrut(30));
      apall.add(afdel_dox);
      apall.add(Box.createVerticalStrut(30));
      apall.add(afdel);
      apall.add(Box.createVerticalStrut(30));
      apall.add(next_1);
      JPanel p=new JPanel();
      p.add(apall);
      f_ad.add(p);
      p.setBounds(0, 40, 450, 250);
      afdel_dox.addActionListener(this);
      afdel.addActionListener(this);
      next_1.addActionListener(this);
      next_1.setEnabled(true);
      f_ad.setBounds(580,320,450,300);
      f_ad.setVisible(true);
      f_ad.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      now=f_ad;
   }


//--------------------------------------以下为事件监听----------------------------------------//

   public void actionPerformed(ActionEvent e){    //按钮事件监听
      if(e.getSource()==find) frame_switch_to_finduser();
      if(e.getSource()==u_find) act_reset_password();
      if(e.getSource()==to_rp) act_rel_rpassword();
      if(e.getSource()==edb) act_exportdata();
      if(e.getSource()==get_rp) act_rel_rp();
      if(e.getSource()==login) act_login();
      if(e.getSource()==register) frame_switch_to_register();
      if(e.getSource()==take) act_takemoney();
      if(e.getSource()==save) act_putmoney();
      if(e.getSource()==modify) {
         String gender=F.isSelected()?"F":"M";
         act_modify(m_name.getText(),m_pass.getText(),m_tel.getText(),gender,m_birth.getText());
      }
      if(e.getSource()==transfer) act_transfer();
      if(e.getSource()==yes) real_transfer();
      if(e.getSource()==readin) act_excelreadin();
      if(e.getSource()==back_1) frame_switch_to_login();
      if(e.getSource()==next_1) frame_switch_to_function();
      if(e.getSource()==next_m) frame_switch_to_function_manager();
      if(e.getSource()==e1) {
         String ssex=JR1.isSelected()?"F":"M";
         act_register(f2.getText(),new String(f3.getPassword()),f4.getText(),f5.getText(),ssex,f7.getText());
      }
      if(e.getSource()==m2) frame_switch_to_register();
      if(e.getSource()==m3) act_delete_manager();
      if(e.getSource()==m4) frame_switch_to_excelreadin();
      if(e.getSource()==m5) frame_switch_to_exportdata();
      if(e.getSource()==m6) act_generatepdf();
      if(e.getSource()==u1) frame_switch_to_modify();
      if(e.getSource()==u2) frame_switch_to_inquery();
      if(e.getSource()==u3) frame_switch_to_putmoney();
      if(e.getSource()==u4) frame_switch_to_takemoney();
      if(e.getSource()==u5) frame_switch_to_transfer();
      if(e.getSource()==u6) frame_switch_to_apdelect();
      if(e.getSource()==afdel_dox) JOptionPane.showMessageDialog(null, "《飞马银行用户销户文件》\n确认销户并承担一切责任","文件",1);
      if(e.getSource()==afdel) re_apdelect();
      if(e.getSource()==exit_1) System.exit(0);
   }

//------------------------------------------以下为事件实现-------------------------------------//


   public void act_login(){  //登录实现
      System.out.println("正在登录...");
      kai_hu=0;
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
                  //c.setXiaohu(true);

                  JOptionPane.showMessageDialog(null, "客户"+c.getBank_ID()+",您已成功登录！\n欢迎使用飞马银行系统！","提示",1); 
                  frame_switch_to_function();
               }
               else{  //管理员登录
                  System.out.println("管理员登录成功");
                  JOptionPane.showMessageDialog(null, "管理员，欢迎登录！","提示",1); 
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
      if(!c.setName(sname)){
            JOptionPane.showMessageDialog(null, "用户名输入格式错误！","警告",JOptionPane.ERROR_MESSAGE); 
         }
      if(!c.setIdentify_ID(sshenfen)){
         JOptionPane.showMessageDialog(null, "身份证输入格式错误！","警告",JOptionPane.ERROR_MESSAGE); 
      }  
      if(!c.setPassword(spassword)){
            JOptionPane.showMessageDialog(null, "密码输入格式错误！","警告",JOptionPane.ERROR_MESSAGE); 
      }
      if(!c.setTel(stel)){
            JOptionPane.showMessageDialog(null, "电话输入格式错误！","警告",JOptionPane.ERROR_MESSAGE); 
      }
      if(!c.setBirth(sbirth)){
            JOptionPane.showMessageDialog(null, "生日输入格式错误！","警告",JOptionPane.ERROR_MESSAGE); 
      }
      try{
         SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
         Date birth=sdf.parse(sbirth);
         Calendar cal = Calendar.getInstance();
         cal.setTime(new Date());
         cal.add(Calendar.YEAR, -18);
         Date time_of_18_years_ago =cal.getTime();
         if(birth.compareTo(time_of_18_years_ago)>0)       //未满十八周岁不能开户
               JOptionPane.showMessageDialog(null, "您未满十八周岁，无法开户！","警告",JOptionPane.ERROR_MESSAGE); 

         if(c.setName(sname)&&c.setIdentify_ID(sshenfen)&&c.setPassword(spassword)&&c.setTel(stel)&&c.setBirth(sbirth)&&birth.compareTo(time_of_18_years_ago)<=0){ 
         out.writeUTF("execute");
         String sql=" insert into users(name,password,identify_ID,tel,gender,birth,money) values('"+sname+"','"+spassword+"','"+sshenfen+"','"+stel+"','"+ssex.charAt(0)+"','"+sbirth+"',"+2000+");";
         out.writeUTF(sql);
         c.setMoney(2000.0);    //新客户送2000 
         out.writeUTF("query");
         out.writeUTF("select bank_ID from users where identify_ID='"+sshenfen+"';");
         c.setBank_ID(in.readUTF());
         JOptionPane.showMessageDialog(null, "注册成功！","提示",1);
         if(kai_hu==0)frame_switch_to_function();
         else frame_switch_to_function_manager();
         }
      }catch(IOException ex){
            ex.printStackTrace();
      }catch(ParseException e){
         e.printStackTrace();
      }
   }

   public void act_delete_manager(){   //管理员自动销户70以上老人的实现  
      SimpleDateFormat f= new SimpleDateFormat("yyyy-MM-dd");
      Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -70);
		String time_of_70_years_ago =f.format(cal.getTime());

      try{
            out.writeUTF("execute");
            String sql2="delete from users where birth<= '"+time_of_70_years_ago+"';";
            out.writeUTF(sql2);
            System.out.println("销户成功！");
            JOptionPane.showMessageDialog(null, "年满70岁及以上的老人全部销户成功！","提示",1); 
         } 
      catch(IOException ex){
         ex.printStackTrace();
      }
   }

   public void act_rel_rp(){   //验证码回复界面
      if(!c.setBank_ID(u_tid.getText())){
         JOptionPane.showMessageDialog(null, "银行账号格式错误\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
      }
      if(!c.setTel(u_tel.getText())){
         JOptionPane.showMessageDialog(null, "手机号格式错误\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
      }
      if(c.setBank_ID(u_tid.getText())&&c.setTel(u_tel.getText())){
         try{
            out.writeUTF("count");
            String sql="select count(*) from users where tel='" + u_tel.getText() + "' and bank_ID='" + u_tid.getText() + "';";
            out.writeUTF(sql);
            int count=in.readInt();
            if(count!=0) {
               for (int i = 0; i <= 20; i++) {
                  int flag = new Random().nextInt(999999);
                  if (flag < 100000) {
                     flag += 100000;
                  }
                  rp = flag;
               }
               System.out.println(rp);
               JOptionPane.showMessageDialog(null, "您的验证码为：" + rp, "提示", 1);
            }
            else JOptionPane.showMessageDialog(null, "该账号不存在或账号与手机号不匹配\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
         }
         catch(IOException ex){
            ex.printStackTrace();
         }}

   }

   public void act_reset_password(){  //重置密码
      if( Integer.parseInt(new String(re_password.getPassword()))==rp){
         now.setVisible(false);
         frame_reset_password();
      }
      else{
         JOptionPane.showMessageDialog(null, "验证码输入错误\n请重新输入或选择重新获取","警告",JOptionPane.ERROR_MESSAGE);
      }
   }

   public void act_rel_rpassword(){   //真正实现重置密码
      try{
         String p1=new String(password1.getPassword());
         String p2=new String(password2.getPassword());
         if(!c.setPassword(p1)|| !c.setPassword(p2)){
            JOptionPane.showMessageDialog(null, "密码输入格式错误！","警告",JOptionPane.ERROR_MESSAGE);
         }
         else if( p2.equals(p1)) {
            System.out.println(p1);
            out.writeUTF("execute");
            String sql="update users set password ='"+ p1+"' where tel='" + u_tel.getText() + "' and bank_ID='" + u_tid.getText() + "';";
            out.writeUTF(sql);
            now.setVisible(false);
            JFrame f_ru = new JFrame("重置成功");
            f_ru.setLayout(null);
            JLabel rmessage = new JLabel();
            rmessage.setText("您已成功重置密码，欢迎使用飞马银行系统！");
            rmessage.setVisible(true);
            exit_1 = new JButton("重新登陆");
            JPanel p = new JPanel();
            p.add(rmessage);
            p.add(exit_1);
            f_ru.add(p);
            p.setBounds(40, 40, 300, 100);
            exit_1.addActionListener(this);
            exit_1.setEnabled(true);
            f_ru.setBounds(600, 350, 400, 200);
            f_ru.setVisible(true);
            f_ru.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            now = f_ru;
         }
         else{
            JOptionPane.showMessageDialog(null, "两次密码输入不一致！\n请重新输入","警告",JOptionPane.ERROR_MESSAGE);
         }
      }
      catch(IOException ex){
         ex.printStackTrace();
      }

   }

    //修改信息实现 
   public void act_modify(String name,String pass,String tel,String gender,String birth){ 
      System.out.println("正在修改信息中...");
      try {
         out.writeUTF("execute");
         String sql="update users set name ='"+m_name.getText()+"' where bank_ID="+c.getBank_ID()+";";
         if(!name.equals(c.getName())){
            if(!c.setName(name)) JOptionPane.showMessageDialog(null, "修改的姓名不符合格式！","警告",0);
            else{
               sql="update users set name ='"+name+"' where bank_ID="+c.getBank_ID()+";";
               out.writeUTF(sql);
               JOptionPane.showMessageDialog(null, "姓名修改成功","提示",2);
            }
         }
         if(!pass.equals(c.getPassword())){
            if(!c.setPassword(pass)) JOptionPane.showMessageDialog(null, "修改的密码不符合格式！","警告",0);
            else{
               sql="update users set password ='"+pass+"' where bank_ID="+c.getBank_ID()+";";
               out.writeUTF(sql);
               JOptionPane.showMessageDialog(null, "密码修改成功","提示",2);
            }
         }
         if(!tel.equals(c.getTel())){
            if(!c.setTel(tel)) JOptionPane.showMessageDialog(null, "修改的电话号码不符合格式！","警告",0);
            else{
               sql="update users set tel ='"+tel+"' where bank_ID="+c.getBank_ID()+";";
               out.writeUTF(sql);
               JOptionPane.showMessageDialog(null, "电话号码修改成功","提示",2);
            }
         }
         if(!gender.equals(String.valueOf(c.getGender()))){
            sql="update users set gender ='"+gender+"' where bank_ID="+c.getBank_ID()+";";
            out.writeUTF(sql);
            JOptionPane.showMessageDialog(null, "性别修改成功","提示",2);
         }
         if(!birth.equals(c.getBirth())){
            if(!c.setBirth(birth)) JOptionPane.showMessageDialog(null, "修改的出生日期不符合格式！","警告",0);
            else{
               sql="update users set birth ='"+birth+"' where bank_ID="+c.getBank_ID()+";";
               out.writeUTF(sql);
               JOptionPane.showMessageDialog(null, "出生日期修改成功","提示",2);
            }
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

   public void act_transfer(){  //转账实现   
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
               f_1.setBounds(570,320,360,200);
               f_1.setVisible(true);
               f_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

               now=f_1;
            }
         }
         catch(IOException ex){
            ex.printStackTrace();
         }
      }
   }

   public void real_transfer(){     // 具体实现转账
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
      try{
         out.writeUTF("execute");
         String sql2="delete from users where bank_ID='"+c.getBank_ID()+"';";
         out.writeUTF(sql2);
         JOptionPane.showMessageDialog(null, "销户成功！\n飞马银行感谢您的多年陪伴！","提示",1); 
         System.exit(0);
      } 
      catch(IOException ex){
         JOptionPane.showMessageDialog(null, "系统出现异常，销户失败！","警告",JOptionPane.ERROR_MESSAGE); 
         ex.printStackTrace();
      }
   }

   public void excelExport(String path) {    // 实现筛选信息并写入 exel
      WritableWorkbook book = null;
      try {
         // 创建一个Excel文件对象
         book = Workbook.createWorkbook(new File(path));
         // 创建Excel第一个选项卡对象
         WritableSheet sheet = book.createSheet("第一页", 0);
         // 设置表头，第一行内容
         // Label参数说明：第一个是列，第二个是行，第三个是要写入的数据值，索引值都是从0开始
         jxl.write.Label label1 = new jxl.write.Label(0, 0, "Bank_ID");
         jxl.write.Label label2 = new jxl.write.Label(1, 0, "姓名");
         jxl.write.Label label3 = new jxl.write.Label(2, 0, "密码");
         jxl.write.Label label4 = new jxl.write.Label(3, 0, "Identify_ID");// 对应为第4列第1行的数据
         jxl.write.Label label5 = new jxl.write.Label(4, 0, "电话号码");
         jxl.write.Label label6 = new jxl.write.Label(5, 0, "性别");
         jxl.write.Label label7 = new jxl.write.Label(6, 0, "生日");
         jxl.write.Label label8 = new jxl.write.Label(7, 0, "存款金额");
         // 添加单元格到选项卡中
         sheet.addCell(label1);
         sheet.addCell(label2);
         sheet.addCell(label3);
         sheet.addCell(label4);
         sheet.addCell(label5);
         sheet.addCell(label6);
         sheet.addCell(label7);
         sheet.addCell(label8);
         System.out.println("完成首行");
         int num = 0;
         out.writeUTF("count");
         if(ed1.getText().equals("无设置") && ed3.getText().equals("无设置") && ed5.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+ "';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num);  System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+ "';");
         }
         else if(ed3.getText().equals("无设置") && ed5.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+ "'and bank_ID='" +ed1.getText()+ "';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num);  System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+ "'and bank_ID='" +ed1.getText()+ "';");
         }
         else if(ed1.getText().equals("无设置") && ed5.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+ "';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num);  System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+ "';");
         }
         else if(ed1.getText().equals("无设置") && ed3.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and name='"+ ed5.getText()+ "';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num); System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and name='"+ ed5.getText()+ "';");
         }
         else if(ed1.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+"'and name='"+ ed5.getText()+ "';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num);  System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+"'and name='"+ ed5.getText()+ "';");
         }
         else if(ed3.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and name='"+ ed5.getText()+ "'and bank_ID='" +ed1.getText()+"';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num); System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and name='"+ ed5.getText()+"'and bank_ID='" +ed1.getText()+ "';");
         }
         else if(ed5.getText().equals("无设置")) {
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+ "'and bank_ID='" +ed1.getText()+"';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num);  System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+ "'and bank_ID='" +ed1.getText()+"';");
         }
         else{
            out.writeUTF("select count(*) from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+ "'and bank_ID='" +ed1.getText()+"'and name='"+ ed5.getText()+"';");
            num=in.readInt();
            String s=String.format("%03d"+"query_m_r",num);  System.out.println("完成1,符合条件的客户数目为："+num+" 传输字符串"+s);
            out.writeUTF(s);
            out.writeUTF("select * from users where money between'" + Double.parseDouble(ed21.getText()) + "'and'" + Double.parseDouble(ed22.getText()) + "'and birth between'" +ed41.getText()+"'and'"+ed42.getText()+"'and gender='"+ ed3.getText()+ "'and bank_ID='" +ed1.getText()+"'and name='"+ ed5.getText()+"';");
         }
         System.out.println("完成2 成功筛选");
         for(int i=1;i<=num;i++){  // 按要求导出数据
            System.out.println("在写入第"+i+"行");
            sheet.addCell(new jxl.write.Label(0, i, in.readUTF()));
            sheet.addCell(new jxl.write.Label(1, i, in.readUTF()));
            sheet.addCell(new jxl.write.Label(2, i, "无权限查看"));in.readUTF();
            sheet.addCell(new jxl.write.Label(3, i, in.readUTF()));
            sheet.addCell(new jxl.write.Label(4, i, in.readUTF()));
            sheet.addCell(new jxl.write.Label(5, i, in.readUTF()));
            sheet.addCell(new jxl.write.Label(6, i, in.readUTF().toString()));
            sheet.addCell(new jxl.write.Label(7, i, in.readUTF().toString()));
         }
         JOptionPane.showMessageDialog(null, "符合上述条件的信息导出成功！","提示",2);
         // 写入数据到目标文件
         book.write();
      }catch (IOException e) {
         System.out.println("IOException: ");
         e.printStackTrace();
      }
      catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            // 关闭
            book.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }

   public void act_exportdata() {  //导出信息到excel
      {
         String path = ".//导出数据.xls";
         System.out.println("开始导出...");
         long s1 = new Date().getTime();
         // 开始导出
         excelExport(path);
         long s2 = new Date().getTime();
         long time = s2 - s1;
         System.out.println("导出完成！消耗时间：" + time + "毫秒");
      }
   }

   public void act_generatepdf(){  //生成pdf报表
     try {
         SimpleDateFormat f= new SimpleDateFormat("yyyy");
         String thisyear= f.format(new Date(System.currentTimeMillis()));
         BaseFont bfComic = BaseFont.createFont("c://windows//fonts//SIMHEI.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
         Font font = new Font(bfComic,14);
         Font tfont = new Font(bfComic,25);
         Document d=new Document();
         String filename=".\\报表.pdf";
         PdfWriter.getInstance(d, new FileOutputStream(filename));
         d.open();
         Paragraph title=new Paragraph(thisyear+"年飞马银行年度报表",tfont);
         title.setAlignment(Element.ALIGN_CENTER);
         d.add(title);
         d.add(new Paragraph(" "));
         out.writeUTF("count");
         out.writeUTF("select count(*) from users;");
         int num=in.readInt();
         d.add(new Paragraph("目前飞马银行中的账户总数："+(num-1),font));  //减去管理员
         d.add(new Paragraph("今年在飞马银行开户的新客户总数："+(num-5),font));
         out.writeUTF("query");
         out.writeUTF("select sum(money) from users;");
         d.add(new Paragraph("目前飞马银行总存储金额："+Double.parseDouble(in.readUTF()),font));
         d.close();
         JOptionPane.showMessageDialog(null, "报表生成成功！","提示",1);
     } catch (IOException e) {
         JOptionPane.showMessageDialog(null, "报表生成失败！请在终端查看问题。","警告",0);
         System.out.println("IOException: ");
         e.printStackTrace();
     } 
     catch (DocumentException e){
         JOptionPane.showMessageDialog(null, "报表生成失败！请在终端查看问题。","警告",0);
         System.out.println("DocumentException: ");
         e.printStackTrace();
     }
   }

   public void act_excelreadin(){  //根据excel文件批量开户  (excel文件必须符合一定格式，否则无法完成此功能)
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
               System.out.println("文件内容格式不符，无法完成批量导入功能！");
               JOptionPane.showMessageDialog(null, "文件内容格式不符，无法完成批量导入功能！","警告",0);
               //e.printStackTrace();
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
               System.out.println("文件内容格式不符，无法完成批量导入功能！");
               JOptionPane.showMessageDialog(null, "文件内容格式不符，无法完成批量导入功能！","警告",0);
               //System.out.println("IOException：");
               //e.printStackTrace();
           }
         }
         frame_switch_to_function_manager();
      }
   }


//--------------------------------------以下为界面转换实现(整体待优化)----------------------------//
 //代码相似度高，最好能修改后实现代码复用，减少方法的数量

  //从现在的页面跳转到登录页面
  public void frame_switch_to_login(){
    now.setVisible(false);
    frame_login();
 }

  //从现在的页面跳转到注册界面
  public void frame_switch_to_register(){  
     now.setVisible(false);
     frame_register();
  }

  //从现在的页面跳转到客户功能选择界面
  public void frame_switch_to_function(){  
      now.setVisible(false);
      frame_function();
  }
   
  //从现在的页面跳转到管理员功能选择界面
  public void frame_switch_to_function_manager(){  
      now.setVisible(false);
      frame_function_manager();
  }

  //转到存款界面
  public void frame_switch_to_putmoney(){  
      now.setVisible(false);
      frame_putmoney();
  }

   //转到取款界面
   public void frame_switch_to_takemoney(){
      now.setVisible(false);
      frame_takemoney();
   }

   //转到转账界面
   public void frame_switch_to_transfer(){
      now.setVisible(false);
      frame_transfer();
   }

   //转到信息修改界面
   public void frame_switch_to_modify(){
      now.setVisible(false);
      frame_modify();
   }

   //转到管理员excel文件批量开户界面
   public void frame_switch_to_excelreadin(){
      now.setVisible(false);
      frame_excelreadin();
   } 

   //转到管理员导出信息界面
   public void frame_switch_to_exportdata(){  
      now.setVisible(false);
      frame_export_data();
   }

   //转到用户找回密码界面
   public void frame_switch_to_finduser(){   
      now.setVisible(false);
      frame_find_user();
   }

  //从现在的页面跳转到查询页面
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
