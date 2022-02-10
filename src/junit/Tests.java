package junit;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@SuppressWarnings("PMD")
public class Tests {

    @Test
    public void equalityExamples() {
        assertTrue(100 == 100);
        assertFalse(1 == 2);
    }

    @Test
    public void assertThatAndAssertEqualsExample() {
        String a = "a";
        String abc = a + "bc";
        assertTrue(abc.equals("abc"));

        assertEquals(3, (1 + 2));
        assertThat(4, is(not(1 + 2)));
        assertThat(new int[]{1, 2, 3}, is(new int[]{1, 2, 3}));
        assertThat(new int[]{1, 2, 3}, is(equalTo(new int[]{1, 2, 3})));
    }

    @Test
    public void findsSpecialNumbers() {
        assertTrue(Code.isSpecial(0));
        assertTrue(Code.isSpecial(1));
        assertTrue(Code.isSpecial(2));
        assertTrue(Code.isSpecial(3));
        assertFalse(Code.isSpecial(4));
        assertTrue(Code.isSpecial(11));
        assertFalse(Code.isSpecial(15));
        assertTrue(Code.isSpecial(36));
        assertFalse(Code.isSpecial(37));
        // other test cases for isSpecial() method
    }

    @Test
    public void findsLongestStreak() {
        assertThat(Code.longestStreak(""), is(0));
        assertThat(Code.longestStreak("a"), is(1));
        assertThat(Code.longestStreak("abbcccaaaad"), is(4));
        assertThat(Code.longestStreak("aaaaa"), is(5));
        assertThat(Code.longestStreak("cccdddaaaaa"), is(5));
        // other test cases for longestStreak() method
    }

    @Test
    public void findsModeFromCharactersInString() {

        assertThat(Code.mode(null), is(nullValue()));
        assertThat(Code.mode(""), is(nullValue()));
        assertThat(Code.mode("abcb"), is('b'));
        assertThat(Code.mode("cbbc"), is('c'));
        // other test cases for mode() method
    }

    @Test
    public void getCharacterCountTest(){
        assertThat(Code.getCharacterCount("", 'a'), is(0));
        assertThat(Code.getCharacterCount("aaa", 'b'), is(0));
        assertThat(Code.getCharacterCount("aaa", 'a'), is(3));
        assertThat(Code.getCharacterCount("abcdd", 'c'), is(1));
        assertThat(Code.getCharacterCount("sssddd", 's'), is(3));
    }

    @Test
    public void removesDuplicates() {
        assertThat(Code.removeDuplicates(arrayOf(123, 1, 2, 3, 3)), is(arrayOf(123, 1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(1, 1)), is(arrayOf(1)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 1, 3, 2)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(1, 2, 3)), is(arrayOf(1, 2, 3)));

        assertThat(Code.removeDuplicates(arrayOf(100, 0, 3, 100, 0, 4, 562, 4)),
                is(arrayOf(100, 0, 3, 4, 562)));
    }

    @Test
    public void sumsIgnoringDuplicates() {
        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 1)), is(1));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 1, 3, 2)), is(6));

        assertThat(Code.sumIgnoringDuplicates(arrayOf(1, 2, 3)), is(6));
    }

    private int[] arrayOf(int... numbers) {
        return numbers;
    }

}
