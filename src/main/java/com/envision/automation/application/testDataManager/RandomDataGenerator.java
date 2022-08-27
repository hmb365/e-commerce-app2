package com.envision.automation.application.testDataManager;

import com.github.javafaker.Faker;

public class RandomDataGenerator {


    public static String getUsername(){
        Faker faker = new Faker();
       return faker.name().username();
    }

    public static String getFirstName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }


    public static String getLastName(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }

    public static String getEmailAddress(){
        Faker faker = new Faker();
        return faker.internet().safeEmailAddress();
    }

    public static String getUniquePassword(){
        Faker faker = new Faker();
        return faker.internet().password();
    }


    public static void main(String[] args) {
        System.out.println(RandomDataGenerator.getUsername());
    }
}
