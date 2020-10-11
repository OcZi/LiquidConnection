package me.oczi.util.guava;

import com.google.common.util.concurrent.FutureCallback;

import java.util.function.Consumer;

/**
 * Guava's future utils.
 */
public interface GFutures {

    static <T> FutureCallbackBuilder<T> newBuilder() {
        return new FutureCallbackBuilderImpl<>();
    }

    static <T> FutureCallback<T> newFutureCallback(Consumer<T> success,
                                                   Consumer<Throwable> failure) {
        FutureCallbackBuilder<T> builder = newBuilder();
        return builder
            .onSuccess(success)
            .onFailure(failure)
            .build();
    }
}
