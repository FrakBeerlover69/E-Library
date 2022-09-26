package library.service;

import library.models.Book;
import library.models.Person;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface BookService {

    public List<Book> getAllBooks();

    public Optional<Book> getBookById(int id);

    public void updateBook(int id, Book book);

    public void saveBook(Book book);

    public void deleteById(int book_id);

    public Optional<Person> getOwner(int book_id);

    public void returnBook(int book_id);

    public void setNewOwner(int book_id, int person_id);

    public List<Book> getBookList(Integer page, Integer booksPerPage, Boolean sortByYear);

    public Integer getBooksPerPage();

    public boolean getSortStatus(Boolean sortByYear);

    public Integer getPage(Integer page);

    public List<Book> findByTitleWhichStartWith(String text);
}
