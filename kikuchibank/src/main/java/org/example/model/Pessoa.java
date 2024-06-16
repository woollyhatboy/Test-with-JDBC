package org.example.model;


public class Pessoa {
    private String name;
    private String cpf;
    private double balance;
    private int age;

    public Pessoa(String name, String cpf, int age) {
        this.name = name;
        this.age = age;
        this.cpf = cpf;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    @Override
    public String toString() {
        return "Client: " + name +
                " age: " + age +
                " CPF: " + cpf +
                " balance: " + balance;
    }
}