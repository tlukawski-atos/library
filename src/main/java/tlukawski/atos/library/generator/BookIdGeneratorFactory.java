package tlukawski.atos.library.generator;

public class BookIdGeneratorFactory {

  public static BookIdGenerator getBookIdGenerator() {
    return new UUIDBookIdGenerator();
  }
}
