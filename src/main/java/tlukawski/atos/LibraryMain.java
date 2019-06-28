package tlukawski.atos;

import java.util.List;
import tlukawski.atos.library.exception.AlreadyBorrowedException;
import tlukawski.atos.library.AtosLibrary;
import tlukawski.atos.library.book.Book;
import tlukawski.atos.library.query.BookQuery;
import tlukawski.atos.library.query.BookQueryBuilder;
import tlukawski.atos.library.Library;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.exception.NotExistException;

public class LibraryMain {

  public static void main(String[] args)
  {
    Library library = new AtosLibrary();
    library.addBookToLibrary(new Book("Martwe dusze", "Gogol", 1988));
    library.addBookToLibrary(new Book("test", "Gogol", 1988));
    library.addBookToLibrary(new Book("test", "test", 2000));
    library.addBookToLibrary(new Book("Martwe dusze", "test", 1988));
    library.addBookToLibrary(new Book("Martwe dusze", "test2", 1978));
    BookQuery what = BookQueryBuilder.findBy().title("Martwe dusze");
    List<LibraryBook> cos = library.findBooks(what);
    cos.forEach(b-> {
      try {
        library.borrowBook(b.getId(), "tlu");
        //library.returnBook(b.getId());
      } catch (NotExistException e) {
        e.printStackTrace();
      } catch (AlreadyBorrowedException e) {
        e.printStackTrace();
      }
    });

    List<LibraryBook> books2 = library
        .findBooks(
            BookQueryBuilder.findBy().title("Martwe dusze").and().author("test2").and().year(1978).and().borrowed(true));
    books2.forEach(p->System.err.println(p));
    System.err.println("---------------");
    System.err.println(library.getLibraryInformation());
  }
}
