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

    /* 
    public void act_login(String s_bankid,String s_password){  //登录实现
        System.out.println("正在登录...");
        if(!c.setBank_ID(s_bankid)){
           JOptionPane.showMessageDialog(null, "银行账号格式错误\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
        }
        else if(!c.setPassword(s_password)){
           JOptionPane.showMessageDialog(null, "密码格式错误，密码不得少于4位！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
        }
        else{
           try{
              out.writeUTF("query");
              String sql="select count(*) from users where bank_ID='"+s_bankid+"' and password='"+s_password+"';";
              out.writeUTF(sql);
           }
           catch(IOException ex){
              ex.printStackTrace();
           }
           try{
              int count=Integer.parseInt(in.readUTF());
              if(count!=0) {
                 if(!c.getBank_ID().equals("0000000000")){  //客户登录
                    System.out.println("客户"+c.getBank_ID()+"登录成功");
                    now.setVisible(false);
  
                    out.writeUTF("query_m");
                    out.writeUTF("select * from users where bank_ID='"+c.getBank_ID()+"';");  //后续改进后记得改成用bank_ID查找，因为名字可能重名，但是Bank_ID是唯一的
                    in.readUTF();
                    c.setName(in.readUTF());
                    in.readUTF();
                    c.setIdentify_ID(in.readUTF());
                    c.setTel(in.readUTF());
                    c.setGender(in.readUTF().charAt(0));
                    c.setBirth(in.readUTF());
                    c.setMoney(Double.parseDouble(in.readUTF()));
                    c.setXiaohu(true);
  
                    JFrame f_1=new JFrame("客户登录成功");
                    f_1.setLayout(null);
                    JLabel message=new JLabel();
                    message.setText("客户"+c.getBank_ID()+",您已成功登录，欢迎使用飞马银行系统！");
                    message.setVisible(true);
                    JPanel p=new JPanel();
                    p.add(message);
                    p.add(next_1);
                    f_1.add(p);
                    p.setBounds(0, 40, 300, 100);
                    next_1.addActionListener(this);
                    next_1.setEnabled(true);
                    f_1.setBounds(500,250,300,200);
                    f_1.setVisible(true);
                    f_1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
                    now=f_1;
                 }
                 else{  //管理员登录
                    System.out.println("管理员登录成功");
                    frame_switch_to_function_manager();
                 }
              }
              else{
                 JOptionPane.showMessageDialog(null, "不存在该用户或账号密码不一致！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE);
              }
           }
           catch(IOException ee){
              ee.printStackTrace();
           }
        }
     }
  
     public void act_register(){   //注册实现
        System.out.println("正在注册...");
        String sid = f1.getText();
        String sname = f2.getText();
        String spassword =new String(f3.getPassword());
          String sshenfen = f4.getText();
        String stel = f5.getText();
        String ssex = f6.getText();
        String sbirth = f7.getText();
          //String money = f8.getText();
        String LoginID =f1.getText();//查询是否有相同的用户名
        String sql1 = "select name from user where bank_ID='"+LoginID+"';";	//从数据库导出登录名
        if(LoginID.equals(sql1)) {   //有错，还没改
           JOptionPane.showMessageDialog(null, "注册失败！该用户已存在...","提示",JOptionPane.ERROR_MESSAGE);
        }
        else{
           try{
              out.writeUTF("execute");
              String sql="insert into users(bank_ID,name,password,identify_ID,tel,gender,birth,money)values('"+sid+"','"+sname+"','"+spassword+"','"+sshenfen+"','"+stel+"','"+ssex+"','"+sbirth+"');";
              out.writeUTF(sql);
              JOptionPane.showMessageDialog(null, "注册成功！","提示",JOptionPane.ERROR_MESSAGE); 
              if(!c.setBirth(sbirth)){
                 JOptionPane.showMessageDialog(null, "生日输入格式错误！","提示",JOptionPane.ERROR_MESSAGE); 
              }
              c.setGender(ssex.charAt(0));
              c.setMoney(2000.0);  //新客户送2000
              c.setPassword(spassword);
              c.setTel(stel);
              c.setIdentify_ID(sid);
              c.setName(sname);
           }catch(IOException ex){
                 ex.printStackTrace();
           }
        }
     }
  
     public void act_function_manager(){   //管理员的功能选择实现（待完成）
  
     }
  
     public void act_delete_manager(){   //管理员销户实现   //需要改
        System.out.println("正在销户...");
        // String sname=d1.getText(),        //改成bank_ID
        // spassword=new String(d2.getPassword());
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
           out.writeUTF("query_m");
           out.writeUTF("select * from users where name='"+c.getName()+"';");  //后续改进后记得改成用bank_ID查找，因为名字可能重名，但是Bank_ID是唯一的
           c.setBank_ID(in.readUTF());
           String name=in.readUTF();
           m_name.setText(name);
           String pass=in.readUTF();
           m_pass.setText(pass);
           in.readUTF();
           String tel=in.readUTF();
           m_tel.setText(tel);
           String gender=in.readUTF();
           m_gender.setText(gender);
           String birth=in.readUTF();
           m_birth.setText(birth);
  
           out.writeUTF("execute");
           String sql="update users set name ='"+m_name.getText()+"' where bank_ID='"+c.getBank_ID()+"';";
           if(!m_name.getText().equals(name)){
              sql="update users set name ='"+m_name.getText()+"' where bank_ID='"+c.getBank_ID()+"';";
              out.writeUTF(sql);
           }
           if(!m_pass.getText().equals(pass)){
              sql="update users set password ='"+m_pass.getText()+"' where bank_ID='"+c.getBank_ID()+"';";
              out.writeUTF(sql);
           }
           if(!m_tel.getText().equals(tel)){
              sql="update users set tel ='"+m_tel.getText()+"' where bank_ID='"+c.getBank_ID()+"';";
              out.writeUTF(sql);
           }
           if(!m_gender.getText().equals(gender)){
              sql="update users set gender ='"+m_gender.getText()+"' where bank_ID='"+c.getBank_ID()+"';";
              out.writeUTF(sql);
           }
           if(!m_birth.getText().equals(birth)){
              sql="update users set birth ='"+m_birth.getText()+"' where bank_ID='"+c.getBank_ID()+"';";
              out.writeUTF(sql);
           }
        } catch (IOException e) {
           e.printStackTrace();
        }
     }
  
     public void act_takemoney(){  //取款实现（待完成）  //取款考虑是否足够
        System.out.println("正在取款中...");
        System.out.println("此时的用户为： 用户名为"+c.getName()+",密码为"+c.getPassword()+"的用户"+c.getMoney()+Double.parseDouble(t_take.getText()));
        try{
           c.setMoney(c.getMoney()-Double.parseDouble(t_take.getText()));
           System.out.println("已完成取款.此时的用户余额为"+c.getMoney());
           out.writeUTF("execute");
           String sql="update users set money = "+c.getMoney()+" where name='"+c.getName()+";";
           out.writeUTF(sql);
           System.out.println("已完成写入数据库"+c.getMoney());
           now.setVisible(false);
           frame_inquery();
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
          String sql="update users set money = "+c.getMoney()+" where name='"+c.getName()+";";
          out.writeUTF(sql);
        }
        catch(IOException ex){
          ex.printStackTrace();
        }
     }
  
     public void act_transfer(){  //转账实现   //暂时没考虑转账的客户余额不足的问题
        System.out.println("正在转账中...");
        try{
           out.writeUTF("query");
           String sql="select cout(*) from users where 'bank_ID='"+t_bankid.getText()+"';";
           out.writeUTF(sql);
           
           int count=Integer.parseInt(in.readUTF());
           if(count==0) JOptionPane.showMessageDialog(null, "不存在该用户！\n请重新输入","提示",JOptionPane.ERROR_MESSAGE); 
           else{
              s.setBank_ID(t_bankid.getText());
              out.writeUTF("query");
              sql="select name from users where 'bank_ID='"+s.getBank_ID()+"';";
              out.writeUTF(sql);
              s.setName(in.readUTF());
  
              out.writeUTF("query");
              sql="select money from users where 'bank_ID='"+s.getBank_ID()+"';";
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
              //message.setVisible(true);
              JPanel p=new JPanel();
              p.add(vb);
              next_1.setText("取消");
              p.add(next_1);
              p.add(yes);
              f_1.add(p);
              p.setBounds(0, 40, 300, 100);
              yes.addActionListener(this);
              next_1.addActionListener(this);
              next_1.setEnabled(true);
              f_1.setBounds(100,100,300,200);
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
  
     public void real_transfer(){  
         try {
           Double money=Double.parseDouble(t_money.getText());
           s.setMoney(s.getMoney()+money);
           out.writeUTF("execute");
           String sql="update users set money = "+s.getMoney()+" where bank_ID='"+s.getBank_ID()+";";
           out.writeUTF(sql);
  
           c.setMoney(c.getMoney()-money);
           out.writeUTF("execute");
           sql="update users set money = "+c.getMoney()+" where name='"+c.getName()+";";  //这里最好用bank_ID查询（需优化）
           out.writeUTF(sql);
         } catch (IOException e) {
           e.printStackTrace();
         }
     }
   
     public void act_inquery_money(){   //查询实现
        System.out.println("正在查询中...");
           rs.setText("余额为:"+c.getMoney());
           System.out.println("余额为:"+c.getMoney());
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
  */

}
