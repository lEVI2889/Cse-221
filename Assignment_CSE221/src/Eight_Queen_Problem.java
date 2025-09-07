public class Eight_Queen_Problem {
    private static final int N = 8;
    private int[] queens = new int[N];
    private int solutionCount = 0;

    public static void main(String[] args) {
        Eight_Queen_Problem problem = new Eight_Queen_Problem();
        System.out.println("Solving 8-Queens Problem...\n");
        problem.solveQueens(0);
        System.out.println("\nTotal number of solutions: " + problem.solutionCount);
    }

    private boolean solveQueens(int row) {
        if (row == N) {
            solutionCount++;
            System.out.println("Solution " + solutionCount + ":");
            printBoard();
            System.out.println();
            return false; // Continue finding all solutions
        }
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                queens[row] = col;
                solveQueens(row + 1);
            }
        }
        return false;
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || Math.abs(queens[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(queens[i] == j ? "Q " : ". ");
            }
            System.out.println();
        }
    }
}