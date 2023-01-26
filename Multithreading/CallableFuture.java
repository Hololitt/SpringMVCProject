import java.util.Random;
import java.util.concurrent.*;

public class CallableFuture {
   // private static int number;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*
        ExecutorService executorService = Executors.newFixedThreadPool(1);
executorService.submit(() -> {
    System.out.println("start");
Thread.sleep(3000);
    System.out.println("finish");
    Random random = new Random();
    return number = random.nextInt(100);
});
executorService.shutdown();

executorService.awaitTermination(4, TimeUnit.SECONDS);

        System.out.println(number);

         */
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();

int value = random.nextInt(5);
if(value == 1) {
    throw new Exception("Too small number");
}
    return value;

            }
        });
        executorService.shutdown();
        try{
        System.out.println(future.get());
    }catch(Exception e){
            Throwable t = e.getCause();
            System.out.println(t.getMessage());
        }
}}
