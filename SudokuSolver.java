// 37.
//timw - O(mn!) -> worst case, whole board is empty -> 1st cell can take 9 options, 2nd 8options and so on
class Solution {
    public void solveSudoku(char[][] board) {
        //edge
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return;
        }
        solve(board); //solves the board
        return;
    }
    
    private boolean solve(char[][] board) {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(board[i][j] == '.') //for each cell, if current cell is empty
                {
                    for(char c = '1'; c <= '9'; c++) //try to place all chars from 1 to 9 in the empty cell
                    {
                        if(isValid(board, i, j, c)) //if placing c in i,j is valid
                        {
                            board[i][j] = c;
                            if(solve(board)) //recurse
                            {
                                return true;
                            }
                            else
                            {
                                board[i][j] = '.'; //backtrack
                            }
                        }
                    }
                    return false; //for current empty cell no char placement is valid
                }
            }
        }
        return true; //solution for the current config of board found
    }
    
    //checks if placing c in row,col violates rules
    private boolean isValid(char[][] board, int row, int col, char c) {
        int normalizedRow = (row / 3) * 3; //start row and col of the current sub grid
        int normalizedCol = (col / 3) * 3;
        //board is a square matrix of size 9 always
        for(int i = 0; i < board[0].length; i++)
        {
            //row
            //check if any col in this row has c
            if(board[row][i] == c)
            {
                return false;
            }
            //col
            //check if any row in this col has c
            if(board[i][col] == c)
            {
                return false;
            }
            //sub grid
            if(board[normalizedRow + (i / 3)][normalizedCol + (i % 3)] == c)
            {
                return false;
            }
        }
        return true;
    }
}
