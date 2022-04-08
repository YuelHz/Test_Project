package testclass;



public class Student extends Person{

    private Integer id;

    private String name;

    private Integer source;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", source=" + source +
                '}';
    }

    public Student() {}

    public Student(Integer id, String name, Integer source) {
        this.id = id;
        this.name = name;
        this.source = source;
    }
}
