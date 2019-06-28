package tlukawski.atos.library.query;

import java.util.function.Function;
import lombok.Data;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.query.BookQueryOperator.BookQueryLogicalOperator;

@Data
public class BookQuery<T>{

  private T queryPhrase;
  private BookQueryCondition queryCondition;
  private String field;
  private BookQueryOperator logicalOperator;
  private Function<LibraryBook, T> mapFunction;
  private BookQueryBuilder builder;

  public BookQuery(BookQueryBuilder bookQueryBuilder) {
    builder = bookQueryBuilder;
  }

  public BookQueryBuilder and()
  {
    BookQueryOperator operator = new BookQueryOperator(BookQueryLogicalOperator.AND);
    operator.setNextCondition(this);
    builder.setLastQueryPart(operator);
    return builder;
  }

  public void setPreviousQueryOperator(BookQueryOperator queryOperator) {
    logicalOperator = queryOperator;
  }
}
