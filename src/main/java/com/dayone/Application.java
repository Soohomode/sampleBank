package com.dayone;

import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;
import com.dayone.scraper.NaverFinanceScraper;
import com.dayone.scraper.Scraper;
import com.dayone.scraper.YahooFinanceScraper;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        System.out.println("Main -> " + Thread.currentThread().getName());

        // 스레드 슬립 테스트
//        for (int i = 0; i < 10; i++) {
//            System.out.println("Hello -> " + i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        // 자동완성 테스트
//        Trie trie = new PatriciaTrie();
//
//        AutoComplete autoComplete = new AutoComplete(trie);
//        AutoComplete autoComplete1 = new AutoComplete(trie);
//
//        autoComplete.add("hello");
//        autoComplete1.add("hello");
//
//        System.out.println(autoComplete.get("hello"));
//        System.out.println(autoComplete1.get("hello"));

    }
}
