import java.util.concurrent.Callable;

public class MyCallable implements Callable<ResultHolder> {
    String name;

    public MyCallable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public ResultHolder call() throws Exception {
        int j = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println("Я " + name + ". Всем привет!");
            j++;
            Thread.sleep(3000);
        }
        ResultHolder resultHolder = new ResultHolder();
        resultHolder.setResult(j);
        resultHolder.setThreadName(name);
        return resultHolder;
    }
}