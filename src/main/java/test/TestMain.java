package test;


public class TestMain {
    public static void main(String[] args){

        String s = new String("a")+new String("b");
        String x = "ab";
        s.intern();
        System.out.println(s == x);

//        System.out.println(s == "ab");
//        String s2 = s.intern();
//        System.out.println(s == "ab");
//        System.out.println(s2 == "ab");

    }

}

