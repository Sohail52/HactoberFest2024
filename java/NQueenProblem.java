/* Java program to solve N Queen Problem using backtracking */
public class NQueenProblem {
    // N represents the size of the chessboard (NxN)
    final int N = 4;

    /* A utility function to print the solution (i.e., the chessboard configuration)
       where queens are placed. '1' represents a queen, '0' represents an empty cell. */
    void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
    }

    /* This function checks if a queen can be safely placed on board[row][col].
       It only checks the left side (columns 0 to col - 1), because queens are placed
       from left to right, one in each column. */
    boolean isSafe(int board[][], int row, int col) {
        int i, j;

        /* Check the row on the left side to ensure no queen exists in the same row */
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        /* Check the upper diagonal on the left side for any queens */
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        /* Check the lower diagonal on the left side for any queens */
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        // If no queens are found in the same row, upper diagonal, or lower diagonal, it's safe
        return true;
    }

    /* A recursive utility function to solve the N Queen problem.
       It tries to place queens column by column starting from column 0. */
    boolean solveNQUtil(int board[][], int col) {
        // Base case: If all queens are placed, return true
        if (col >= N)
            return true;

        /* Try placing the queen in each row of the current column (one by one). */
        for (int i = 0; i < N; i++) {
            // Check if placing a queen at board[i][col] is safe
            if (isSafe(board, i, col)) {
                // Place the queen at board[i][col]
                board[i][col] = 1;

                /* Recur to place the rest of the queens */
                if (solveNQUtil(board, col + 1))
                    return true;

                /* If placing the queen in board[i][col] doesn't lead to a solution,
                   backtrack by removing the queen (reset to 0) and try the next row */
                board[i][col] = 0; // BACKTRACK
            }
        }

        // If the queen can't be placed in any row in this column, return false
        return false;
    }

    /* This function initiates solving the N Queen problem using backtracking.
       It returns false if no solution exists. Otherwise, it prints one solution. */
    boolean solveNQ() {
        // Initialize a 4x4 chessboard with all cells set to 0 (no queens placed)
        int board[][] = { { 0, 0, 0, 0 },
                          { 0, 0, 0, 0 },
                          { 0, 0, 0, 0 },
                          { 0, 0, 0, 0 } };

        // If no solution exists, print a message and return false
        if (!solveNQUtil(board, 0)) {
            System.out.print("Solution does not exist");
            return false;
        }

        // If a solution is found, print the solution (board configuration) and return true
        printSolution(board);
        return true;
    }

    // Driver program to test the above function
    public static void main(String args[]) {
        NQueenProblem Queen = new NQueenProblem();
        Queen.solveNQ(); // Solve the N Queen problem and display the result
    }
}
