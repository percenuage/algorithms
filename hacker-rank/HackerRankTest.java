package fr.superprof.hackerRank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HackerRankTest {

    @Test
    void highestValuePalindrome() {
        assertAll("Palindrome",
                () -> assertEquals("3993", HackerRank.highestValuePalindrome("3943", 4, 1)),
                () -> assertEquals("992299", HackerRank.highestValuePalindrome("932239", 6, 2)),
                () -> assertEquals("9999", HackerRank.highestValuePalindrome("3943", 4, 4))
        );
    }

    @Test
    void happyLadybugs() {
        assertAll("LadyBugs",
                () -> assertEquals("YES", HackerRank.happyLadybugs("_"), "'_'"),
                () -> assertEquals("NO", HackerRank.happyLadybugs("RBRB"), "'RBRB'"),
                () -> assertEquals("YES", HackerRank.happyLadybugs("RRRR"), "'RRRR'"),
                () -> assertEquals("YES", HackerRank.happyLadybugs("BBB"), "'BBB'"),
                () -> assertEquals("YES", HackerRank.happyLadybugs("BBB_"), "'BBB_'"),
                () -> assertEquals("NO", HackerRank.happyLadybugs("G"), "'G'"),
                () -> assertEquals("NO", HackerRank.happyLadybugs("GR"), "'GR'"),
                () -> assertEquals("NO", HackerRank.happyLadybugs("_GR_"), "'_GR_'"),
                () -> assertEquals("NO", HackerRank.happyLadybugs("_R_G_"), "'_R_G_'"),
                () -> assertEquals("YES", HackerRank.happyLadybugs("R_R_R"), "'R_R_R'"),
                () -> assertEquals("YES", HackerRank.happyLadybugs("RRGGBBXX"), "'RRGGBBXX'"),
                () -> assertEquals("NO", HackerRank.happyLadybugs("RRGGBBXY"), "'RRGGBBXY'"),
                () -> assertEquals("YES", HackerRank.happyLadybugs("BBBXX"), "'BBBXX'")
        );
    }
}