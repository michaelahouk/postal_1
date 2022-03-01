package houk.postal.wikipedia;

import junit.framework.TestCase;

/**
 * Unit tests for the Wikipedia fetching application
 */
public class AppTest
    extends TestCase
{

    public void testFetchWikipedia() {
        App.main(null);
    }

    public void testCountOccurrences_isValid() {
        testCountOccurrences_helper("test test test test Test", "test", 4);
        testCountOccurrences_helper("", "string", 0);
        testCountOccurrences_helper("Test test Test test test", "test", 3);
    }

    private void testCountOccurrences_helper(String source, String search, int occurrences)
    {
        assertEquals(occurrences, App.countOccurrences(source, search));
    }
}
