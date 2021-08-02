// 488.
class Solution {
    public int findMinStep(String board, String hand){
        
        //converting to map as balls can be inserted in any order
        HashMap<Character, Integer> freq = new HashMap<>(); //for hand
        for(int i = 0; i < hand.length(); i++)
        {
            Character current = hand.charAt(i);
            freq.put(current, freq.getOrDefault(current, 0) + 1); //update map
        }
        HashMap<String, Integer> cache = new HashMap<>();
        
        int result = dfs(board, freq, cache);
        if(result == Integer.MAX_VALUE)
        {
            return -1; //not possible to win
        }
        return result; //min moves needed
    }
    
    //time - O(length of hand ^ length of board) length of hand choices for each index in board
    private int dfs(String board, HashMap<Character, Integer> freq, HashMap<String, Integer> cache) {
        //base
        if(cache.containsKey(board))
        {
            return cache.get(board);
        }
        if(board.length() == 0)
        {
            return 0; //no insertions of new balls needed
        }
        
        //logic
        StringBuilder boardEq = new StringBuilder(board); //string builder eq
        int insertionsNeeded = Integer.MAX_VALUE; //return ans
        
        for(int i = 0; i < boardEq.length(); i++)
        {
            for(Character ball : freq.keySet()) //for each available ball
            {
                if(freq.get(ball) > 0) //current ball is available
                {
                    boardEq.insert(i, ball); //insert ball at ith index
                    freq.put(ball, freq.get(ball) - 1); //update freq map
                    
                    String newBoard = updateBoard(boardEq.toString()); //update board;
                    int insertionsNeededForNewBoard = dfs(newBoard, freq, cache); //recurse
                    if(insertionsNeededForNewBoard != Integer.MAX_VALUE)
                    {
                        insertionsNeeded = Math.min(insertionsNeeded, 1 + insertionsNeededForNewBoard); //update global min
                    }
                    
                    boardEq.deleteCharAt(i); //bacaktrack on baord
                    freq.put(ball, freq.get(ball) + 1); //backtrack on map
                }
            }
        }
        
        if(insertionsNeeded == Integer.MAX_VALUE)
        {
            cache.put(board, insertionsNeeded);
        }
        
        return insertionsNeeded;
    }
    
    //checks whole board for 3 or more adjacent balls with same color and removes them till no further removols are possible
    private String updateBoard(String board) {
        int start = 0;
        int end = 0;
        
        while(end < board.length())
        {
            //adjacent balls are same color
            while(end < board.length() && board.charAt(end) == board.charAt(start))
            {
                end++;
            }
            //all balls from start till end - 1 are of smae color -> remove them is size of substring >= 3
            if(end - start >= 3)
            {
                String updatedBoard = board.substring(0, start) + board.substring(end);
                return updateBoard(updatedBoard);
            }
            else
            {
                start = end;
            }
        }
        
        return board;
    }
}
