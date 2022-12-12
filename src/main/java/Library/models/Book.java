package Library.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Book name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be greater 2 and 100 characters!")
    private String name;
    @NotEmpty(message = "Author name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be greater 2 and 100 characters!")
    private String author;
    private int year;
    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
