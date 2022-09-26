package library.models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book implements Comparable<Book> {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Please enter a valid book title")
    @Size(min = 2, max = 50, message = "Book title can be 2 to 50 characters long")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Enter Author")
    @Size(min = 2, max = 50, message = "The author's name can be 2 to 50 characters long")
    @Column(name = "author")
    private String author;

    @Pattern(regexp = "\\d{1,4}|Unknown", message = "Please enter a valid book title year or Unknown")
    @Column(name = "creationDate")
    private String creationDate;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @Column(name = "expirationDate")
    private Date expirationDate;

    @Transient
    private boolean isExpired;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date date) {
        this.expirationDate = date;
    }

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author) && creationDate.equals(book.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, creationDate);
    }


    @Override
    public int compareTo(Book o) {

        if (this.getTitle().compareTo(o.getTitle()) == 0) {
            if (this.getAuthor().compareTo(o.getAuthor()) == 0) {
                return this.getCreationDate().compareTo(o.getCreationDate());
            } else
                return this.getAuthor().compareTo(o.getAuthor());
        } else
            return this.getTitle().compareTo(o.getTitle());
    }

    public void expiredCheck() {
        this.isExpired = this.expirationDate.getTime() < new Date().getTime();
    }

    public boolean isExpired() {
        return isExpired;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }
}
