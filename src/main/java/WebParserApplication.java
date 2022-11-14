import models.PageItem;
import models.Writable;
import services.WebsiteService;
import services.impl.CompanyWebsiteService;
import writers.InFileWriter;
import writers.impl.InCsvWriter;

import java.nio.file.Path;
import java.util.Collection;

public class WebParserApplication {

    public static void main(String[] args) {
        String baseUrl = "https://career.habr.com";
        String firstPageLink = "/companies?skills%5B%5D=1012&with_ratings=1";
        Path filePath = Path.of("src/main/resources/parsingResults/companies.csv");

        Collection<? super PageItem> companies;
        WebsiteService companyWebsiteService = new CompanyWebsiteService();
        InFileWriter inFileWriter = new InCsvWriter();

        companies = companyWebsiteService.getItems(baseUrl, firstPageLink);
        inFileWriter.write((Collection<Writable>) companies, filePath);
    }
}
