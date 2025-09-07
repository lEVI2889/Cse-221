public class Assignment_23201206 {
    static final int n = 8;
    static int[] queens = new int[n];
    static int solutions_count = 0;

    public static void main(String[] args){
        Assignment_23201206 obj = new Assignment_23201206();
        System.out.println("------------Solving 8-Queens Problem------------\n");
        obj.solve(0);
    }

    static boolean solve(int row) {
        if(row == n) {
            solutions_count++;
            System.out.println("Solution " + solutions_count + ":");
            printBoard();
            System.out.println();
            return false;
        }
        for(int col = 0; col < n; col++) {
            if(isSafe(row, col)){
                queens[row] = col;
                solve(row + 1);
            }
        }
        return false;
    }

    static void printBoard() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(queens[i] == j ? "Q " : ". ");
            }
            System.out.println();
        }
    }

    static boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if(queens[i]==col || Math.abs(queens[i]-col)==Math.abs(i-row)){
                return false;
            }
        }
        return true;
    }
}
