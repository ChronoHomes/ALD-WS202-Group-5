package A05_Breitensuche;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class DemoApp {

    public static void main(String[] args) {

        HashSet<Integer> set = new LinkedHashSet<>();

        System.out.println(set.add(2));
        set.add(4);
        set.add(6);
        set.add(5);

        set.forEach(System.out::println);

        System.out.println("---");

        System.out.println(set.add(4));

        set.forEach(System.out::println);

        int test = set.stream().findFirst().get();
        System.out.println("test: " + test);

        set.remove(set.stream().findFirst().get());
        test = set.stream().findFirst().get();
        System.out.println("test: " + test);

    }
}
