package tlukawski.atos.library;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import tlukawski.atos.library.book.Book;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.exception.AlreadyBorrowedException;
import tlukawski.atos.library.exception.AlreadyReturnedException;
import tlukawski.atos.library.exception.NotExistException;
import tlukawski.atos.library.generator.BookIdGenerator;
import tlukawski.atos.library.generator.BookIdGeneratorFactory;
import tlukawski.atos.library.query.BookQuery;
import tlukawski.atos.library.storage.BookStorage;
import tlukawski.atos.library.storage.BookStorageFactory;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtosLibrary implements Library{

  /**
   * Builder created to simplify testing and inject dependencies other than default.
   */
  @Builder.Default
  private BookStorage storage= BookStorageFactory.getBookStorage();
  @Builder.Default
  private BookIdGenerator generator =BookIdGeneratorFactory.getBookIdGenerator();


  public void addBookToLibrary(Book book) {
      storage.addToStorage(new LibraryBook(generator.generateUniqueId(), book));
  }

  @Override
  public LibraryBook findBook(String id) {
    return storage.findBookById(id);
  }

  @Override
  public List<LibraryBook> findBooks(BookQuery condition) {
    return storage.find(condition);
  }

  @Override
  public LibraryInformation getLibraryInformation() {
    List<LibraryBook> books = storage.findAll();
    return new LibraryInformation(books);
  }

  /**
   * Optimistic locking for operation without recurrency break condition can cause StackOverFlow error.
   * TODO: Add recurrency breaking condition.
   * TODO: Better if methods will return operation result information rather then throw checked exceptions.
   * @param id
   * @param borrower
   * @throws AlreadyBorrowedException
   * @throws NotExistException
   */
  @Override
  public void borrowBook(String id, String borrower) throws AlreadyBorrowedException, NotExistException {
    LibraryBook oldBook = storage.findBookById(id);
    checkNotBorrowed(oldBook);
    LibraryBook newBook = LibraryBook.newBorrowedBook(oldBook, borrower);
    if(!storage.updateInStorage(oldBook, newBook))
      borrowBook(id, borrower);
  }

  /**
   *
   * Optimistic locking for operation without recurency break condition can cause StackOverFlow error.
   * TODO: Add recurrency breaking condition.
   * TODO: Better if methods will return operation result information rather then throw checked exceptions.
   * @param id
   * @throws NotExistException
   * @throws AlreadyReturnedException
   */
  @Override
  public void returnBook(String id) throws NotExistException, AlreadyReturnedException {
    LibraryBook oldBook = storage.findBookById(id);
    checkBorrowed(oldBook);
    if(!storage.updateInStorage(oldBook, LibraryBook.newReturnedBook(oldBook)))
      returnBook(id);
  }


  /**
   *
   * Optimistic locking for operation without recurency break condition can cause StackOverFlow error.
   * TODO: Add recurrency breaking condition.
   * TODO: Better if methods will return operation result information rather then throw checked exceptions.
   * @param id
   * @throws NotExistException
   * @throws AlreadyBorrowedException
   */
  @Override
  public void removeBookFromLibrary(String id) throws NotExistException, AlreadyBorrowedException {
    LibraryBook book = storage.findBookById(id);
    checkNotBorrowed(book);
    if(!storage.removeFromStorage(book))
      removeBookFromLibrary(id);
  }

  private void checkNotBorrowed(LibraryBook book) throws NotExistException, AlreadyBorrowedException {
    if(book == null)
      throw new NotExistException();
    if(book.isBorrowed())
      throw new AlreadyBorrowedException();
  }

  private void checkBorrowed(LibraryBook book) throws NotExistException, AlreadyReturnedException {
    if(book == null)
      throw new NotExistException();
    if(!book.isBorrowed())
      throw new AlreadyReturnedException();
  }


}
