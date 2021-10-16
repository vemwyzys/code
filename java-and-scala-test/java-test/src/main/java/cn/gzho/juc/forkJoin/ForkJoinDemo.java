package cn.gzho.juc.forkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-09 9:59 AM
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    long start;
    long end;

    private int temp = 10000;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (end + start) / 2;
            ForkJoinDemo joinDemo1 = new ForkJoinDemo(start, middle);
            ForkJoinDemo joinDemo2 = new ForkJoinDemo(middle + 1, end);
            joinDemo1.fork();
            joinDemo2.fork();
            return joinDemo1.join() + joinDemo2.join();
        }
    }

    public static void main(String[] args) {
        long num = 10_0000_0000;

        long sum = 0;
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            sum += i;
        }
        System.out.println(sum);
        System.out.println(System.currentTimeMillis() - l1);

        long l2 = System.currentTimeMillis();
        System.out.println(new ForkJoinDemo(0, num).compute());
        System.out.println(System.currentTimeMillis()-l2);

        long l3 = System.currentTimeMillis();
        System.out.println(LongStream.rangeClosed(0, num).parallel().reduce(0L, Long::sum));
        System.out.println(System.currentTimeMillis()-l3);

    }
}
