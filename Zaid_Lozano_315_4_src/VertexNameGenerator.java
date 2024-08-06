// Zaid Lozano
// CMSC 315 Project 4
// This class is used to generate unique names for vertices in the graph sequentially.
// It converts integer values to a base-26 string representation to produce names like "A", "B", ..., "Z", "AA", etc.

public class VertexNameGenerator {
    private int counter = 0;  // Start from 0 to make the first vertex "A"

    // Generates the next unique vertex name in sequence
    public String getNextName() {
        return convertToBase26(counter++);
    }

    // Converts an integer to a base-26 string to generate vertex names
    private String convertToBase26(int num) {
        StringBuilder sb = new StringBuilder();
        do {
            int remainder = num % 26;  // Calculate remainder
            sb.append((char) ('A' + remainder));  // Convert remainder to corresponding alphabet character
            num = (num / 26) - 1;  // Adjust for zero-indexing in naming
        } while (num >= 0);
        return sb.reverse().toString();  // Reverse string builder content to get the correct name
    }
}
