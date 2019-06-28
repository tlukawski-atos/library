package tlukawski.atos.library.query;


import java.util.function.Function;
import tlukawski.atos.library.book.LibraryBook;

public class BookQueryBuilder {

  BookQueryOperator lastQueryOperator;

  public static BookQueryBuilder findBy() {
    return new BookQueryBuilder();
  }

  private <T> BookQuery newEqualCondition(T search, Function<LibraryBook, T> function) {
    BookQuery<T> condition = new BookQuery<>(this);
    condition.setMapFunction(function);
    condition.setQueryPhrase(search);
    condition.setQueryCondition(BookQueryCondition.EQUALS);
    condition.setPreviousQueryOperator(lastQueryOperator);
    return condition;
  }

  public BookQuery title(String search) {
    return newEqualCondition(search, book -> book.getBook().getTitle());
  }

  public BookQuery borrowed(boolean search) {
    return newEqualCondition(search, LibraryBook::isBorrowed);
  }

  public BookQuery author(String search) {
    return newEqualCondition(search, book -> book.getBook().getAuthor());
  }

  public BookQuery year(Integer search) {
    return newEqualCondition(search, book -> book.getBook().getYear());
  }

  public void setLastQueryPart(BookQueryOperator lastQueryOperator) {
    this.lastQueryOperator = lastQueryOperator;
  }

}
