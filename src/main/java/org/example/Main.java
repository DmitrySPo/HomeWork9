package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Дмитрий", 25));
        people.add(new Person("Елена", 35));
        people.add(new Person("Сергей", 45));

        System.out.println("Исходная коллекция: " + people);

        Map<String, Person> agedPeople = Streams.of(people)
                .filter(person -> person.getAge() > 20)
                .transform(person -> new Person(person.getName(), person.getAge() + 30))
                .toMap(Person::getName, Function.identity());

        System.out.println("Мапа людей старше 50 лет: " + agedPeople);
    }
}