package source;

import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

/**
 * 旧框架
 *
 * @Writer ArtisanLS
 * @Date 2023/1/30
 */
public class MemorySource<String> extends RichParallelSourceFunction<String> {
    @Override
    public void run(SourceContext ctx) throws Exception {
        int ct = 1;
        while (true) {
            ctx.collect("a," + 1);
            Thread.sleep(2000);
            ct++;
        }
    }

    @Override
    public void cancel() {

    }
}
