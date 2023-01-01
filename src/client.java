import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;

//需要对每一个属性的set进行约束
public class client {
    private String bank_ID;  //不能更改 
    private String name;
    private String password;
    private String identify_ID;  //不能更改 
    private String tel;
    private char gender;
    private Date birth;
    private Double money;  
    private Boolean xiaohu;   //false为要销户

    public Boolean getXiaohu() {
        return xiaohu;
    }
    public void setXiaohu(Boolean xiaohu) {
        this.xiaohu = xiaohu;
    }

    public String getBank_ID() {
        return bank_ID;
    }
    public Boolean setBank_ID(String bank_ID) {  
        if(bank_ID.length()==10&&Pattern.matches ("[0-9]+",bank_ID)){
            this.bank_ID = bank_ID;
            return true;
        }
        else{
            this.bank_ID = " ";
            return false;
        }
    }

    public String getName() {
        return name;
    }
    public boolean setName(String name) {  //不超过10个汉字字符
        if(name.length()>10){
            this.name="name";
            return false;
        }
        else{
            this.name = name;
            return true;
        }
    }

    public String getPassword() {
        return password;
    }
    public boolean setPassword(String password) {    //不少于四位
        if(password.length()<4){
            this.password="password";
            return false;
        }
        else{
            this.password = password;
            return true;
        }
    }

    public String getIdentify_ID() { 
        return identify_ID;
    }
    public void setIdentify_ID(String identify_ID) {  //不能更改 
        this.identify_ID = identify_ID;
    }

    public String getTel() {
        return tel;
    }
    public boolean setTel(String tel) {   //电话格式
        if(Pattern.matches ("[0-9]+",tel)&&tel.length()==11){ 
            this.tel = tel;
            return true;
        }
        else {
            this.tel = "11234567890";
            return false;   
        }
    }

    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }
    public Boolean setBirth(String sbirth) {
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        try {
            Date birth=sdf.parse(sbirth);
            this.birth = birth;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {this.money = money;}
}
