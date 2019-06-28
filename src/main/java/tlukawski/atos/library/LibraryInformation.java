package tlukawski.atos.library;

import java.util.Collection;
import tlukawski.atos.library.book.LibraryBook;

public final class LibraryInformation {
  private final long borrowedBooksCount;
  private final long availableBooksCount;
  private final Collection<LibraryBook> books;

  LibraryInformation(Collection<LibraryBook> books) {
    this.books = books;
    borrowedBooksCount = books.stream().filter(b -> b.isBorrowed()).count();
    availableBooksCount = books.stream().filter(b -> !b.isBorrowed()).count();
  }

  @Override
  public String toString() {
    return "LibraryInformation{" +
        "borrowedCount=" + borrowedBooksCount +
        ", availableCount=" + availableBooksCount +
        ", books=" + books +
        '}';
  }
}
