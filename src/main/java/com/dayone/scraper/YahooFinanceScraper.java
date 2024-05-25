package com.dayone.scraper;

import com.dayone.model.Company;
import com.dayone.model.Dividend;
import com.dayone.model.ScrapedResult;
import com.dayone.model.constants.Month;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class YahooFinanceScraper implements Scraper {

    private static final String STATISTICS_URL =
            "https://finance.yahoo.com/quote/%s/history?period1=%d&period2=%d&interval=1mo&frequency=1mo";
    private static final String SUMMARY_URL = "https://finance.yahoo.com/quote/%s?p=%s";
    private static final long START_TIME = 86400; // 60초 * 60분 * 24시간 1일

    @Override
    public ScrapedResult scrap(Company company) { // Company를 받아 ScrapedResult 리턴하는 메서드

        var scrapResult = new ScrapedResult();
        scrapResult.setCompany(company);

        try {
            long now = System.currentTimeMillis() / 1000; // 밀리세컨드이기에 초로 바꾸기위해 1000을 나눔
            // System.currentTimeMillis() 는 1970년부터 경과된 밀리세컨드를 보여준다

            String url = String.format(STATISTICS_URL, company.getTicker(), START_TIME, now);

            Connection connection = Jsoup.connect(url);
            Document document = connection.get();

            Elements parsingDivs = document.getElementsByClass(".table svelte-ewueuo");
            if (parsingDivs.isEmpty()) {
                throw new RuntimeException("No tables found with the specified class");
            }

            Element tableEle = parsingDivs.get(0); // table 전체

            Element tbody = tableEle.children().get(1);

            List<Dividend> dividends = new ArrayList<>();
            for (Element e : tbody.children()) {
                String txt = e.text();
                if (!txt.endsWith("Dividend")) {
                    continue;
                }

                String[] splits = txt.split(" ");
                int month = Month.strToNumber(splits[0]);
                int day = Integer.valueOf(splits[1].replace(",", ""));
                int year = Integer.valueOf(splits[2]);
                String dividend = splits[3];

                if (month < 0) { // 정상적인 월을 입력하지 않았을 경우
                    throw new RuntimeException("Unexpected Month enum value -> " + splits[0]);
                }

                dividends.add(new Dividend(LocalDateTime.of(year, month, day, 0, 0), dividend));

//                System.out.println(year + "/" + month + "/" + day + " -> " + dividend);

            }
            scrapResult.setDividends(dividends);

        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

        return scrapResult;

    }

    @Override
    public Company scrapCompanyByTicker(String ticker) {
        String url = String.format(SUMMARY_URL, ticker, ticker);

        try {
            Document document = Jsoup.connect(url).get();
            Element titleEle = document.getElementsByTag("h1").get(0);
            String fullTitle = titleEle.text();
            String title;

            // Split the title and handle various formats
            String[] titleParts = fullTitle.split(" - ");
            if (titleParts.length >= 2) {
                title = titleParts[1].trim();
            } else {
                title = fullTitle.trim();
            }

            return new Company(ticker, title);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            // Log the exception for debugging
            System.err.println("Unexpected title format: " + e.getMessage());
        }

        return null;
    }

}
