package parsers.impl;

import exceptions.WebsiteFormatException;
import models.PageItem;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsers.PageItemParser;
import parsers.PageParser;
import utils.enums.PageCssClass;

import java.util.ArrayList;
import java.util.Collection;

public class CompaniesPageParser implements PageParser {
    PageItemParser parser;

    public CompaniesPageParser(CompanyParser parser) {
        this.parser = parser;
    }

    public CompaniesPageParser() {
        this.parser = new CompanyParser();
    }

    @Override
    public Collection<PageItem> parse(Document page) throws WebsiteFormatException {
        Elements companyBlocks = page.getElementsByClass(PageCssClass.COMPANIES_ITEM.toString());
        Collection<PageItem> companies = new ArrayList<>();

        if (companyBlocks.isEmpty()) {
            throw new WebsiteFormatException();
        }

        for (Element companyBlock : companyBlocks) {
            companies.add(parser.parsePageItem(companyBlock, page.location()));
        }
        return companies;
    }

}
