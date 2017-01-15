package dto;

/**
 * Created by karol on 14/01/2017.
 */
public class Subject {

    private Integer id;

    private String subject;

    public Subject(Integer id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }
}
