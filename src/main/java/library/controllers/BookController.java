package library.controllers;

import library.models.Book;
import library.models.Person;
import library.service.BookService;
import library.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String allBook(Model model, @RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                          @RequestParam(value = "sort_by_year", required = false) Boolean sortByYear) {

        model.addAttribute("books", bookService.getBookList(page, booksPerPage, sortByYear));

        if ((page != null && booksPerPage != null) || bookService.getAllBooks().size() > 6) {

            model.addAttribute("sort", bookService.getSortStatus(sortByYear));
            model.addAttribute("page", bookService.getPage(page));
            model.addAttribute("booksPerPage", bookService.getBooksPerPage());

            return "book/all";
        }

        return "book/all";
    }

    @GetMapping("/search")
    public String searchForm(@ModelAttribute("textToSearch") String text) {
        return "book/search";
    }

    @PostMapping("/search")
    public String resultList(@RequestParam("queue") String titleToSearch, Model model) {


        if (titleToSearch == null || titleToSearch.isBlank()) {
            model.addAttribute("books", new ArrayList<Book>());
            model.addAttribute("emptyInput", true);
            return "book/search";
        }

        model.addAttribute("books", bookService.findByTitleWhichStartWith(titleToSearch));
        model.addAttribute("queue", titleToSearch);
        model.addAttribute("emptyInput", false);

        return "book/search";
    }

    @GetMapping("/{id}")
    public String showBoobById(@ModelAttribute("newOwner") Person person, @PathVariable("id") int book_id, Model model) {
        model.addAttribute("book", bookService.getBookById(book_id).get());

        Optional<Person> owner = bookService.getOwner(book_id);

        if (owner.isPresent())
            model.addAttribute("owner", owner.get());
        else
            model.addAttribute("people", peopleService.getAllPeople());

        return "book/personalBookPage";
    }

    @GetMapping("/{id}/edit")
    public String updateBookForm(@PathVariable("id") int book_id, Model model) {
        model.addAttribute("book", bookService.getBookById(book_id).get());
        return "book/editForm";
    }

    @PatchMapping("/{id}/edit")
    public String updateBook(@PathVariable("id") int book_id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/editForm";

        bookService.updateBook(book_id, book);
        return "redirect:/book";
    }

    @GetMapping("/new")
    public String newBookForm(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String newBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/new";

        bookService.saveBook(book);
        System.out.println(book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int book_id) {
        bookService.deleteById(book_id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int book_id) {
        bookService.returnBook(book_id);
        return "redirect:/book/" + book_id;
    }

    @PatchMapping("/{id}")
    public String setNewOwner(@ModelAttribute("newOwner") Person person, @PathVariable("id") int book_id) {
        bookService.setNewOwner(book_id, person.getId());
        return "redirect:/book/" + book_id;
    }
}
