import java.util.ArrayList;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentSystem {
    public static ArrayList<User> userlist=new ArrayList<>();
    public static ArrayList<Student> list=new ArrayList<>();
    public static boolean in=false;
    public static void main(String[] args) {
      GUI();
    }
    public static void GUI()
    {
        while(true){
            Register();
            if(in)
            {
                GUI1();
            }
        }
    }
    public static void GUI1()
    {
        while(true)
        {
            Welcome();
        }
    }

    public static void Register()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to regist student System");
        System.out.println("1.用户登录");
        System.out.println("2.用户注册");
        System.out.println("3.忘记密码");
        System.out.println("4.退出系统");
        String choice=sc.next();
        switch(choice)
        {
            case "1"->UserLogin(userlist);
            case "2"->UserRegister(userlist);
            case "3"->Forgetpassword(userlist);
            case "4"->
            {
                System.out.println("欢迎下次再来");
                System.exit(0);

            }
            default -> System.out.println("Invalid choice");
        }
    }
    public static void UserLogin(ArrayList<User> userlist)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to login student System");
        System.out.println("please input username");
        String username=sc.next();
        if(!contains(userlist,username))
        {
            System.out.println("该用户没有注册，请检查用户名");
        }
        else {
            System.out.println("please input password");
            String password=sc.next();
            if(oklogin(userlist,username,password))
            {
                while(true)
                {
                    System.out.println("please input 验证码");
                    String yzm=yzm();
                    System.out.println("验证码是"+yzm);
                    System.out.println("please input yzm");
                    String word=sc.next();
                    if(word.equals(yzm))
                    {
                        System.out.println("Login Success");
                        in=true;
                        break;
                    }
                    else
                    {
                        System.out.println("验证码错误，请重新输入");
                    }
                }
            }
            else
            {
                System.out.println("Login Failed");
            }
        }

    }

    public static void UserRegister(ArrayList<User> userlist)
    {
        System.out.println("Welcome to UserResgiste");
        Scanner sc=new Scanner(System.in);
        User user=new User();
        while(true)
        {
            System.out.println("please input username");
            String username=sc.next();
            if(contains(userlist,username))
            {
                System.out.println("Username already exists");
                System.out.println("please input another username");
            }
            else if(check(username))
            {
                System.out.println("Username is incorrect in length");
                System.out.println("please input again");
            }else if(check1(username))
            {
                System.out.println("username is chao chu fanwei");
                System.out.println("please input again");
            }else if(check2(username))
            {
                user.setUsername(username);
                System.out.println("add successfully");
                break;
            }
            else {
                System.out.println("名字不是大小写字母和数字混合");
                System.out.println("请重新输入");
            }
        }
        System.out.println("please input password");
        String password=sc.next();
        user.setPassword(password);
        int i=0;
        boolean flag=false;
        while(true)
        {
            System.out.println("please ok your password");
            String ackpassword=sc.next();
            if(ok(user,ackpassword))
            {
                System.out.println("设置密码成功");
                flag=true;
                break;
            }
            else
            {
                System.out.println("请重新输入密码");
                System.out.println("你还有"+(2-i)+"次机会");
                if(i==2)
                {
                    System.out.println("账户已经被锁定，请联系管理人员");
                    System.out.println("或者重新进行注册");
                    break;
                }
                i++;
            }
        }
        boolean flag1=false;
        while(true)
        {
            if(flag)
            {
                System.out.println("please input your id");
                String id=sc.next();
                if(id.length()!=18)
                {
                    System.out.println("ID length is not enough");
                    System.out.println("please input again");
                }
                else if(id.charAt(0)=='0')
                {
                    System.out.println("Id is not valid");
                    System.out.println("please input again");
                }else if(checkid(id))
                {
                    user.setId(id);
                    System.out.println("add id successfully");
                    flag1=true;
                    break;
                }
                else
                {
                    System.out.println("id is not valid");
                    System.out.println("please input again");
                }
            }
        }
        while(true)
        {
            if(flag1)
            {
                System.out.println("please input your phone number");
                String phonenumber=sc.next();
                if(phonenumber.length()!=11)
                {
                    System.out.println("phone number length is not valid");
                    System.out.println("please input again");
                }
                else if(phonenumber.charAt(0)=='0')
                {
                    System.out.println("phone number is not valid");
                    System.out.println("please input again");
                }else if(checkpn(phonenumber))
                {
                    user.setPhonenumber(phonenumber);
                    System.out.println("add successfully");
                    break;
                }
                else
                {
                    System.out.println("phone number is not valid");
                    System.out.println("please input again");
                }
            }
        }
        userlist.add(user);
    }
    public static void Forgetpassword(ArrayList<User> userlist)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入用户名");
        String username=sc.next();
        User u=getuser(userlist,username);
        while(true)
        {
            System.out.println("请输入原密码");
            String password=sc.next();
            if(u.getPassword().equals(password))
            {
                System.out.println("请输入新的密码");
                String newpassword=sc.next();
                u.setPassword(newpassword);
                System.out.println("设置新的密码成功");
                break;
            }
            else
            {
                System.out.println("旧密码不正确，请重新输入");
            }
        }
    }
    public static boolean contains(ArrayList<User> userlist,String username)
    {
        for (int i = 0; i < userlist.size(); i++) {
            if(userlist.get(i).getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean check(String username)
    {
        return username.length()<3||username.length()>15;
    }
    public static boolean check1(String username)
    {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
        Matcher matcher = pattern.matcher(username);
        if (matcher.find()) {
           return true;
        } else {
           return false;
        }
    }

    public static boolean check2(String username)
    {
        int cout1=0;
        int cout=0;
        for (int i = 0; i < username.length(); i++) {
            if(username.charAt(i)>='0'&&username.charAt(i)<='9')
            {
                cout++;
            }
            else if(username.charAt(i)>='a'&&username.charAt(i)<='z'
                    ||username.charAt(i)>='A'&&username.charAt(i)<='Z')
                cout1++;
        }
       if(cout>0&&cout1>0)
       {
           return true;
       }
       return false;
    }

    public static boolean ok(User user,String ackpassword)
    {
        if(user.getPassword().equals(ackpassword))
        {
            return true;
        }
        return false;
    }
    public static boolean checkid(String id)
    {
        int cout=0;
        for (int i = 0; i < id.length()-1; i++) {
            if(id.charAt(i)>='0'&&id.charAt(i)<='9')
            {
                cout++;
            }
        }
        if(id.charAt(id.length()-1)=='x'||id.charAt(id.length()-1)=='X'
        ||id.charAt(id.length()-1)>='0'&&id.charAt(id.length()-1)<='9')
        {
            cout++;
        }
        if(cout==18)
        {
            return true;
        }
        return false;
    }
    public static boolean checkpn(String phonenumber)
    {
        int cout=0;
        for (int i = 0; i < phonenumber.length(); i++) {
            if(phonenumber.charAt(i)>='0'&&phonenumber.charAt(i)<='9')
            {
                cout++;
            }
        }
        if(cout==11)
        {
            return true;
        }
        return false;
    }
    public static boolean oklogin(ArrayList<User> userlist,String username,String password)
    {
        for (int i = 0; i < userlist.size(); i++) {
            if(userlist.get(i).getUsername().equals(username)&&userlist.get(i).getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
    public static String yzm()
    {
        char[] a=new char[52];
        for (int i = 0; i < a.length; i++) {
            if(i<26)
            {
                a[i]=(char)('a'+i);
            }
            else
            {
                a[i]=(char)('A'+i-26);
            }
        }
        Random r=new Random();
        char[]b=new char[5];
        for(int i=0;i<4;i++)
        {
            int index=r.nextInt(52);
            b[i]=a[index];
        }
        int j=r.nextInt(10);
        b[b.length-1]=(char) ('0'+j);
        char c=b[b.length-1];
        int k=r.nextInt(4);
        b[b.length-1]=b[k];
        b[k]=c;
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(b[i]);
        }
        return sb.toString();
    }

    public static User getuser(ArrayList<User> userlist,String username)
    {
        for (int i = 0; i < userlist.size(); i++) {
            if(userlist.get(i).getUsername().equals(username))
            {
                return userlist.get(i);
            }
        }
        return null;
    }



    public static void Welcome()
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to Student System");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Query Student");
        System.out.println("4. Regist Student");
        System.out.println("5. Exit System");
        System.out.println("6.回到开始界面");
        System.out.println("Enter your choice");
        String choose=sc.next();
        switch(choose)
        {
            case "1"->AddStudent(list);
            case "2"->DeleteStudent(list);
            case "3"->QueryStudent(list);
            case "4"->RegistStudent(list);
            case "5"->System.exit(0);
            case "6"->GUI();
            default -> System.out.println("Invalid choice");
        }
    }

    private static void RegistStudent(ArrayList<Student> list) {
        Scanner sc=new Scanner(System.in);
        System.out.println("please input student id");
        String id=sc.next();
        if(!contain(list,id))
        {
            System.out.println("Student id not exist");
        }
        System.out.println("please input student name");
        String newname=sc.next();
        list.get(getIndex(list,id)).setName(newname);
        System.out.println("please input student age");
        int newage=sc.nextInt();
        list.get(getIndex(list,id)).setAge(newage);
        System.out.println("please input student address");
        String newaddress=sc.next();
        list.get(getIndex(list,id)).setAddress(newaddress);
    }

    private static void QueryStudent(ArrayList<Student> list) {
        if(list.size()==0)
        {
            System.out.println("No students found");
            return ;
        }
        System.out.println("id\t\tname\tage\taddress");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }

    public static void AddStudent(ArrayList<Student> list)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("please input student info");
        Student stu=new Student();
       while(true)
        {
            System.out.println("please input student id");
            String id=sc.next();
            if(contain(list,id))
            {
                System.out.println("Student id already exist");
            }else
            {
                stu.setId(id);
                break;
            }
        }
        System.out.println("please input student name");
        String name=sc.next();
        stu.setName(name);
        System.out.println("please input student age");
        int age=sc.nextInt();
        stu.setAge(age);
        System.out.println("please input student address");
        String address=sc.next();
        stu.setAddress(address);
        list.add(stu);
        System.out.println("add successfully");
    }
    public static void DeleteStudent(ArrayList<Student> list)
    {
        if(list.size()==0)
        {
            System.out.println("No students found");
            return ;
        }
        Scanner sc=new Scanner(System.in);
        System.out.println("please input student id");
        String id=sc.next();
        if(getIndex(list,id)==-1)
        {
           System.out.println("student not found");
        }
        list.remove(getIndex(list,id));
        System.out.println("delete successfully");
    }
    public static boolean contain(ArrayList<Student> list, String id) {
        /*for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))
            {
                return true;
            }
        }
        return false;*/
        return getIndex(list,id)!=-1;
    }
    public static int getIndex(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equals(id))
            {
                return i;
            }
        }
        return -1;
    }
}
