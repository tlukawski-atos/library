package tlukawski.atos.library.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Book {

  private final String title;
  private final String author;
  private final int year;

  @Override
  public String toString() {
    return "Book{" +
        "title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", year=" + year +
        '}';
  }
}
