package me.oczi.util.guava;

import com.google.common.util.concurrent.FutureCallback;

import java.util.function.Consumer;

/**
 * Builder of {@link FutureCallback}.
 * @param <T> Type.
 */
public interface FutureCallbackBuilder<T> {

    FutureCallbackBuilder<T> onSuccess(Consumer<T> success);

    FutureCallbackBuilder<T> onFailure(Consumer<Throwable> failure);

    FutureCallback<T> build();
}
