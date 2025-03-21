package ed.lab;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Comparator<Integer> comparator = Integer::compare;
        E03AVLTree<Integer> tree = new E03AVLTree<>(comparator);

        System.out.println("Size: " + tree.size());
        System.out.println("Height: " + tree.height());
        tree.insert(50);
        tree.insert(100);
        tree.insert(75);
        tree.insert(25);
        tree.insert(150);
        tree.insert(90);
        System.out.println("High: " + tree.height());
        tree.delete(75);
        System.out.println("Size: " + tree.size());
    }
}