package tlukawski.atos.library.book;

import lombok.Getter;

@Getter
public final class Book {

  public Book(String title, String author, int year) {
    this.title = title;
    this.author = author;
    this.year = year;
  }

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
