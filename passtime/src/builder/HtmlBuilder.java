package builder;

import content.QiuShi;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11-7-14
 * Time: 上午10:32
 * To change this template use File | Settings | File Templates.
 */
public class HtmlBuilder {


    public static void createHtml(String ftlName,List<QiuShi> objects,String htmlName) {
        String currentPath = HtmlBuilder.class.getResource("").getPath();
        String templatePath = currentPath.substring(0, currentPath.length() - 9);
        templatePath += "/template/";

        Configuration cfg = new Configuration();
        try {
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            cfg.setDefaultEncoding("utf-8");
            cfg.setObjectWrapper(new DefaultObjectWrapper());
            Map root = new HashMap();
            root.put("objects", objects);
            Template temp = cfg.getTemplate(ftlName);
            Writer out = new OutputStreamWriter(FileUtils.openOutputStream(new File(templatePath+htmlName+".html")));
            temp.process(root,out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
