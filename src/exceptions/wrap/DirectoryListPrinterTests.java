package exceptions.wrap;

import org.junit.Test;

import java.io.IOException;

public class DirectoryListPrinterTests {

    @Test
    public void readingThrowsExample() throws IOException {
        new DirectoryListPrinter().printDirectoryList();
    }

}
