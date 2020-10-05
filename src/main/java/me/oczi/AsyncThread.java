package me.oczi;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * Single-Asynchronous thread.
 * Used to run {@link LiquidConnector} async.
 */
public class AsyncThread {
    private static final ListeningExecutorService executorService;

    static {
        executorService = MoreExecutors.listeningDecorator(
            Executors.newSingleThreadExecutor());
    }

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public static <T> ListenableFuture<T> submit(Callable<T> callback) {
        return executorService.submit(callback);
    }

    public static ListeningExecutorService getExecutorService() {
        return executorService;
    }
}
