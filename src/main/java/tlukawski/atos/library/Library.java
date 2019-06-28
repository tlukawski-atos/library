package tlukawski.atos.library;

import java.util.List;
import tlukawski.atos.library.book.Book;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.exception.AlreadyBorrowedException;
import tlukawski.atos.library.exception.AlreadyReturnedException;
import tlukawski.atos.library.exception.NotExistException;
import tlukawski.atos.library.query.BookQuery;

public interface Library {

  void addBookToLibrary(Book book);

  LibraryBook findBook(String id);

  List<LibraryBook> findBooks(BookQuery condition);

  LibraryInformation getLibraryInformation();

  void borrowBook(String id, String boorower) throws AlreadyBorrowedException, NotExistException;

  void returnBook(String id) throws NotExistException, AlreadyReturnedException;

  void removeBookFromLibrary(String id) throws NotExistException, AlreadyBorrowedException;
}
