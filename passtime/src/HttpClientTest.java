import content.QiuShi;
import spider.AbsSpider;
import spider.FDaySpider;
import spider.QiushiSpider;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11-7-13
 * Time: 下午2:45
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientTest {


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        final AbsSpider qiuShiSpider = new QiushiSpider();
        final AbsSpider fdShiSpider = new FDaySpider();

        Future<List<QiuShi>> future1 = service.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                List<QiuShi> qiuShiList = (List<QiuShi>) qiuShiSpider.spiderContent("http://www.qiushibaike.com/hot.php");
                return qiuShiList;
            }
        });
        try {
            for (QiuShi qiuShi : future1.get()) {
                System.out.println(qiuShi.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        Future<List<QiuShi>> future2 = service.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                List<QiuShi> fdShiList = (List<QiuShi>) fdShiSpider.spiderContent("http://www.caoegg.cn/?page=1");
                return fdShiList;
            }
        });
        try {
            for (QiuShi qiuShi : future2.get()) {
                System.out.println(qiuShi.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();

    }


}
