package de.nikon.springCourse.dao;

import de.nikon.springCourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
private static final String USERNAME = "postgres";
private static final String PASSWORD = "postgres";
private static Connection connection;
static{
    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try{

            Statement statement = connection.createStatement();
            String sqlCode = "SELECT * FROM public.Person";
            ResultSet resultSet = statement.executeQuery(sqlCode);

            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return people;
    }
   public Person show(int id) {
    Person person = null;
   try{
       PreparedStatement preparedStatement =
               connection.prepareStatement("select * from public.Person where id=?");
       preparedStatement.setInt(1, id);
       ResultSet resultSet = preparedStatement.executeQuery();

       resultSet.next();

       person = new Person();

       person.setId(resultSet.getInt("id"));
       person.setAge(resultSet.getInt("age"));
       person.setEmail(resultSet.getString("email"));
   }catch(SQLException e){
       e.printStackTrace();
   }

return person;
   }
public void save(Person person) {
try{
    PreparedStatement preparedStatement = connection.prepareStatement(
            "insert into public.Person values (3, ?, ?, ?)");
    preparedStatement.setInt(1, person.getAge());
    preparedStatement.setString(2, person.getEmail());

    preparedStatement.executeUpdate();
}catch(SQLException e){
    e.printStackTrace();
}
}
public void update(int id, Person updatedPerson) {
    try{
        PreparedStatement preparedStatement =
                connection.prepareStatement("update Person set age=?, email where id=?");

        preparedStatement.setInt(1, updatedPerson.getAge());
        preparedStatement.setString(2, updatedPerson.getEmail());
        preparedStatement.setInt(3, id);

        preparedStatement.executeUpdate();
    }catch(SQLException e){
        e.printStackTrace();
    }
}
public void delete(int id){
    try{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from public.Person where id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }catch (SQLException e){
        e.printStackTrace();
    }
}
}
