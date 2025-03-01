package pkg_Person;


import java.io.Serializable;
import java.util.regex.Pattern;

abstract public class Person implements Serializable {
   protected String name;
   protected String emailId;
   protected String phoneNo;
   protected String address;
   protected String dob;

   public String getName(){
       return name;
   }
   public void setName(String name){
       // Regular Expression used;
       boolean isValidName = Pattern.matches("[a-zA-Z]+",(name));
       if (isValidName)
       this.name = name;
       else
           this.name = "default name";
   }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
       boolean isValidDob = Pattern.matches("\\d{2}-\\d{2}-\\d{4}",dob);
       if (isValidDob)
        this.dob = dob;
       else
           this.dob = "01-01-2006";
    }
    public Person(String name,String emailId,String phoneNo,String address,String dob){
       super();
       this.setName(name);
       this.emailId = emailId;
       this.phoneNo = phoneNo;
       this.address = address;
       this.setDob(dob);
    }
    public Person(){}

}
