package Library.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "Name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be greater 2 and 100 characters!")
    @Column(name = "name")
    private String name;

    @Column(name = "year")
    @Min(value = 1920, message = "Year should be greater 1920")
    private int year;
    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {
    }

    public Person(int id, String name, int year, List<Book> books) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
