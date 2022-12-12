package Library.utils;

import Library.DAO.PersonDAO;
import Library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PeopleValidator implements Validator {

    private PersonDAO personDAO;

    @Autowired
    public PeopleValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personDAO.getPersonByFullName(person.getName()).isPresent()) {
            errors.rejectValue("name","","A person with that name already exists!");
        }
    }
}
