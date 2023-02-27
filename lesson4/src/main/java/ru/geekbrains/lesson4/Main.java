package ru.geekbrains.lesson4;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Employee> hashMap = new HashMap<>();
        Employee e1 = hashMap.put("abc", new Employee("Worker1", 30));
        Employee e2 = hashMap.put("abcd", new Employee("Worker2", 45));
        Employee e3 = hashMap.put("abc", new Employee("Worker44", 31));
        Employee e4 = hashMap.put("abcde", new Employee("Worker3", 33));

        Employee e5 = hashMap.remove("abc");
        Employee e6 = hashMap.remove("abc");
        Employee e7 = hashMap.get("abc");
        Employee e8 = hashMap.get("abcde");
    }
}

class Employee {

    String name;
    int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
