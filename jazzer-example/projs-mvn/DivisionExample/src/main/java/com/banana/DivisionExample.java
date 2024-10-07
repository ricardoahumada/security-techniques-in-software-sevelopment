package com.banana;

public class DivisionExample {
    public static void main(String[] args) {
        System.out.println("The division is "+divide(4,0));
    }
 
    public static int divide(int a, int b) {
        if (b == 0) { 
            throw new IllegalArgumentException("The b input value must be >0");
        }
        int result = a/b;
        return result;
    }
}