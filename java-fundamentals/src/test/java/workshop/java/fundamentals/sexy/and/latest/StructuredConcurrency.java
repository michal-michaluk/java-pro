package workshop.java.fundamentals.sexy.and.latest;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class StructuredConcurrency {

    Response handle() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> user = scope.fork(this::findUser);
            Future<Integer> order = scope.fork(this::fetchOrder);

            scope.join();           // Join both forks
            scope.throwIfFailed();  // ... and propagate errors

            // Here, both forks have succeeded, so compose their results
            return new Response(user.resultNow(), order.resultNow());
        }
    }

    record Response(String user, int order) {}

    private String findUser() {
        return null;
    }

    private int fetchOrder() {
        return 0;
    }
}
