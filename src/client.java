import java.util.*;

//需要对每一个属性的set进行约束
public class client {
    private String bank_ID;
    private String name;
    private String password;
    private String identify_ID;
    private String tel;
    private char gender;
    private Date birth;
    private Double money;

    public String getBank_ID() {
        return bank_ID;
    }
    public void setBank_ID(String bank_ID) {
        this.bank_ID = bank_ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentify_ID() {
        return identify_ID;
    }
    public void setIdentify_ID(String identify_ID) {
        this.identify_ID = identify_ID;
    }

    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
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
    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getMoney() {
        return money;
    }
    public void setMoney(Double money) {
        this.money = money;
    }
}
