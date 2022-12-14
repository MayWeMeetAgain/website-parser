package parsers.impl;

import exceptions.WebsiteFormatException;
import models.impl.Company;
import models.PageItem;
import org.jsoup.nodes.Element;
import parsers.PageItemParser;
import utils.enums.CompanyOptionCssClass;
import utils.ElementExtension;
import utils.enums.PageCssClass;

import java.time.LocalDateTime;

public class CompanyParser implements PageItemParser {

    @Override
    public PageItem parsePageItem(Element companyBlock, String pageUrl) throws IllegalArgumentException {
        if (!companyBlock.classNames().contains(PageCssClass.COMPANIES_ITEM.toString()))
            throw new IllegalArgumentException();

        String name = parseName(ElementExtension.getElementByClassName(companyBlock, CompanyOptionCssClass.NAME_CLASS));
        Boolean isAccredited = ElementExtension.ifExist(companyBlock, CompanyOptionCssClass.IS_ACCREDITED);
        Boolean isHabrMember = ElementExtension.ifExist(companyBlock, CompanyOptionCssClass.IS_HABR_MEMBER);
        Double rating = parseRating(ElementExtension.getElementByClassName(companyBlock, CompanyOptionCssClass.RATING));
        String location = parseLocation(ElementExtension.getElementByClassName(companyBlock, CompanyOptionCssClass.LOCATION));
        String sizeType = parseSizeType(ElementExtension.getElementByClassName(companyBlock, CompanyOptionCssClass.SIZE));
        Integer vacanciesCount = parseVacanciesCount(ElementExtension.getElementByClassName(companyBlock, CompanyOptionCssClass.VACANCIES));
        String companyUrl = parseLink(ElementExtension.getElementByClassName(companyBlock, CompanyOptionCssClass.NAME_CLASS));

        return new Company(name, isAccredited, isHabrMember, rating, location, sizeType, vacanciesCount, companyUrl, pageUrl, LocalDateTime.now());
    }

    private static String parseName(Element titleBlock) throws WebsiteFormatException {
        if (titleBlock == null)
            throw new WebsiteFormatException();
        return titleBlock.text();
    }

    private static Double parseRating(Element ratingBlock) {
        if (ratingBlock == null)
            return null;
        return Double.parseDouble(ratingBlock.text());
    }

    private static String parseLocation(Element locationBlock) {
        if (locationBlock == null)
            return null;
        return locationBlock.select("a").text();
    }

    private static String parseSizeType(Element sizeBlock) {
        if (sizeBlock == null)
            return null;
        return sizeBlock.select("a").text();
    }

    private static Integer parseVacanciesCount(Element vacanciesCountBlock) {
        if (vacanciesCountBlock == null)
            return 0;
        return Integer.parseInt(vacanciesCountBlock.select("a").text().split(" ")[0]);
    }

    private static String parseLink(Element titleBlock) throws WebsiteFormatException {
        if (titleBlock == null)
            throw new WebsiteFormatException();
        return titleBlock.absUrl("href");
    }
}
