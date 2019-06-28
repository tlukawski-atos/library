package tlukawski.atos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import tlukawski.atos.library.AtosLibrary;
import tlukawski.atos.library.book.Book;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.exception.AlreadyBorrowedException;
import tlukawski.atos.library.exception.NotExistException;
import tlukawski.atos.library.storage.BookStorage;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryStorageTest {

  @InjectMocks
  AtosLibrary library;

  @Mock
  private BookStorage storageMock;

  LibraryBook baseBook = new LibraryBook("1", new Book("Martwe dusze", "Gogol", 1988));

  @Test
  public void borrowBookStackOverFlowTest() throws AlreadyBorrowedException, NotExistException {
    when(storageMock.findBookById(anyString()))
        .thenReturn(baseBook);
    when(storageMock.updateInStorage(any(LibraryBook.class), any(LibraryBook.class))).thenReturn(true);
    library.borrowBook("1", "tlu");
    verify(storageMock, atMostOnce()).updateInStorage(any(LibraryBook.class), any(LibraryBook.class));
  }

  @Test(expected=AlreadyBorrowedException.class)
  public void borrowBookAlreadyBorrowedTest() throws AlreadyBorrowedException, NotExistException {
    when(storageMock.findBookById(anyString()))
        .thenReturn(LibraryBook.newBorrowedBook(baseBook, "tlu"));
    library.borrowBook("1", "tlu");
  }

  @Test(expected=NotExistException.class)
  public void borrowBookNotFoundTest() throws AlreadyBorrowedException, NotExistException {
    when(storageMock.findBookById(anyString()))
        .thenReturn(null);
    library.borrowBook("1", "tlu");
  }
}
