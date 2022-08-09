package com.addy360.proxyserver.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
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

    public String requestUrl(String url) {
        log.info("Sending request : {}", url);
        Request request = new Request
                .Builder()
                .url(url)
                .post(RequestBody.create("", MediaType.parse("application/json")))
                .addHeader("User-Agent", "PostmanRuntime/7.29.0")
                .build();

        OkHttpClient client = new OkHttpClient.Builder().build();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                log.info("Error : {}", response.message());
            }
            ResponseBody body = response.body();
            if (body == null) return "";
            return body.string();
        } catch (Exception e) {
            return e.getMessage();
        }


    }
}
