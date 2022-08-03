//1220.
// time - O(5^n)
// space - O(n) -> call stack size -> depth of tree
class Solution {
    
    int MOD_VALUE = 1000000007;
    
    public int countVowelPermutation(int n) {
        //edge
        if(n <= 0)
        {
            return 0;
        }
        
        //lookup map tracks the list of available options for next letter following current letter
        HashMap<Character, char[]> lookup = new HashMap<>();
        lookup.put('a', new char[]{'e'});
        lookup.put('e', new char[]{'a', 'i'});
        lookup.put('i', new char[]{'a', 'e', 'o', 'u'});
        lookup.put('o', new char[]{'i', 'u'});
        lookup.put('u', new char[]{'a'});
        
        char[] allowedCharacters = {'a', 'e', 'i', 'o', 'u'}; //allowed letters
        int result = 0; //return value
        
        for(char vowel : allowedCharacters)
        {
            StringBuilder path = new StringBuilder();
            path.append(vowel); //start constructing string with each vowel as a start character
            result += helper(path, 1, n, lookup) % MOD_VALUE; //update ans with count of strings with start letter as vowel
        }
        
        return result % MOD_VALUE;
    }
    
    //time - O(5^n) -> worst case each letter can be any one of 5 allowed vowels
    private int helper(StringBuilder path, int index, int size, HashMap<Character, char[]> lookup) {
        //base
        if(path.length() == size)
        {
            return 1; //1 string is formed with size = i/p size
        }
        //logic
        char prevLetter = path.charAt(index - 1); //get prev letter
        int result = 0;
        for(char next : lookup.get(prevLetter))
        {
            //for each available option that can potentially be next letter
            path.append(next);
            result += helper(path, index + 1, size, lookup); //recurse to build the path string
            path.deleteCharAt(path.length() - 1); //backtrack to remove next letter
        }
        return result % MOD_VALUE;
    }
}
