// 131.

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>(); //return list
        //edge
        if(s == null || s.length() == 0)
        {
            return result; //return empty list
        }
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    //time - 
    // T(n) = T(n-1) + T(n-2) + ... + T(1) with n work at each call to check for palindrom
    // thus O(n * 2^n)
    //space - O(n) - max call stack size
    private void backtrack(String s, int index, List<String> path, List<List<String>> result) {
        
        //base
        if(index == s.length())
        {
            result.add(new ArrayList<>(path));
            return;
        }
        //logic
        // for s = aab - possible partitions - a & recurse(ab), aa & recurse(b), aab & recurse()
        for(int i = index; i < s.length(); i++)
        {
            String snippet = s.substring(index, i + 1); //process snippets
            if(isPalindrome(snippet))
            {
                //if snippet is a palidrome
                path.add(snippet); //add to path indicating a valid partiton
                //recurse
                backtrack(s, i + 1, path, result); //recurse on the remaining string
                //bactrack
                path.remove(path.size() - 1);  //backtrack to restore state
            }
        }
        return;
    }
    
    //helper to check if a string is palindrome
    //time - O(n)
    //space - constant
    private boolean isPalindrome(String current) {
        int low = 0;
        int high = current.length() - 1;
        while(low < high)
        {
            if(current.charAt(low) != current.charAt(high))
            {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
}
