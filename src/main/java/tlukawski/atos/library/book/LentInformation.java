package tlukawski.atos.library.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class LentInformation {

  private final String borrower;

  @Override
  public String toString() {
    return "LentInformation{" +
        "borrower='" + borrower + '\'' +
        '}';
  }
}
