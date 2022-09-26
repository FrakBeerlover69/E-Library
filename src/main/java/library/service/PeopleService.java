package library.service;

import library.models.Book;
import library.models.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PeopleService {

    public List<Person> getAllPeople();

    public Person getPersonById(int person_id);

    public void savePerson(Person person);

    public void updatePerson(int person_id, Person person);

    public void deletePerson(int id);

    public List<Book> getBooksByPerson(int person_id);
}
