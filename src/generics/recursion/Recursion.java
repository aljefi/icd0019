package generics.recursion;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recursion {

    public List<String> getParts(Path path) {

        // a)


        Path runner = path;
        List<String> list = new ArrayList<>();

        while (runner != null) {
            list.add(runner.getFileName().toString());
            runner = runner.getParent();
        }

        Collections.reverse(list);

        return list;
    }

    public List<String> getParts2(Path path) {

        // b)

        if (path.getParent() != null) {
            getParts2(path.getParent());
        }
        System.out.println(path.getFileName().toString());

        return null;
    }

    public List<String> getParts3(Path path, List<String> parts) {

        // c)


        if (path.getParent() != null) {
            getParts3(path.getParent(), parts);
        }
        parts.add(path.getFileName().toString());


        return parts;
    }

    public List<String> getParts4(Path path) {

        // d)

        if (path == null) {
            return new ArrayList<>();
        }

        List<String> result = getParts4(path.getParent());

        result.add(path.getFileName().toString());

        return result;
    }
}
