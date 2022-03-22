package com.practicalbits.loader;

/*
Class copied from GraalVM.org and renamed with modifications
https://www.graalvm.org/docs/examples/java-performance-examples/
 */
public class Counter {
    static final int ITERATIONS = Math.max(Integer.getInteger("iterations", 1), 1);

    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.count(String.join("", args));
    }

    public void count(String sentence){
        for (int iter = 0; iter < ITERATIONS; iter++) {
            if (ITERATIONS != 1) System.out.println("-- iteration " + (iter + 1) + " --");
            long total = 0, start = System.currentTimeMillis(), last = start;
            for (int i = 1; i < 10_000_000; i++) {
                total += sentence.chars().filter(Character::isUpperCase).count();
                if (i % 1_000_000 == 0) {
                    long now = System.currentTimeMillis();
                    System.out.printf("%d (%d ms)%n", i / 1_000_000, now - last);
                    last = now;
                }
            }
            System.out.printf("total: %d (%d ms)%n", total, System.currentTimeMillis() - start);
        }
    }
}
