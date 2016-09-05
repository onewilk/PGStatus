package it.wilk.pg.parse;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;

import it.wilk.pg.PGApplication;
import it.wilk.pg.utils.SortByNameAndStatus;
import it.wilk.pg.model.StatusInfo;

/**
 * 网页解析
 * Created by Mr.Wilk on 2016/08/12 0012.
 */

public class WebParser {

    public static void getData() throws IOException {
        PGApplication.mResult.clearData();

        String WEB_URL = "http://pgo.xose.net/";
        Elements mElementList = Jsoup.connect(WEB_URL).get().body().getElementsByTag("li");

        Element temp;
        for (int index = 0; index < mElementList.size(); index++) {
            temp = mElementList.get(index);

            String url = temp.getElementsByTag("img").attr("src");
            String name = temp.textNodes().get(0).text();
            boolean isOpen = temp.getElementsByTag("span").get(1).text().equals("YES");
            if (isOpen)
                PGApplication.mResult.openNum += 1;
            else
                PGApplication.mResult.closeNum += 1;
            PGApplication.mResult.dataList.add(new StatusInfo(url, name, isOpen));
            Collections.sort(PGApplication.mResult.dataList,new SortByNameAndStatus());
        }
    }
}
