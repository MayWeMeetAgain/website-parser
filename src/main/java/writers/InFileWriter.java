package writers;

import models.Writable;

import java.nio.file.Path;
import java.util.Collection;

public interface InFileWriter {
    void write(Collection<Writable> items, Path path);
}
