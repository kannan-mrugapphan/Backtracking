// 473.
// time - O(4 ^ n) - 4 possiblities for each stick
class Solution {
    public boolean makesquare(int[] matchsticks) {
        
        int sideLength = 0; //side length of potential square
        for(int num : matchsticks)
        {
            sideLength += num; //running sum
        }
        if(sideLength % 4 != 0)
        {
            return false; //impossible to build a square using all matchsticks
        }
        sideLength = sideLength / 4; //actual side length
        
        int[] sides = new int[4]; //a path array to fit matchsticks
        Arrays.fill(sides, sideLength);
        return dfs(matchsticks, sides, 0, sideLength);
    }
    
    private boolean dfs(int[] matchsticks, int[] sides, int index, int sideLength) {
        //base
        if(index == matchsticks.length) //all sticks consumed
        {
            if(sides[0] == 0 && sides[1] == 0 && sides[2] == 0 && sides[3] == 0) //expected value
            {
                return true;
            }
            return false;
        }
        //logic
        //for each side try to place current match stick
        for(int i = 0; i < 4; i++)
        {
            if(matchsticks[index] <= sides[i]) //current stick can be placed
            {
                sides[i] -= matchsticks[index]; //place current in ith side
                if(dfs(matchsticks, sides, index + 1, sideLength)) //recurse
                {
                    return true; //possible to build square
                }
                sides[i] += matchsticks[index]; //backtrack
            }
        }
        return false; //all possiblities explored
    }
}
