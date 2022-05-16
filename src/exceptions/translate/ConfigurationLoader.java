package exceptions.translate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Objects;

public class ConfigurationLoader {

    public String loadConfiguration(String configFilePath) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(configFilePath)));
        } catch (NoSuchFileException e) {
            throw new ConfigurationException(e);
        } catch (IOException e) {
            throw new InstallationException(e);
        }
    }
}
