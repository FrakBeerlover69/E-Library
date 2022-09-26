package library.repositories;

import library.models.Book;
import library.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Book> {

    Optional<Person> findById(int id);

    void deleteById(int id);
}