//1286.
class CombinationIterator {
    
    Queue<String> validCombinations = new LinkedList<>();
    TreeSet<String> validCombinationsBitMasked = new TreeSet<>();
    
    public CombinationIterator(String characters, int combinationLength) {
        backtrack(characters, 0, new StringBuilder(), combinationLength); //populate the queue
        bitMask(characters, combinationLength);
    }
    
    public String next() {
        //return validCombinations.poll();
        String answer = null;
        for(String current : validCombinationsBitMasked)
        {
            answer = current;
            break;
        }
        validCombinationsBitMasked.remove(answer);
        return answer;
    }
    
    public boolean hasNext() {
        return (validCombinationsBitMasked.size() > 0);
    }
    
    //time - O(2^n)
    //space - O(n)
    private void backtrack(String characters, int index, StringBuilder path, int combinationLength) {
        //base
        if(index == characters.length())
        {
            if(path.length() == combinationLength) //add those combinations with length same as that input
            {
                validCombinations.offer(path.toString());
            }
            return;
        }
        //logic
        //choose current character
        path.append(characters.charAt(index));
        backtrack(characters, index + 1, path, combinationLength); //recurse on the remaining string
        path.deleteCharAt(path.length() - 1); //backtrack
        backtrack(characters, index + 1, path, combinationLength); //dont choose the current char
        return;
    }
    
    //time - O(logn * 2^n)
    //space - O(n)
    private void bitMask(String characters, int combinationLength) {
        int length = characters.length(); //length of the i/p string
        int maxBitMask = 1 << length; //max bitmask for string of length 3 is 111 and the max value in this case is 1000
        for(int current = 0; current < maxBitMask; current++) 
        {
            int currentMask = current;
            int index = characters.length() - 1;
            StringBuilder eqString = new StringBuilder(); //equivalent string for the current mask
            while(currentMask > 0)
            {
                if((currentMask & 1)== 1)
                {
                    eqString.insert(0, characters.charAt(index));
                }
                index--;
                currentMask >>= 1;
            }
            if(eqString.length() == combinationLength)
            {
                validCombinationsBitMasked.add(eqString.toString());
            }
        }
        return;
    }
}
