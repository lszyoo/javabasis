package source;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @Writer ArtisanLS
 * @Date 2022/12/6
 */
public class TestSource implements SourceFunction<String> {
    private boolean isRunning = true;

    @Override
    public void run(SourceContext<String> ctx) throws Exception {
        while (isRunning) {
            ctx.collect("a b c");
            ctx.collect("b");
            Thread.sleep(2000);
//            Thread.sleep(1000);
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
