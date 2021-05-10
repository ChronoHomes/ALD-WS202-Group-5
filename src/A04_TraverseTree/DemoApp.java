package A04_TraverseTree;

import java.util.ArrayList;

public class DemoApp {

    public static void main(String[] args) {



        Wörterbuch wb = new Wörterbuch();

        wb.add("Homer");
        wb.add("Flanders");
        wb.add("Maggie");
        wb.add("Marge");
        wb.add("Lisa");
        wb.add("Burns");
        wb.add("Crusty");
        wb.add("Bart");
        wb.add("Manjula");
        wb.add("Marty");


        ArrayList<Wort> list = wb.createList(wb.getRoot());
        for (Wort wort : list) {

        }


    }
}
