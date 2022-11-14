package services;

import models.PageItem;

import java.io.IOException;
import java.util.Collection;

public interface WebsiteService {
    Collection<PageItem> getItems(String baseUrl, String firstPageLink);
}
