// 22.
// brute force - choose not choose approach to pick either ( or ) and then when string length becomes 2n check if it is balanced or not -> time (2n * 2^2n) -> 2n because 2n chars must be picked 

//time - number of elements in o/p
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>(); //return value
        StringBuilder path = new StringBuilder(); //path string
        
        dfs(result, path, n, 0, 0);
        
        return result;
    }
    
    private void dfs(List<String> result, StringBuilder path, int n, int open, int closed) {
        //System.out.println(path.toString());
        //base
        if(closed > open)
        {
            return; //invalid string
        }
        if(closed == open && path.length() == 2*n)
        {
            result.add(path.toString()); //balanced string
            return;
        }
        //logic
        if(open < n) //add open bracket
        {
            path.append('(');
            dfs(result, path, n, open + 1, closed); //recurse with modified counts
            path.deleteCharAt(path.length() - 1); //backtrack
        }
        if(closed < open) //add a closed bracket
        {
            path.append(')');
            dfs(result, path, n, open, closed + 1); //recurse with modified counts
            path.deleteCharAt(path.length() - 1); //backtrack
        }
        return;
    }
}
