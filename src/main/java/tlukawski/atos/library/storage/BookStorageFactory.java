package tlukawski.atos.library.storage;

public class BookStorageFactory {

  public static BookStorage getBookStorage()
  {
    return new InMemoryBookStorage();
  }
}
