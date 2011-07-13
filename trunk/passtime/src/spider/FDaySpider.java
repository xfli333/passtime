package spider;

import content.QiuShi;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11-7-13
 * Time: 下午7:46
 * To change this template use File | Settings | File Templates.
 */
public class FDaySpider extends AbsSpider {


    @Override
    protected List<QiuShi> parserHtml(String html) {
        System.out.println("start......");
        List<QiuShi> qiuShis = new ArrayList<QiuShi>();
        Parser parser = Parser.createParser(html, "utf-8");
        NodeFilter divFilter = new NodeClassFilter(Div.class);
        OrFilter lastFilter = new OrFilter();
        lastFilter.setPredicates(new NodeFilter[]{divFilter});
        try {
            NodeList nodeList = parser.extractAllNodesThatMatch(lastFilter);
            for (Node node : nodeList.toNodeArray()) {
                QiuShi qiuShi = new QiuShi();
                String divInfo = node.getText();
                if (divInfo.equals("div class=\"c\"")) {
                    //a href="/view/91037"
                    String hrefTxt = node.getFirstChild().getText();
                    String id = hrefTxt.substring(hrefTxt.lastIndexOf("/") + 1, hrefTxt.length() - 1);
//                    System.out.println("node1:" + id);
                    qiuShi.setId(id);
                    String content = node.getFirstChild().getFirstChild().toHtml();
//                    System.out.println("node2:" + content);
                    qiuShi.setContent(content);
                    qiuShi.setOriginType("FUCK-DAY");
                    qiuShis.add(qiuShi);
                }

            }
        } catch (ParserException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return qiuShis;
    }
}
