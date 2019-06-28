package tlukawski.atos.library.book;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class LibraryBook {

  @NonNull
  private final String id;
  @NonNull
  private final Book book;
  private LentInformation lent;

  public boolean isBorrowed() {
    return lent != null;
  }

  private LibraryBook(LibraryBook book, String borrower) {
    this.book = book.getBook();
    this.id = book.getId();
    lent = new LentInformation(borrower);
  }

  private LibraryBook(LibraryBook book) {
    this.book = book.getBook();
    this.id = book.getId();
    lent = null;
  }

  public static LibraryBook newBorrowedBook(LibraryBook book, String borrower) {
    return new LibraryBook(book, borrower);
  }

  public static LibraryBook newReturnedBook(LibraryBook book) {
    return new LibraryBook(book);
  }


  @Override
  public String toString() {
    return "LibraryBook{" +
        "id='" + id + '\'' +
        ", lent=" + lent +
        ", book=" + book +
        '}';
  }
}
