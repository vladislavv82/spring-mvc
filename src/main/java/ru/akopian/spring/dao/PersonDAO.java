package ru.akopian.spring.dao;

import org.springframework.stereotype.Component;
import ru.akopian.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/spring-app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("BD Error");
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement(); // содержит в себе запрос к базе дынных
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL); // строки которые вернулись с дб
            //executeQuery выполняет запрос и возвращает данные

            //переводим в Джава объект строки которые вернулись
            //итератор
            while (resultSet.next()) {
                Person person = new Person();

                //для каждой строки получаем значение и записываем её в объект
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                //внесенные изменения в объекте person запиываем в список
                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }


//    public Person show(int id) {
//        //     return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//    }

    public void save(Person person) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
                    "'," + person.getAge() + ",'" + person.getEmail() + "')";

            statement.executeUpdate(SQL);
            //executeUpdate обновляет и изменяет данные в базе
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
//    public void update(int id, Person updatedPerson) {
//        Person personToBeUpdated = show(id);
//
//        personToBeUpdated.setName(updatedPerson.getName());
//        personToBeUpdated.setAge(updatedPerson.getAge());
//        personToBeUpdated.setEmail(updatedPerson.getEmail());
//    }
//
//    public void delete(int id) {
//        people.removeIf(p -> p.getId() == id);
//    }
//}
