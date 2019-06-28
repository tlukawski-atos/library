package tlukawski.atos.library.storage;

import java.util.List;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.query.BookQuery;


/**
 * TODO: Future separation queries from storage manipulation.
 */
public interface BookStorage {

   void addToStorage(LibraryBook book);

  boolean updateInStorage(LibraryBook oldBook, LibraryBook newBook);

  LibraryBook findBookById(String id);
  <T> List<LibraryBook> find(BookQuery<T> condition);

  boolean removeFromStorage(LibraryBook book);

  List<LibraryBook> findAll();
}
