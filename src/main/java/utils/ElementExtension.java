package utils;

import exceptions.WebsiteFormatException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.enums.CompanyOptionCssClass;

public class ElementExtension {

    public static boolean ifExist(Element companyBlock, CompanyOptionCssClass cssClass) {
        Elements elements = companyBlock.getElementsByClass(cssClass.toString());
        return !elements.isEmpty();
    }

    public static Element getElementByClassName(Element companyBlock, CompanyOptionCssClass cssClass) throws WebsiteFormatException {
        Elements elements = companyBlock.getElementsByClass(cssClass.toString());
        if (elements.size() > 1)
            throw new WebsiteFormatException();
        return elements.isEmpty() ? null : elements.get(0);
    }
}
