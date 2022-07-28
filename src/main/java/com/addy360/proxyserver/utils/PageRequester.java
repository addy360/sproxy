package com.addy360.proxyserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PageRequester {

    private Document getPage(String url) {

        try {
            return Jsoup.connect(url)
                    .followRedirects(true)
                    .get();
        } catch (Exception e) {
            return null;
        }
    }

    public String loadDocument(String url) {
        Document page = getPage(url);
        if (page == null) return "Error while fetching page.";
        Elements src = page.getElementsByAttribute("src");
        log.info("Base url : {}", page.baseUri());
        log.info("src : {}", src);
        return page.toString();
    }

}
