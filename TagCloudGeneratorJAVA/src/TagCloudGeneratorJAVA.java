import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Program that takes in an input file, counts the amount of times each word
 * occurs, and outputs the most frequent "n" words in a Cloud Tag using all Java
 * components
 *
 * @author Blake Brady, Tomasz Frelek
 */

public final class TagCloudGeneratorJAVA {

    /**
     * Comparator<Map.Entry<String, Integer> implementation to be used sorting.
     * Compare {@code Map.Entry.value()}s in lexicographic order.
     */
    private static class comp
            implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> m1,
                Map.Entry<String, Integer> m2) {
            if (m2.getValue().compareTo(m1.getValue()) == 0) {

                /*
                 * Returns -1 (or m2) if the values are the same
                 */
                return -1;
            }
            return m2.getValue().compareTo(m1.getValue());
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        String toReturn = "";
        char tempChar = ' ';

        /*
         * Constant for the length of text from index position to the end
         */
        int textLength = text.substring(position, text.length()).length();

        /*
         * Loop that will iterate over the length of text, starting from
         * position and ending at the last letter
         */

        if (!separators.contains(text.charAt(position))) {

            /*
             * If first letter is not in separators set, while loop iterates
             * until string ends.
             */

            int i = 0;
            while (i < textLength
                    && (!separators.contains(text.charAt(position + i)))) {
                tempChar = text.charAt(position + i);
                toReturn = toReturn + tempChar;
                i++;

            }
        } else {

            /*
             * If first letter is in separators set, adding each separator in
             */
            int i = 0;
            while (i < textLength
                    && separators.contains(text.charAt(position + i))) {
                tempChar = text.charAt(position + i);
                toReturn = toReturn + tempChar;
                i++;

            }
        }

        return toReturn;
    }

    /**
     * Returns a map with all of the input file's words and the amount of times
     * each word occurs
     *
     * @param fileName
     *            The file to be analyzed
     * @returns a Map with all words and their respective occurrences
     * @requires fileName != ""
     * @ensures the returned Map will contain all of the words in the file and
     *          the amount of times each word occurs
     */
    public static Map<String, Integer> wordList(BufferedReader input) {

        /*
         * Initializing map to store words and amount of times seen
         */
        Map<String, Integer> words = new HashMap<>();

        /*
         * Initializing a list of separators to use with the NextWordOrSeparator
         * method
         */
        Set<Character> separators = new HashSet<>();
        separators.add('\t');
        separators.add('\n');
        separators.add('\r');
        separators.add('-');
        separators.add('!');
        separators.add('?');
        separators.add('[');
        separators.add(']');
        separators.add('\'');
        separators.add(';');
        separators.add(':');
        separators.add('/');
        separators.add('(');
        separators.add(')');
        separators.add('*');
        separators.add(' ');
        separators.add('_');

        /*
         * Initializing counter value of 1 for when an unseen word is added
         * (CheckStyle prevention)
         */
        int initialCounter = 1;

        /*
         * Try/catch to read into Map
         */
        try {

            /*
             * Reading in the first line
             */
            String nextLine = input.readLine();
            while (nextLine != null) {

                /*
                 * For loop that iterates through the current line and pulls
                 * each word/separator
                 */
                for (int i = 0; i < nextLine.length(); i++) {

                    /*
                     * String that stores the next word or separator
                     */
                    String nextWord = nextWordOrSeparator(nextLine, i,
                            separators);

                    /*
                     * Did this for additional activity 2, where you ignore
                     * uppercase/lowercase and count it all as the same word
                     */
                    nextWord = nextWord.toLowerCase();
                    /*
                     * Removing any commas, periods, and underscores in the
                     * word. i is increased by one because if not, there will be
                     * an infinite loop due to the period being removed and the
                     * index will not be correct for the nextWordOrSeparator
                     * method
                     */
                    if (nextWord.contains(".")) {
                        nextWord = nextWord.substring(0, nextWord.indexOf('.'));
                        i += 1;
                    }
                    if (nextWord.contains(",")) {
                        nextWord = nextWord.substring(0, nextWord.indexOf(','));
                        i += 1;
                    }
                    if (nextWord.contains("_")) {
                        nextWord = nextWord.substring(0, nextWord.indexOf('_'));
                        i += 1;

                    }

                    /*
                     * Increasing the i value to its current value + the length
                     * of the previous word
                     */
                    i += nextWord.length() - 1;

                    /*
                     * if the words map has the key and the key is not a
                     * space/another separator, the current counter value is
                     * increased by 1
                     */
                    if (words.containsKey(nextWord)
                            && !(separators.contains(nextWord.charAt(0)))) {
                        words.replace(nextWord, words.get(nextWord) + 1);
                    }

                    /*
                     * If the words map does not have the key and it is not a
                     * separator, the word is added with a value of 1
                     */
                    else if (!nextWord.equals("")
                            && (!separators.contains(nextWord.charAt(0)))) {
                        words.put(nextWord, initialCounter);
                    }

                }

                /*
                 * Reading in new line for while loop check
                 */
                nextLine = input.readLine();
            }

            /*
             * Catch block
             */
        } catch (IOException e) {
            System.err.println("Error reading from file");
        }

        /*
         * Returning the words Map with the proper words and word occurrences
         */
        return words;

    }

    /**
     * Returns a map with the input file's reduced words and the amount of times
     * each word occurs according to the value of n. Organized from List
     *
     * @param wordList
     *            A map with all the words and their counts
     * @param n
     *            The amount of words to output, as specified my user
     * @returns a Map<String, Integer> with the top n occuring words
     * @requires wordList is not null
     * @ensures the returned Map will contain all of the words in the file and
     *          the amount of times each word occurs
     */
    public static Map<String, Integer> wordListOrganizer(
            Map<String, Integer> wordList, int n) {
        assert wordList != null : "Violation of: wordList is not null";

        /*
         * Initializing map to store reduced list of words and amount of times
         * seen, along with tempMap to store entiries from iterator. Also
         * initializing int num for the amount of words to have total
         */
        Map<String, Integer> reducedMap = new HashMap<>();
        int num = n;

        /*
         * Creating iterator over the map and a ordered TreeSet to sort the
         * values
         */
        Iterator<Map.Entry<String, Integer>> itr = wordList.entrySet()
                .iterator();

        Set<Map.Entry<String, Integer>> orderedSet = new TreeSet<>(new comp());

        /*
         * Adding all wordList entries into the ordered treeSet
         */

        while (itr.hasNext()) {
            Map.Entry<String, Integer> temp = itr.next();
            orderedSet.add(temp);
        }

        /*
         * Now that the treeset has all Entires ordered by value, we can extract
         * the first "n" into a HashMap through an iterator
         */

        Iterator<Map.Entry<String, Integer>> itr2 = orderedSet.iterator();

        while (num > 0 && orderedSet.size() != 0) {

            Map.Entry<String, Integer> temp = itr2.next();
            reducedMap.put(temp.getKey(), temp.getValue());
            num--;

        }
        return reducedMap;

    }

    /**
     * Returns a alphabetized List of the terms from the Map of terms
     *
     * @param countedMap
     *            Map<String, Integer> of the "n" most occuring terms
     * @param order
     *            String comparator
     * @returns Sequence of alphabetized terms
     * @clears terms
     * @requires terms.size != 0
     * @ensures a Sequence is returned of the terms in alphabetical order
     */
    private static List<String> orderedWords(Map<String, Integer> countedMap) {
        assert countedMap != null : "Violation of: countedMap is not null";

        /*
         * Initializing list and iterator for countedMap
         */
        List<String> names = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> entry = countedMap.entrySet()
                .iterator();

        /*
         * While loop to add all keys from countedMap into the names list
         */
        while (entry.hasNext()) {
            Map.Entry<String, Integer> temp = entry.next();
            names.add(temp.getKey());
        }

        /*
         * Sorting the names list and returning it
         */
        Collections.sort(names);
        return names;

    }

    /*
     * Returns the amount of times the lowest appearing word shows up in the map
     *
     * @param finalMap the {@code Map} of "n" words
     *
     * @returns the amount of times the highest appearing words appears </pre>
     */
    private static int minimumValue(Map<String, Integer> finalMap) {

        /*
         * Same concept as the alphabetizer but with the values instead
         */
        List<Integer> values = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> entry = finalMap.entrySet()
                .iterator();

        while (entry.hasNext()) {
            Map.Entry<String, Integer> temp = entry.next();
            values.add(temp.getValue());
        }
        Collections.sort(values);
        return values.remove(0);
    }

    /**
     * Returns the amount of times the highest appearing word shows up in the
     * map
     *
     * @param finalMap
     *            the {@code Map} of "n" words
     * @returns the amount of times the highest appearing words appears </pre>
     */
    private static int maximumValue(Map<String, Integer> finalMap) {

        /*
         * Same as above
         */
        List<Integer> values = new ArrayList<>();
        Iterator<Map.Entry<String, Integer>> entry = finalMap.entrySet()
                .iterator();

        while (entry.hasNext()) {
            Map.Entry<String, Integer> temp = entry.next();
            values.add(temp.getValue());
        }
        Collections.sort(values);
        return values.remove(values.size() - 1);
    }

    /**
     * Returns the size the word should be
     *
     * @param max
     *            max times a word appears
     * @param min
     *            min times a word appears
     * @param appearances
     *            specific word appearances to get a value for
     * @returns the proper font size to use </pre>
     */
    private static int wordSize(int max, int min, int appearances) {

        /*
         * Constants for the highest and lowest f values from the project
         * description for checkstyle prevention
         */
        final int maxValue = 48;
        final int minValue = 11;

        /*
         * Percentage double comes from taking the ratio of the total range of
         * word counts over the range of font sizes. This provides a scaling
         * value that is used in the following calculation
         *
         * Double is casted to double here because with small values, the value
         * would be 0.0 because of integer division. By subtracting min from
         * appearances, that gives the total counts for that word over the
         * miminum word count. From here, the percentage scaling value is used
         * to scale the word count into a range within the max and min font
         * sizes, and then the minValue is added on
         */

        double percentage = (double) (max - min) / (maxValue - minValue);

        int size = (int) ((appearances - min) / percentage);

        return size + minValue;

    }

    /**
     * Outputs the HTML file with the given terms and their occurrences
     *
     * @param counts
     *            Map<String, Integer> of all terms and their respective
     *            occurrences
     * @param ordered
     *            Sequence<String> of the terms alphabetized
     * @param outputFile
     *            Name of the output file
     * @param inputFile
     *            Name of the input file
     * @param n
     *            Number of words to output
     * @param max
     *            Max number of word appearances
     * @param min
     *            Min number of word appearances
     *
     * @requires counts.size() != 0 && ordered.length() != 0
     * @clears ordered
     * @ensures a properly formatted HTML page is returned with the given terms
     *          and their occurrences
     */
    private static void outputPage(List<String> alphabetized,
            Map<String, Integer> counts, PrintWriter out, String inputFile,
            int n, int max, int min) {

        /*
         * Outputting HTML headers
         */
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Top " + n + " words in " + inputFile + "</title>");

        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-"
                        + "sw2/assignments/projects/tag-cloud-generator/data/tagcloud."
                        + "css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("<link href=\"tagcloud.css\" rel=\"stylesheet\" type="
                + "\"text/css\">");

        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Top " + n + " words in " + inputFile + "</h2>");
        out.println("<hr />");
        out.println("<div class=\"cdiv\">");
        out.println("<p class=\"cbox\">");

        /*
         * Loop to output each word with proper html syntax
         */
        while (alphabetized.size() != 0) {
            /*
             * Grabbing next term and then its associated occurrence count, then
             * calling wordSize method to give f value
             */
            String term = alphabetized.remove(0);
            int appearances = counts.get(term);
            String appearancesString = String.valueOf(appearances);
            int wordSize = wordSize(max, min, appearances);
            /*
             * Outputting HTML
             */
            out.println("<span style=\"cursor:default\" class=\"f" + wordSize
                    + "\" title=\"count: " + appearancesString + "\">" + term
                    + "</span>");
        }

        /*
         * Closing tags
         */
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        /*
         * Closing the output stream
         */
        out.close();

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {

        BufferedReader input = new BufferedReader(
                new InputStreamReader(System.in));

        /*
         * Asking the user for input and output file along with the amount of
         * words to take with a try/catch to cover the IOexceptions
         */

        System.out.print("Enter the name of the input file: ");
        String inputFileName;
        try {
            inputFileName = input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading string");
            return;
        }

        System.out.print("Enter the name of the output file: ");
        String outputFileName;
        try {
            outputFileName = input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading string");
            return;
        }

        System.out.print("Enter the amount of words to list: ");
        String num;
        try {
            num = input.readLine();
        } catch (IOException e) {
            System.err.println("Error reading number of words");
            return;
        }

        int n = Integer.parseInt(num);

        /*
         * Try/catch to open the input file
         */
        BufferedReader inputFile;
        try {
            inputFile = new BufferedReader(new FileReader(inputFileName));
        } catch (IOException e) {
            System.err.println("Error opening input file");
            return;
        }

        /*
         * Calling the wordList method to create a map of all new words and
         * their respective amount of occurances
         */
        Map<String, Integer> wordCounter = wordList(inputFile);

        /*
         * Calling the wordListorganizer method to organize all words based on
         * their counts in a SortingMachine. This Map has the top "N" occuring
         * words
         */
        Map<String, Integer> finalCounter = wordListOrganizer(wordCounter, n);

        /*
         * Using SortingMachines to find the highest appearing and lowest
         * appearing value to assist with calculating font size
         */

        int max = maximumValue(finalCounter);
        int min = minimumValue(finalCounter);

        /*
         * Creating a set of all the keys in the map in alphabetical order
         *
         */

        List<String> wordsInOrder = orderedWords(finalCounter);

        /*
         * Try/catch for the output file
         */
        PrintWriter output;
        try {
            output = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFileName)));
        } catch (IOException e) {
            System.err.println("Error opening output file");
            return;

        }

        /*
         * Outputting the html page using the names of the input and output
         * files, the alphabetized sequence, and the map containing all the
         * words and their occurrence count
         */

        outputPage(wordsInOrder, finalCounter, output, inputFileName, n, max,
                min);

        /*
         * Closing input and output files with try/catch
         */
        try {
            output.close();
            input.close();
            inputFile.close();
        } catch (IOException e) {
            System.err.println("Error closing file");
        }

    }

}
