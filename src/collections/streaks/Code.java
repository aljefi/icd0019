package collections.streaks;

import java.util.*;

public class Code {

    public static List<List<String>> getStreakList(String input) {

        List<List<String>> ret = new ArrayList<>();

        if (input.length() == 0) {
            return ret;
        }

        List<String> temp = new ArrayList<>();
        Set<String> withoutDup = new HashSet<>(List.of(input.split("")));

        for (String s : withoutDup) {
            for (Character c :
                    input.toCharArray()) {
                if (c.toString().equals(s)) {
                    temp.add(s);
                }
            }
            ret.add(temp);
            temp = new ArrayList<>();
        }
        return ret;
    }


}
