import dto.Book;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by karol on 14/01/2017.
 */
public class BookDaoTest {

   @Test
   public void allBooks() throws SQLException {
      BookDao bookDao = new BookDao();
      final List<Book> books = bookDao.find(null, null, null);

      assertThat(books).hasSize(13);
   }

   @Test
   public void frankHerbertBooks() throws SQLException {
      BookDao bookDao = new BookDao();
      final List<Book> books = bookDao.find(null, "Herbert", null);

      assertThat(books).hasSize(1);
      final Book book = books.get(0);
      assertThat(book.getAuthor().getLastName()).isEqualTo("Herbert");
      assertThat(book.getTitle()).isEqualTo("Dune");
      assertThat(book.getSubject().getSubject()).isEqualTo("Science Fiction");
   }

   @Test
   public void complexSearch() throws SQLException {
      BookDao bookDao = new BookDao();
      final List<Book> books = bookDao.find("Learning Python", "Lutz", "Computers");

      assertThat(books).hasSize(1);
      final Book book = books.get(0);
      assertThat(book.getAuthor().getLastName()).isEqualTo("Lutz");
      assertThat(book.getTitle()).isEqualTo("Learning Python");
      assertThat(book.getSubject().getSubject()).isEqualTo("Computers");
   }


}



