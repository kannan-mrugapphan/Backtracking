// 51.
// brute force - place queen in each row initially and then check for valid placement to get to time O(n^n)
// time - O(n!)
//space - O(n)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>(); //return list
        //edge
        if(n <= 0)
        {
            return result;
        }
        
        char[][] board = new char[n][n]; //chess board
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < n; j++)
            {
                board[i][j] = '.'; //initially filling every cell with '.'
            }
        }
        
        backtrack(board, n, 0, result); //populates result
        return result;
    }
    
    private void backtrack(char[][] board, int n, int currentRow, List<List<String>> result) {
        //base
        if(n == 0)
        {
            //all queens are placed
            //add to result
            addResult(board, result);
            return;
        }
        //logic
        for(int i = 0; i < board[0].length; i++)
        {
            //for each column in current row, place queen only if it is safe
            if(isSafe(board, currentRow, i))
            {
                //act
                board[currentRow][i] = 'Q'; //place queen in cell currentRow,i 
                backtrack(board, n - 1, currentRow + 1, result); //recurse to place queens in subsequent rows
                board[currentRow][i] = '.'; //backtrack to explore other possiblities
            }
        }
        return;
    }
    
    //time - O(n)
    private boolean isSafe(char[][] board, int currentRow, int currentColumn) {
        //check columns
        for(int i = 0; i < currentRow; i++)
        {
            //for all rows from 0 to current row, check if a queen is placed in current column
            if(board[i][currentColumn] == 'Q')
            {
                return false;
            }
        }
        
        //left diagonal
        int i = currentRow - 1;
        int j = currentColumn - 1;
        while(i >= 0 && j >= 0)
        {
            if(board[i][j] == 'Q')
            {
                return false;
            }
            i--;
            j--;
        }
        
        //right diagonal
        i = currentRow - 1;
        j = currentColumn + 1;
        while(i >= 0 && j < board[0].length)
        {
            if(board[i][j] == 'Q')
            {
                return false;
            }
            i--;
            j++;
        }
        
        return true;
    }
    
    //helper to populate result
    private void addResult(char[][] board, List<List<String>> result) {
        //each valid placement should be in result list as list of rows in board
        List<String> solution = new ArrayList<>();
        for(int i = 0; i < board.length; i++)
        {
            String row = "";
            for(int j = 0; j < board[0].length; j++)
            {
                row += board[i][j];
            }
            solution.add(row);
        }
        result.add(solution);
        return;
    }
}
