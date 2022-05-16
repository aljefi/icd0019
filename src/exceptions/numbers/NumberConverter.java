package exceptions.numbers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.ResourceBundle;

public class NumberConverter {
    private final String filePath;

    public NumberConverter(String lang) {
        switch (lang) {
            case "cu" -> filePath = "src/exceptions/numbers/numbers_cu.properties";
            case "en" -> filePath = "src/exceptions/numbers/numbers_en.properties";
            case "es" -> filePath = "src/exceptions/numbers/numbers_es.properties";
            case "et" -> filePath = "src/exceptions/numbers/numbers_et.properties";
            case "fr" -> filePath = "src/exceptions/numbers/numbers_fr.properties";
            default -> filePath = null;
        }
        FileInputStream is = null;
        try {
            ResourceBundle.getBundle("1");

            assert filePath != null;
            is = new FileInputStream(filePath);

        } catch (Exception e) {
            switch (lang) {
                case "ru" -> throw new MissingLanguageFileException(lang,
                        new RuntimeException("not implemented yet"));
                case "es" -> throw new MissingTranslationException(lang);
                case "fr" -> throw new BrokenLanguageFileException(lang,
                        new RuntimeException("not implemented yet"));
            }
        } finally {
            close(is);
        }
    }

    private static void close(FileInputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException ignore) {
        }
    }

    public String numberInWords(Integer number) {

        String ret = "";
        Properties properties = new Properties();

        FileInputStream is = null;
        try {
            is = new FileInputStream(filePath);

            InputStreamReader reader = new InputStreamReader(
                    is, StandardCharsets.UTF_8);

            properties.load(reader);
        } catch (Exception e) {
            // handle exceptions
        } finally {
            close(is);
        }
        if (!properties.containsKey(String.valueOf(number))) {
            int a, b, c, bAndC;
            a = number / 100;
            b = ((number - a * 100) / 10) * 10;
            c = number % 10;
            bAndC = b + c;

            // a
            if (a > 0) {
                ret += properties.getProperty(String.valueOf(a)) +
                        properties.getProperty("hundreds-before-delimiter") +
                        properties.getProperty("hundred");
                if (number == a * 100) {
                    return ret;
                } else {
                    ret += properties.getProperty("hundreds-after-delimiter");
                }
            }

            if (!properties.containsKey(String.valueOf(bAndC)) && 10 < bAndC && bAndC < 20) {
                return ret + (properties.getProperty(String.valueOf(c))
                        + properties.getProperty("teen"));
            } else if (properties.containsKey(String.valueOf(bAndC))) {
                return ret + properties.getProperty(String.valueOf(bAndC));
            }

            // b
            else if (b > 0) {
                if (properties.containsKey(String.valueOf(b))) {
                    ret += properties.getProperty(String.valueOf(b));
                } else {
                    int bc = b / 10;
                    ret += properties.getProperty(String.valueOf(bc));
                    ret += properties.getProperty("tens-suffix");
                }
            }

            // c
            if (c != 0) {
                if (b != 0) {
                    ret += properties.getProperty("tens-after-delimiter");
                }
                ret += properties.getProperty(String.valueOf(c));
            }
            return ret;
        }
        return properties.getProperty(String.valueOf(number));
    }
}