package testclass;


public class Teacher extends Person{

    private String name;

    private int salary;

    private String demo;

    public Teacher(String name, int salary, String demo) {
        this.name = name;
        this.salary = salary;
        this.demo = demo;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", demo='" + demo + '\'' +
                '}';
    }
}
