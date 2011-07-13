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
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class QiushiSpider extends AbsSpider {
    public QiushiSpider() {
    }

    @Override
    public List<QiuShi> parserHtml(String html) {
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
                if (divInfo.startsWith("div class=\"qiushi_body article \"")) {
                    int titleLocation = divInfo.indexOf("title=");
                    String id = divInfo.substring(divInfo.indexOf("id=") + 4, titleLocation - 2);
//                    System.out.println("id:" + id);
                    qiuShi.setId(id);
                    String createTime = divInfo.substring(titleLocation + 7, divInfo.length() - 1);
//                    System.out.println("createTime :" + createTime);
                    qiuShi.setCreateTime(createTime);
                    String content=node.getFirstChild().toHtml();
//                    System.out.println("Content:" + node.getFirstChild().toHtml());
                    qiuShi.setContent(content);
                    if (node.getChildren().size() > 3) {
                        String thirdText = node.getChildren().elementAt(3).getText();
                        if (thirdText.startsWith("a href=\"http://www.qiushibaike.com/system/pictures")) {
//                            String imageUrl=node.getChildren().elementAt(3).toHtml();
//                            System.out.println("image:" + imageUrl);
                            String imageUrl=node.getChildren().elementAt(3).getFirstChild().toHtml();
                            qiuShi.setImageUrl(imageUrl);
                        }
                    }
                    qiuShi.setOriginType("QIUSHI");
                    qiuShis.add(qiuShi);
                }
            }
        } catch (ParserException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return qiuShis;
    }

}
