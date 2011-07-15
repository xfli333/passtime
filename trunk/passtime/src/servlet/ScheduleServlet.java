package servlet;

import builder.HtmlCreater;
import content.QiuShi;
import spider.AbsSpider;
import spider.FDaySpider;
import spider.QiushiSpider;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * User: Lee
 * Date: 11-7-14
 * Time: 下午1:40
 */
public class ScheduleServlet extends HttpServlet {
     ExecutorService service = Executors.newFixedThreadPool(2);

     ExecutorService mainService = Executors.newFixedThreadPool(20);

     ScheduledExecutorService scheduleService = Executors.newSingleThreadScheduledExecutor();

    int i = 0;

    String createHtmlPath="";


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("path :" + req.getSession().getServletContext().getRealPath(""));
        createHtmlPath=req.getSession().getServletContext().getRealPath("");
        executeThread();
    }


    private void executeThread() {
        scheduleService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule beginning....");
                for (int i = 0; i < 20; i++) {
                    mainService.execute(new SpiderThread(i));
                }
                mainService.shutdown();
            }
        }, 0, 1, TimeUnit.HOURS);

    }


    class SpiderThread implements Runnable {
        private int index = 0;

        SpiderThread(int index) {
            this.index = index;
        }

        @Override
        public void run() {
            final AbsSpider qiuShiSpider = new QiushiSpider();
            final AbsSpider fdShiSpider = new FDaySpider();
            List<QiuShi> qiuShiList = new ArrayList<QiuShi>();

            Future<List<QiuShi>> future1 = service.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    List<QiuShi> qiuShiList = (List<QiuShi>) qiuShiSpider.spiderContent("http://www.qiushibaike.com/hot.php?s=" + (index * 20));
                    return qiuShiList;
                }
            });
            Future<List<QiuShi>> future2 = service.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    List<QiuShi> fdShiList = (List<QiuShi>) fdShiSpider.spiderContent("http://www.caoegg.cn/?page=" + (index + 1));
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

            HtmlCreater.createHtml("content.ftl", qiuShiList, createHtmlPath+"/content/"+"content" + index);
            service.shutdown();
            System.out.println("thread " + index + " down");
        }
    }


}
