package fr.superprof.hackerRank;

import java.util.HashMap;
import java.util.Map;

public class HackerRank {
    public static String happyLadybugs(String b) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String c : b.split("")) {
            map.putIfAbsent(c, 0);
            map.put(c, map.get(c) + 1);
        }

        boolean isContainBlank = map.remove("_") != null;

        if (map.values().stream().allMatch(v -> v >= 2)) {
            if (!isContainBlank) {
                return checkNextColor(map, b);
            }
            return "YES";
        }
        return "NO";
    }

    private static String checkNextColor(HashMap<String, Integer> map, String b) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int currentIndex = 0;
            for (int i = 0; i < entry.getValue() - 1; i++) {
                currentIndex = b.indexOf(entry.getKey(), currentIndex);
                currentIndex++;
                int nextIndex = b.indexOf(entry.getKey(), currentIndex);
                if (currentIndex != nextIndex) {
                    return "NO";
                }
            }
        }
        return "YES";
    }

    static String highestValuePalindrome(String s, int n, int k) {
        return "";
    }
}
