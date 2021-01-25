import java.util.Comparator;

/**
 * A comparator that evaluates Strings based on their lengths.
 */
public class StringLengthComparator implements Comparator<String> {
    /**
     * Compare two strings according to their lengths.
     * @param a first string to be compared
     * @param b second string to be compared
     * @return -1 if a is longer, 0 if lengths are equal and 1 if b is longer.
     */
    public int compare(String a, String b){
        if (a.length() < b.length()) return -1;
        else if (a.length() == b.length()) return 0;
        else return 1;
    }
}
