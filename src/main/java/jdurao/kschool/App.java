package jdurao.kschool;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 10)
@Measurement(iterations = 20)
public class App {
    @Param({"10000000"})
    private int N;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PostgresOperations.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();

        PostgresOperations.init();
    }
}
