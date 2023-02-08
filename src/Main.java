import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<ResultHolder> myCallable = new MyCallable("поток 1");
        Callable<ResultHolder> myCallable2 = new MyCallable("поток 2");
        Callable<ResultHolder> myCallable3 = new MyCallable("поток 3");
        Callable<ResultHolder> myCallable4 = new MyCallable("поток 4");
        List<Callable<ResultHolder>> callables = Arrays.asList(myCallable, myCallable2, myCallable3, myCallable4);
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final List<Future<ResultHolder>> task = threadPool.invokeAll(callables);

        System.out.println("Завершаю все потоки.");

        task.forEach(resultHolderFuture -> {
            ResultHolder resultHolder;
            try {
                resultHolder = resultHolderFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            System.out.println(resultHolder.getThreadName() + " — Кол-во отправленных сообщений: " + resultHolder.getResult());
        });

        final ResultHolder task2 = threadPool.invokeAny(callables);

        System.out.println("Завершаю все потоки.");

        System.out.println(task2.getThreadName() + " — Кол-во отправленных сообщений: " + task2.getResult());

        threadPool.shutdown();
    }

}