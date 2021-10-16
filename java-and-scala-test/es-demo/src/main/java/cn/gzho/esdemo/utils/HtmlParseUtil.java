package cn.gzho.esdemo.utils;

import cn.gzho.esdemo.pojo.JDGoodInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * parse the jd goods website,
 * return the list of goodsInfo of the first page
 * @author gzho
 * @version 1.0.0
 * @since 2021-10-06 10:01 AM
 */
@Component
public class HtmlParseUtil {

    public List<JDGoodInfo> pareJD(String keyword) {
        String url = "https://search.jd.com/Search?keyword=" + keyword;

        //parse the website, return the document in the website
        Document document = null;
        try {
            document = Jsoup.parse(new URL(url), 30000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element element = document.getElementById("J_goodsList");

        Elements elementsByTag = element.getElementsByTag("li");

        ArrayList<JDGoodInfo> list = new ArrayList<>();
        for (Element el : elementsByTag) {
            String img = el.getElementsByAttribute("data-lazy-img").first().attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            list.add(new JDGoodInfo(null,title, img, Double.parseDouble(price.split(" ")[0].substring(1))));
        }
        return list;
    }

    public static void main(String[] args) {
        new HtmlParseUtil().pareJD("java").forEach(System.out::println);
    }
}
