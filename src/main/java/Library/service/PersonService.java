package Library.service;

import Library.models.Book;
import Library.models.Person;
import Library.repositories.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(int id) {
        Person person = null;
        if (personRepository.findById(id).isPresent()) {
            person = personRepository.findById(id).get();
        }
        return person;
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        personRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    public List<Book> getAllBooksPerson(int id) {
        Person person = null;
        if (personRepository.findById(id).isPresent()) {
            person = personRepository.findById(id).get();
        }
        Hibernate.initialize(person.getBooks());
        person.getBooks().forEach(book -> {
            long diffBetweenTime = Math.abs(book.getDate().getTime() - new Date().getTime());
            if (diffBetweenTime > 30000) {
                book.setCheckDate(true);
            }
        });
        return person.getBooks();
    }
    public Person findByFullName(String name) {
        return personRepository.findPersonByName(name).get();
    }
}
