package com.dayone.scraper;

import com.dayone.model.Company;
import com.dayone.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker); // 메서드
    ScrapedResult scrap(Company company); // 메서드
}
