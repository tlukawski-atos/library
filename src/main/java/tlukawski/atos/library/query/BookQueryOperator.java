package tlukawski.atos.library.query;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class BookQueryOperator{

  public enum BookQueryLogicalOperator {
    AND
  }

  @Getter @Setter @NonNull
  private BookQueryLogicalOperator operator;

  @Getter @Setter
  private BookQuery nextCondition;

}
