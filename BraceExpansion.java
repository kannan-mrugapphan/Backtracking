// 1087.
// time - O(length of o/p list)
// space - call stack size = length of each entry in result list
// instead of treeset result, have a treeset for each option list in mappings
class Solution {
    public String[] expand(String s) {
        HashMap<Integer, List<Character>> mappings = new HashMap<>(); //map tracks the list of chars for each index
        int index = 0; //initail index
        for(int i = 0; i < s.length(); i++)
        {
            mappings.put(index, new ArrayList<>()); //create entry for current 
            char current = s.charAt(i);
            if(current == '{')
            {
                List<Character> options = new ArrayList<>();
                int j = i + 1; //next index after '{'
                while(s.charAt(j) != '}') //as long as '}' is not reached
                {
                    char next = s.charAt(j); //get next char
                    if(next != ',')
                    {
                        options.add(next); //add next char to option list if it is not a comma
                    }
                    j++; //go to next char
                }
                //at this point j is at '}'
                i = j; //set i to '}' so i becomes  i + 1 in next loop
                mappings.put(index, options); //update map
            }
            else
            {
                mappings.get(index).add(current);
            }
            index++;
        }
        
        TreeSet<String> result = new TreeSet<>(); //return list
        StringBuilder path = new StringBuilder();
        helper(path, result, mappings, 0);
        
        String[] answer = new String[result.size()]; //casting to array
        index = 0; //iteration ptr to fill answer
        for(String ans : result)
        {
            answer[index++] = ans;
        }
        return answer;
    }
    
    private void helper(StringBuilder path, TreeSet<String> result, HashMap<Integer, List<Character>> mappings, int index) {
        //base
        if(index == mappings.size())
        {
            //valid string is formed
            result.add(path.toString());
            return;
        }
        //logic
        List<Character> options = mappings.get(index); //get all options
        for(Character option : options)
        { 
            path.append(option); //choose current option
            helper(path, result, mappings, index + 1); //recurse
            path.deleteCharAt(path.length() - 1); //backtrack
        }
        return;
    }
}
