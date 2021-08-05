package emailSorter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jdk.internal.jline.internal.TestAccessible;

/**
 * This class provides a suite of UnitTests for the accuracy of my methods.
 */

public class UnitTests {
    @Test
    public void testFilter() {

        Sorter test_one = new Sorter();
        ArrayList<String> input_one = new ArrayList<String>();
        ArrayList<String> correct_one = new Arraylist<String>();
        input_one.add("ethan_wang@berkeley.edu");
        input_one.add(".hello@berkeley.edu");
        input_one.add("...@berkeley.edu");
        input_one.add("tom$berkeley.edu");
        correct_one.add("ethan_wang@berkeley.edu");
        assertEquals(correct_one, test_one.filter(input_one));

    }

    @Test  
    public void testSort() {
        Sorter test_two = new Sorter();
        ArrayList<String> input_two = new ArrayList<String>();
        ArrayList<String> correct_two = new ArrayList<String>();
        // creating input
        input_two.add("ethan_wang@berkeley.edu");
        input_two.add("brian_hu@berkeley.edu");
        input_two.add("andrea_zhang@berkeley.edu");
        input_two.add("ryan_zhao@abc***"); // exclude
        input_two.add("Whut---.@npr.org"); // exclude
        input_two.add("blake_barker@npr.org");
        input_two.add("abigail_mcrea@npr.org");
        input_two.add("tyler@npr.org");
        input_two.add("alex@abc.com");
        input_two.add("adam@abc.com");
        correct_two.add("adam@abc.com");
        correct_two.add("alex@abc.com");
        correct_two.add("andrea_zhang@berkeley.edu");
        correct_two.add("brian_hu@berkeley.edu");
        correct_two.add("ethan_wang@berkeley.edu");
        correct_two.add("abigail_mcrea@npr.org");
        correct_two.add("blake_barker@npr.org");
        correct_two.add("tyler@npr.org");
        assertEquals(correct_two, test_two.sort(input_two));

    }

}
