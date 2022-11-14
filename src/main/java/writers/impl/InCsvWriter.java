package writers.impl;

import models.Writable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import writers.InFileWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InCsvWriter implements InFileWriter {
    public static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT;
    private static final Character DELIMITER = ';';
    public static final String NULL_STRING = "";
    public static final QuoteMode QUOTE_MODE = QuoteMode.MINIMAL;
    public static final Logger log = Logger.getLogger(InCsvWriter.class.getName());

    public void write(Collection<Writable> items, Path path) {
        try {
            log.info("connecting to output file");
            FileWriter out = new FileWriter(String.valueOf(path));
            Collection<String> headers = getHeaders(items);

            log.info("building printer");
            if (headers == null) return;
            CSVPrinter printer = buildPrinter(out, headers);

            log.info("writing items");
            for (Writable item : items) {
                printer.printRecord(item.getAttributes().values());
            }
            log.info("written successfully");
        } catch (IOException e) {
            log.log(Level.SEVERE, String.format("writing in file %s failed", path), e);
        }
    }

    private static CSVPrinter buildPrinter(FileWriter out, Collection<String> headers) throws IOException {
        return new CSVPrinter(out, CSV_FORMAT.builder()
                .setDelimiter(DELIMITER)
                .setNullString(NULL_STRING)
                .setQuoteMode(QUOTE_MODE)
                .setHeader(headers.toArray(String[]::new))
                .build());
    }

    private static Collection<String> getHeaders(Collection<Writable> items) {
        Optional<Writable> first = items.stream().findFirst();
        if (first.isEmpty()) {
            log.warning("nothing to write");
            return null;
        }
        return first.get().getAttributes().keySet();
    }
}
