package com.addy360.proxyserver.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
public class PageRequester {

    private Document getPage(String url) {

        try {
            return Jsoup.connect(url).followRedirects(true).get();
        } catch (Exception e) {
            return null;
        }
    }

    public String loadDocument(String url) {
        Document page = getPage(url);
        if (page == null) return "Error while fetching page.";
        return page.toString();
    }

}
