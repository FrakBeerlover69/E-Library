package library.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @Pattern(regexp = "([A-Z][a-z]+ [A-Z][a-z]+)|([A-Z][a-z]+ [A-Z][a-z]+ [A-Z][a-z]+)",
            message = "Please enter a name in the format: Surname Name Patronymic \n (Patronymic is optional)")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    @Column(name = "fullName")
    private String fullName;

    @Min(value = 1900, message = "Minimum year of birth - 1900")
    @NotNull(message = "Enter year of birth")
    @Column(name = "yearOfBirth")
    private Integer yearOfBirth;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
