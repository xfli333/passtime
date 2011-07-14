import builder.HtmlBuilder;
import content.QiuShi;
import spider.AbsSpider;
import spider.FDaySpider;
import spider.QiushiSpider;

import javax.jdo.PersistenceManagerFactory;
import java.util.ArrayList;
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
    static ExecutorService service = Executors.newFixedThreadPool(2);

    static ExecutorService mainService = Executors.newFixedThreadPool(20);
    int i=0;

    public static void main(String[] args) {
        new HttpClientTest().executeThread();

    }

    private void executeThread(){
        for(int i=0;i<20;i++){
            mainService.execute(new SpiderThread(i));
        }
        mainService.shutdown();
    }


    class SpiderThread implements Runnable {
        private int index=0;
        SpiderThread(int index) {
            this.index=index;
        }

        @Override
        public void run() {
            final AbsSpider qiuShiSpider = new QiushiSpider();
            final AbsSpider fdShiSpider = new FDaySpider();
            List<QiuShi> qiuShiList = new ArrayList<QiuShi>();

            Future<List<QiuShi>> future1 = service.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    List<QiuShi> qiuShiList = (List<QiuShi>) qiuShiSpider.spiderContent("http://www.qiushibaike.com/hot.php?s="+(index*20));
                    return qiuShiList;
                }
            });
            Future<List<QiuShi>> future2 = service.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    List<QiuShi> fdShiList = (List<QiuShi>) fdShiSpider.spiderContent("http://www.caoegg.cn/?page="+(index+1));
                    return fdShiList;
                }
            });

            try {
                qiuShiList = future1.get();
                qiuShiList.addAll(future2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            HtmlBuilder.createHtml("content.ftl", qiuShiList, "content"+index);
            service.shutdown();
            System.out.println("thread "+index+" down");
        }
    }


}
