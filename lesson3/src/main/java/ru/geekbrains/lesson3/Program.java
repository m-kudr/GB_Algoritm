package ru.geekbrains.lesson3;

import java.util.LinkedList;

public class Program {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList01 = new LinkedList<>();

        MyLinkedList<String> myLinkedList = new MyLinkedList<>();

//        myLinkedList.addLast("1");
//        myLinkedList.addLast("2");
//        myLinkedList.addLast("3");
//        myLinkedList.addLast("4");
//        myLinkedList.addLast("5");

        int[] keys = {1, 2, 3, 4, 5, 6}; // набор значений для списка

        for (int i = 0; i < keys.length; i++) {
            myLinkedList.addLast("" + keys[i]); // заполнение списка
        }

        printLinkedList(myLinkedList); // вывод в консоль списка
        reverseLinkedList(myLinkedList); // разворот списка
        printLinkedList(myLinkedList); // вывод в консоль списка

        Node middleNode = middleNode(myLinkedList.getHead());
    }

    public static void reverseLinkedList(MyLinkedList<String> myLinkedList) {
        Node previous = null;
        Node current = myLinkedList.getHead();
        while (current != null) {
            Node nextElement = current.getNext();
            current.setNext(previous);
            previous = current;
            current = nextElement;
        }
        myLinkedList.setHead(previous);
    }

    public static void printLinkedList(MyLinkedList<String> list) {
        Node current = list.getHead();
        while (current != null) {
            System.out.print(current.getValue() + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    public static Node middleNode(Node head) {
        int counter = 1;

        Node node = head;
        while (node.getNext() != null) {
            counter++;
            node = node.getNext();
        }

        counter = counter / 2 + 1;

        node = head;
        for (int i = 0; i < counter - 1; i++) {
            node = node.getNext();
        }

        return node;
    }

}
