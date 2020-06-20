// 282.
class Solution {
    public List<String> addOperators(String num, int target) {
        //edge
        if(num == null || num.length() == 0)
        {
            return new ArrayList<>(); //empty list
        }
        
        List<String> result = new ArrayList<>(); //return list
        String path = ""; //initial root when dfs is called
        dfs(num, 0, target, path, result, 0, 0); //populates result
        return result;
    }
    
    //time - O(4^n) - 4 choices at each element 
    //space - O(n) - max call stack size
    private void dfs(String num, int index, int target, String path, List<String> result, long calcValue, long tail) {
        //base
        if(index == num.length())
        {
            if(calcValue == target)
            {
                result.add(new String(path));
            }
            return;
        }
        //logic
        for(int i = index; i < num.length(); i++)
        {
            //eg i/p - num = 123 => 1 && dfs(23), 12 && dfs(3), 123 && dfs(),...
            String currentSnippet = num.substring(index, i + 1); //current snippet
            //there could be snippet like "05", when such snippet is parsed its int value is 5 and 0 is ignored, such a path is invalid
            if(currentSnippet.charAt(0) == '0' && currentSnippet.length() >= 2)
            {
                continue;
            }
            Long current = Long.parseLong(currentSnippet); //int value of current snippet
            
            if(index == 0)
            {
                //at the 1st level of tree, calc value and tail = currentSnippet
                dfs(num, i + 1, target, path + currentSnippet, result, current, current); 
            }
            else
            {
                //update path, calculated value and tail based on operator
                //'+' -> calculated value += current, tail = current
                //'-' -> calculated value -= current, tail = -current
                //'*' -> calculated value = calculated value - tail + tail * current, tail = tail * current
                
                dfs(num, i + 1, target, path + "+" + currentSnippet, result, calcValue + current, current);
                dfs(num, i + 1, target, path + "-" + currentSnippet, result, calcValue - current, -current);
                dfs(num, i + 1, target, path + "*" + currentSnippet, result, calcValue - tail + tail * current, tail * current);
            }
        }
    }
}
