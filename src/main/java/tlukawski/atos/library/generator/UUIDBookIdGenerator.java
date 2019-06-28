package tlukawski.atos.library.generator;

import java.util.UUID;

public class UUIDBookIdGenerator implements BookIdGenerator{

  @Override
  public String generateUniqueId() {
    return UUID.randomUUID().toString();
  }
}
