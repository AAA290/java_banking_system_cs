import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;

//具体的客户类
//对每一个有要求的属性的set进行约束
public class client {
    private String bank_ID; 
    private String name;
    private String password;
    private String identify_ID;  
    private String tel;
    private char gender;
    private Date birth;
    private Double money;  
   
    // get&set 及判断内容是否合规
    public String getBank_ID() {
        return bank_ID;
    }
    public Boolean setBank_ID(String bank_ID) {  
        if(bank_ID.length()==10&&Pattern.matches ("[0-9]+",bank_ID)){
            this.bank_ID = bank_ID;
            return true;
        }
        else{
            return false;
        }
    }

    public String getName() {
        return name;
    }
    public boolean setName(String name) {  //不超过10个汉字字符
        if(name.length()>10){
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
    public boolean setIdentify_ID(String identify_ID) {   
        if(identify_ID.length()==12&&Pattern.matches ("[0-9]+",identify_ID)){
            this.identify_ID = identify_ID;
            return true;
        }
        else{
            return false;
        }
    }

    public String getTel() {
        return tel;
    }
    public boolean setTel(String tel) {   //电话格式（11位数字）
        if(Pattern.matches ("[0-9]+",tel)&&tel.length()==11){ 
            this.tel = tel;
            return true;
        }
        else {
            return false;   
        }
    }

    public char getGender() {
        return gender;
    }
    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBirth() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(birth);
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
    public Boolean setMoney(Double money) {
        if(money>=0){
           this.money = money;
           return true;
        }
        else return false;
    }
}
