package com.exercise.lambda;

public class TestFuncInterface {
    public static void main(String[] args) {
        FuncInterface fobj = (int x) -> System.out.println(2*x);

        fobj.abstractFun(5);
    }
}
