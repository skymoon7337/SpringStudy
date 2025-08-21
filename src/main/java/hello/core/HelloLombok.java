package hello.core;

import lombok.*;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sky");

        System.out.println("helloLombok = " + helloLombok);
    }
}
