package parsers;

import models.PageItem;
import org.jsoup.nodes.Element;

public interface PageItemParser {
    PageItem parsePageItem(Element block, String pageUrl) throws IllegalArgumentException;
}
