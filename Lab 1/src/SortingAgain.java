import java.util.*;

public class SortingAgain {
    static class Student implements Comparable<Student> {
        int id;
        int mark;

        public Student(int id, int mark) {
            this.id = id;
            this.mark = mark;
        }

        @Override
        public int compareTo(Student other) {
            // First compare marks in descending order
            if (this.mark != other.mark) {
                return Integer.compare(other.mark, this.mark);
            }
            // If marks are same, compare IDs in ascending order
            return Integer.compare(this.id, other.id);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of students
        int n = scanner.nextInt();

        // Read student IDs
        int[] ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = scanner.nextInt();
        }

        // Read student marks
        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            marks[i] = scanner.nextInt();
        }

        // Create students array
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student(ids[i], marks[i]);
        }

        // Count minimum swaps
        int swaps = countMinimumSwaps(students);

        // Print results
        System.out.println("Minimum swaps: " + swaps);

        // Print sorted students
        for (Student student : students) {
            System.out.println("ID: " + student.id + " Mark: " + student.mark);
        }

        scanner.close();
    }

    // Method to count minimum swaps using cycle sort
    static int countMinimumSwaps(Student[] students) {
        int swaps = 0;

        // Cycle sort
        for (int i = 0; i < students.length; i++) {
            // Find the correct position for current student
            int correctPos = i;
            for (int j = i + 1; j < students.length; j++) {
                if (students[j].compareTo(students[correctPos]) < 0) {
                    correctPos = j;
                }
            }

            // If correct position is different from current position
            if (correctPos != i) {
                // Swap students
                Student temp = students[i];
                students[i] = students[correctPos];
                students[correctPos] = temp;
                swaps++;

                // Recheck the current position
                i--;
            }
        }

        return swaps;
    }
}