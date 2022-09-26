package library.service.serviceImpl;

import library.models.Book;
import library.models.Person;
import library.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
public class PeopleServiceImpl implements library.service.PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleServiceImpl(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAllPeople() {
        return peopleRepository.findAll();
    }

    public Person getPersonById(int person_id) {
        return peopleRepository.findById(person_id).orElse(null);
    }

    @Transactional(readOnly = false)
    public void savePerson(Person person) {
        peopleRepository.save(person);
    }

    @Transactional(readOnly = false)
    public void updatePerson(int person_id, Person person) {
        person.setId(person_id);
        peopleRepository.save(person);
    }

    @Transactional(readOnly = false)
    public void deletePerson(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPerson(int person_id) {
        Optional<Person> person = peopleRepository.findById(person_id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        }

        return null;
    }


}
