package com.naveen.training.FunctionalProgramming;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.*;

import javax.crypto.interfaces.PBEKey;

interface Example {
    void demo();
}

class EmployeeDetails {
    int empId;
    String empName;
    int empAge;
    int empSalary;

    public EmployeeDetails(int empId, String empName, int empAge, int empSalary) {
        this.empId = empId;
        this.empName = empName;
        this.empAge = empAge;
        this.empSalary = empSalary;
    }
}

class ReduceExample {
    List<Integer> list = Arrays.asList(2, 5, 6, 19, 20, 25, 61, 35, 47, 21);

    public void factorial(int n) {

        list.stream().map(i -> LongStream.rangeClosed(1, i).reduce(1, (a, b) -> a * b)).forEach(System.out::println);

        System.out.println(IntStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b));
    }

    public void sumOfList() {
        System.out.println(list.stream().reduce(0, (a, b) -> a + b));
    }

    public void sumOfSquares() {
        System.out.println(list.stream().map(i -> i * i).reduce(0, (a, b) -> a + b));
    }
}

public class FunctionalInterfaceExample {

    public static void demo() {
        System.out.println("\nThis is an example of functional interface\n");
    }

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> adder = (i, j) -> i + j;
        adder = adder.andThen(i -> i * 10);
        System.out.println("\n" + adder.apply(2, 3));

        Example example = FunctionalInterfaceExample::demo;
        example.demo();

        List<EmployeeDetails> list = new ArrayList<>();
        list.add(new EmployeeDetails(1, "Potter", 49, 70000));
        list.add(new EmployeeDetails(2, "Gustin", 30, 40000));
        list.add(new EmployeeDetails(3, "Tom", 22, 10000));
        list.add(new EmployeeDetails(4, "Dick", 25, 15000));
        list.add(new EmployeeDetails(5, "Harry", 32, 45000));

        System.out.println("\n--------------------------\n");

        list.stream().filter(p -> p.empSalary > 30000).map(p -> p.empSalary).limit(2).collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("\n--------------------------\n");

        Stream.iterate(5, element -> element + 5).limit(10).forEach(System.out::println);

        System.out.println("\n--------------------------\n");

        ReduceExample reduce = new ReduceExample();
        reduce.factorial(5);

        System.out.println("\n--------------------------\n");

        reduce.sumOfList();

        System.out.println("\n--------------------------\n");

        reduce.sumOfSquares();

        System.out.println("\n--------------------------\n");

    }
}
