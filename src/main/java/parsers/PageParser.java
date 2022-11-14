package parsers;

import exceptions.WebsiteFormatException;
import models.PageItem;
import org.jsoup.nodes.Document;

import java.util.Collection;

public interface PageParser {
    Collection<PageItem> parse(Document page) throws WebsiteFormatException;
}
