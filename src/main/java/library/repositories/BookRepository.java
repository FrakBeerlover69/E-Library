package library.repositories;

import library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByTitleContainingIgnoreCase(String text);

    List<Book> findByAuthorContainingIgnoreCase(String text);

}
