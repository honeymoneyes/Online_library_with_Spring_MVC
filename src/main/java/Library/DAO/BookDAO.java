package Library.DAO;

import Library.models.Book;
import Library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?",
                        new BeanPropertyRowMapper<>(Book.class),
                        id)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?,?,?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void update(Book book, int id) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE id=?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }


    public void setBookPerson(Person person, int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?",
                person.getId(),
                book_id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM book" +
                                " JOIN person on book.person_id = person.id" +
                                " WHERE book.id=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        id)
                .stream()
                .findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE id=?",
                id);
    }
}