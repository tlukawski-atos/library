package tlukawski.atos.library.book;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public
class LibraryBook  {
  @NonNull
  private String id;
  private LentInformation lent;
  @NonNull
  private Book book;

  public boolean isBorrowed() {
    return lent != null;
  }

  private LibraryBook(LibraryBook book, String borrower)
  {
    this.book =  book.getBook();
    this.id = book.getId();
    lent = new LentInformation(borrower);
  }

  private LibraryBook(LibraryBook book)
  {
    this.book =  book.getBook();
    this.id = book.getId();
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
