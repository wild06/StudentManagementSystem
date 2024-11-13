public class Student {
    private String name;
    private String id;
    private int age;
    private String address;
    public Student(String name, String id, int age, String address) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.address = address;
    }
    public Student(){}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String toString ()
    {
        return this.id+"\t"+this.name+"\t\t"+this.age+"\t\t"+this.address;
    }

}
