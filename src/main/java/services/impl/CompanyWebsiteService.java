package services.impl;

import exceptions.WebsiteFormatException;
import models.PageItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parsers.PageParser;
import parsers.impl.CompaniesPageParser;
import services.WebsiteService;
import utils.enums.PageCssClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompanyWebsiteService implements WebsiteService {
    public static final Logger log = Logger.getLogger(CompanyWebsiteService.class.getName());
    PageParser parser;

    public CompanyWebsiteService(CompaniesPageParser parser) {
        this.parser = parser;
    }

    public CompanyWebsiteService() {
        this.parser = new CompaniesPageParser();
    }

    @Override
    public Collection<PageItem> getItems(String baseUrl, String firstPageLink) {
        Collection<PageItem> pageItems = new ArrayList<>();
        Document page;
        String url = baseUrl + firstPageLink;

        try {
            do {
                log.info("connecting to " + url);
                page = Jsoup.connect(url).get();
                log.info("connected successfully");
                log.info("parsing page " + url);
                pageItems.addAll(parser.parse(page));
                log.info("parsed successfully");
                url = getNextUrl(baseUrl, page);
            } while (hasNext(page));
        } catch (IOException e) {
            log.log(Level.SEVERE,"connection failed", e);
        } catch (WebsiteFormatException e) {
            log.log(Level.SEVERE, "parsing failed", e);
        }

        return pageItems;
    }

    public static boolean hasNext(Document page) {
        return !page.getElementsByClass(PageCssClass.NEXT_PAGE.toString()).attr("href").isEmpty();
    }

    public static String getNextUrl(String baseUrl, Document page) {
        return baseUrl + page.getElementsByClass(PageCssClass.NEXT_PAGE.toString()).attr("href");
    }
}

