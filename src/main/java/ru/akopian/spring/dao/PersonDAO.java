package ru.akopian.spring.dao;

import org.springframework.stereotype.Component;
import ru.akopian.spring.models.Person;

import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT,"Vlad" ));
        people.add(new Person(++PEOPLE_COUNT,"Artem" ));
        people.add(new Person(++PEOPLE_COUNT,"Tom" ));
    }
    public List<Person> index(){
        return people;
    }
    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }
    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

}
