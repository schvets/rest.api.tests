package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;
import static java.nio.charset.Charset.defaultCharset;

public class FileHelper {

    public static String readFile(final File file) {
        try {
            return FileUtils.readFileToString(file, String.valueOf(defaultCharset()));
        } catch (final IOException e) {
            throw new IllegalStateException(format("Failed to read file [%s] from [%s]", file, file.getAbsolutePath()), e);
        }
    }
}
