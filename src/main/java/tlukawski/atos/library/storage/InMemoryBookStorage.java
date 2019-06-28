package tlukawski.atos.library.storage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import tlukawski.atos.library.book.LibraryBook;
import tlukawski.atos.library.query.BookQuery;

public class InMemoryBookStorage implements BookStorage {

  /**
   * ConcurrentHashMap will be suffient for multithread usage.
   * There could be some problems with identifying operation error
   * causes, but storage consistency should not be broken.
   * TODO: Add synchronization if stronger consistency needed.
   */
  private ConcurrentMap<String, LibraryBook> map = new ConcurrentHashMap<>();

  public void addToStorage(LibraryBook book) {
    map.putIfAbsent(book.getId(), book);
  }

  @Override
  public boolean updateInStorage(LibraryBook oldBook, LibraryBook newBook) {
    return map.replace(newBook.getId(), oldBook, newBook);
  }

  public LibraryBook findBookById(String id) {
    return map.get(id);
  }

  /**
   * TODO: Search function is simplified to process conditions with only "AND" logical
   *       operator for joining conditions and "EQUAL" operator for condition relation.
   *       It should be refactored if other operations will be introduced.
   * @param condition
   * @param <T>
   * @return
   */
  public <T> List<LibraryBook> find(BookQuery<T> condition) {
    List<LibraryBook> result = Optional.ofNullable(condition.getLogicalOperator())
        .map(operator -> find(operator.getNextCondition())).orElse(map.values().stream()
            .collect(Collectors.toList()));

    return result.stream()
        .filter(book -> condition.getMapFunction().apply(book).equals(condition.getQueryPhrase()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean removeFromStorage(LibraryBook book) {
    return map.remove(book.getId(), book);
  }

  @Override
  public List<LibraryBook> findAll() {
    return map.values().stream()
        .collect(Collectors.toList());
  }
}
