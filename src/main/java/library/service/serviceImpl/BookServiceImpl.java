package library.service.serviceImpl;

import library.models.Book;
import library.models.Person;
import library.repositories.BookRepository;
import library.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
@Transactional(readOnly = true)
public class BookServiceImpl implements library.service.BookService {
    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public void updateBook(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional(readOnly = false)
    public void saveBook(Book book) {

        bookRepository.save(book);
    }

    @Transactional(readOnly = false)

    public void deleteById(int book_id) {
        bookRepository.deleteById(book_id);
    }

    public Optional<Person> getOwner(int book_id) {
        Book book = bookRepository.findById(book_id).get();
        return Optional.ofNullable(book.getOwner());
    }

    @Transactional(readOnly = false)
    public void returnBook(int book_id) {
        Book book = bookRepository.findById(book_id).get();
        book.setExpirationDate(null);
        book.setOwner(null);
    }

    @Transactional(readOnly = false)
    public void setNewOwner(int book_id, int person_id) {
        Book book = bookRepository.findById(book_id).get();

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 10);
        book.setExpirationDate(c.getTime());

        book.setOwner(peopleRepository.findById(person_id).get());
    }

    private List<Book> getAllByPage(int page, int books_per_page) {
        return bookRepository.findAll(PageRequest.of(page, books_per_page)).getContent();
    }

    private List<Book> getAllByPageAndSortByYear(int page, int books_per_page) {
        return bookRepository.findAll(PageRequest.of(page, books_per_page, Sort.by("creationDate"))).getContent();
    }

    public List<Book> findByTitleWhichStartWith(String text) {

        Set<Book> bookSet = new HashSet<>(bookRepository.findByTitleContainingIgnoreCase(text));
        bookSet.addAll(bookRepository.findByAuthorContainingIgnoreCase(text));

        return new ArrayList<>(bookSet);
    }

    public Integer getPage(Integer page) {
        if (page != null)
            return page;
        else
            return 0;
    }

    private List<Book> getPagableBookList(Boolean sortByYear, Integer page, Integer booksPerPage) {
        Integer pageToModel = getPage(page);
        Integer booksPerPageToModel = 6;

        if (sortByYear == null || !sortByYear) {
            return getAllByPage(pageToModel, booksPerPageToModel);
        } else {
            return getAllByPageAndSortByYear(pageToModel, booksPerPageToModel);
        }
    }

    public boolean getSortStatus(Boolean sortByYear) {
        if (sortByYear == null || !sortByYear)
            return false;
        else
            return true;
    }

    public Integer getBooksPerPage() {
        return 6;
    }

    public List<Book> getBookList(Integer page, Integer booksPerPage, Boolean sortByYear) {

        if ((page != null && booksPerPage != null) || getAllBooks().size() > 6) {

            return getPagableBookList(sortByYear, page, booksPerPage);

        }

        return getAllBooks();
    }
}
