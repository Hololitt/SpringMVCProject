package de.nikon.springCourse.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @Min(value = 0, message = "Write your real age")
    private int age;
    @NotEmpty(message = "Name should be not empty!")
    @Size(min = 3, max = 15, message = "Your name has invalid character")
    private String name;
    @NotEmpty(message = "Write your email")
    @Email(message = "Email should be valid")
    private String email;

    public Person(String name, int id, int age, String email){
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
    }
    public Person(){}
    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

}
