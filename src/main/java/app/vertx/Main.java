package app.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;

import static io.vertx.core.Vertx.vertx;
import static java.lang.System.err;
import static java.lang.System.out;

public enum Main {
  ;

  public static void main(String... args) {
    vertx()
      .deployVerticle(new HiThere())
      .onSuccess(it -> out.println("Hi-there deployed!"))
      .onFailure(it -> err.println("Hi-there not deployed: " + it.getMessage()));
  }
}

final class HiThere extends AbstractVerticle implements Verticle {
  @Override
  public void start(final Promise<Void> start) {
    vertx.createHttpServer()
      .requestHandler(it -> it.response().end("Hi there!"))
      .listen(8080)
      .onSuccess(it -> start.complete())
      .onFailure(start::fail);
  }
}
