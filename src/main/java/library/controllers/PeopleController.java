package library.controllers;

import library.models.Book;
import library.models.Person;
import library.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String showAll(Model model) throws SQLException {
        model.addAttribute("people", peopleService.getAllPeople());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String showPersonById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.getPersonById(id));
        List<Book> bookList = peopleService.getBooksByPerson(id);

        if (!bookList.isEmpty()) {
            for (Book book : bookList) {
                book.expiredCheck();
            }
        }

        model.addAttribute("books", bookList);
        return "people/personal";
    }

    @GetMapping("new")
    public String newPersonForm(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.savePerson(person);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getEditForm(@PathVariable("id") int person_id, Model model) {
        model.addAttribute("person", peopleService.getPersonById(person_id));
        return "people/editForm";
    }

    @PatchMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int person_id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "people/editForm";

        peopleService.updatePerson(person_id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int person_id) {
        peopleService.deletePerson(person_id);
        return "redirect:/people";
    }
}
