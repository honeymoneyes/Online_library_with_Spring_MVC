package Library.DAO;

import Library.models.Book;
import Library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, year) VALUES (?,?)",
                person.getName(),
                person.getYear());
    }

    public void update(Person person, int id) {
        jdbcTemplate.update("UPDATE person SET name=?, year=? WHERE id=?",
                person.getName(),
                person.getYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public Optional<Person> getPersonByFullName(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?",
                new BeanPropertyRowMapper<>(Person.class),
                name).stream().findAny() ;
    }
    public List<Book> getAllBooksPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }
}
