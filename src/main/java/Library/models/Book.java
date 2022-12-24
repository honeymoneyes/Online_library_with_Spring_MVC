package Library.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty(message = "Book name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be greater 2 and 100 characters!")
    private String name;
    @Column(name = "author")
    @NotEmpty(message = "Author name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be greater 2 and 100 characters!")
    private String author;
    @Column(name = "year")
    private int year;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Transient
    private boolean checkDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {
    }

    public Book(int id, String name, String author, int year, Date date, Person owner) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.date = date;
        this.owner = owner;
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

    public Person getOwner() {
        return owner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCheckDate() {
        return checkDate;
    }

    public void setCheckDate(boolean checkDate) {
        this.checkDate = checkDate;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }


}
