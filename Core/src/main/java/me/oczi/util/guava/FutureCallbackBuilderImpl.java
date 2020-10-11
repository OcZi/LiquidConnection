package me.oczi.util.guava;

import com.google.common.util.concurrent.FutureCallback;

import java.util.function.Consumer;

class FutureCallbackBuilderImpl<T> implements FutureCallbackBuilder<T> {
    private Consumer<T> success;
    private Consumer<Throwable> failure;

    @Override
    public FutureCallbackBuilder<T> onSuccess(Consumer<T> success) {
        this.success = success;
        return this;
    }

    @Override
    public FutureCallbackBuilder<T> onFailure(Consumer<Throwable> failure) {
        this.failure = failure;
        return this;
    }

    @Override
    public FutureCallback<T> build() {
        return new FutureCallback<T>() {
            @Override
            public void onSuccess(T result) {
                if (success == null) {
                    return;
                }
                success.accept(result);
            }

            @Override
            public void onFailure(Throwable t) {
                if (failure == null) {
                    return;
                }
                failure.accept(t);
            }
        };
    }
}
