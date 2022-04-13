package cn.jvm.test.test;

import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 1024*3; i++) {
            list.add(new Person());
        }
    }
}
class Person{
    private byte[] name = new byte[1024*1024];
}
