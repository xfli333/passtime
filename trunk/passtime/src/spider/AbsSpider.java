package spider;

import content.QiuShi;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11-7-13
 * Time: 下午7:47
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbsSpider {
    public List<QiuShi> spiderContent(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            StringBuffer stringBuffer = new StringBuffer();
            List<String> lines = IOUtils.readLines(entity.getContent());
            for (String line : lines) {
                stringBuffer.append(line);
            }
            return parserHtml(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract List<QiuShi> parserHtml(String html);
}
