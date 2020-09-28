package fire.loginsingupfirebase;

public class User {
    public String fullName;
    public String email;
    public String age;
    public String mobileNumber;
    public String password;

    public User(String fullName, String email, String age, String mobileNumber, String password) {
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
