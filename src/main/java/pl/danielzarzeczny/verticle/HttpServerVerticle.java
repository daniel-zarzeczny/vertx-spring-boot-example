package pl.danielzarzeczny.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import pl.danielzarzeczny.annotation.Verticle;

@Verticle
class HttpServerVerticle extends AbstractVerticle {

    private static final int DEFAULT_HTTP_PORT = 8080;

    private final int httpPort;

    @Autowired
    HttpServerVerticle(@Value("${server.port}") final int httpPort) {
        this.httpPort = getHttpPort(httpPort);
    }

    private int getHttpPort(final int httpPort) {
        if (httpPort < 0) return DEFAULT_HTTP_PORT;
        return httpPort;
    }

    @Override
    public void start(final Future<Void> startFuture) throws Exception {

        vertx.createHttpServer()
                .requestHandler(requestHandler())
                .listen(httpPort, serverStartHandler(startFuture));
    }

    private Handler<HttpServerRequest> requestHandler() {
        return onRequest -> onRequest.response().end("It works!");
    }

    private Handler<AsyncResult<HttpServer>> serverStartHandler(final Future<Void> startFuture) {
        return onComplete -> {
            if (onComplete.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(onComplete.cause());
                System.exit(0);
            }
        };
    }
}
