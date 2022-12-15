import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class graphic extends JFrame implements ActionListener{
   JButton inquery;
   JTextField user,password;
   JTextArea rs;
   Socket socket; 
   DataInputStream in;
   DataOutputStream out;

   public graphic(){
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
      //-----------------------------------以上为与远程服务器相连接--------------------------//
      inquery=new JButton("查询账户余额");
      user=new JTextField("name",12);
      password=new JTextField("password",12);
      Box  boxV1=Box.createVerticalBox();
      boxV1.add(new JLabel("用户名"));
      boxV1.add(new JLabel("密码"));
      rs=new JTextArea(2,12);
      Box boxV2=Box.createVerticalBox();
      boxV2.add(user);
      boxV2.add(password);
      Box baseBox=Box.createHorizontalBox();
      baseBox.add(boxV1);
      baseBox.add(boxV2);
      Container con=getContentPane();
      con.setLayout(new FlowLayout());
      con.add(inquery);
      con.add(baseBox);
      con.add(new JScrollPane(rs));
      inquery.addActionListener(this);
      setBounds(100,100,360,300);
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void actionPerformed(ActionEvent e){
      System.out.println("正在查询中...");
      String s_user=user.getText(),
             s_password=password.getText();
      System.out.println("此时的用户为： 用户名为"+s_user+",密码为"+s_password+"的用户");
      try{
       out.writeUTF(s_user);
       out.writeUTF(s_password);
      }
      catch(IOException ex){
        ex.printStackTrace();
      }
      inquery_money();
   }
 
   public void inquery_money(){
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

   public static void main(String[] args){
      graphic g=new graphic();
   }
}
