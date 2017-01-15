package dto;

/**
 * Created by karol on 14/01/2017.
 */
public class Book {

    private Integer id;

    private String title;

    private Subject subject;

    private Author author;

    public Book(Integer id, String title, Subject subject, Author author) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Subject getSubject() {
        return subject;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject=" + subject +
                ", author=" + author +
                '}';
    }
}
