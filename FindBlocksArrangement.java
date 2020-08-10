// You have a word n letters long. You can spell the word out using n 6-sided wooden spelling blocks. A block can only be used once to spell the word.

// Spell the word GOOGLE using these blocks:

// 1: [G , B , A, C, D , E]
// 2: [Q , L , M, O, B, C]
// 3: [M, O, X, Q, Z, A]
// 4: [G, M, T, L, N, E]
// 5: [F, N, B, R, E, V]
// 6: [C, D, E , F, G, H]

// In this example, you can spell the word by arranging the blocks in this order [1, 2, 3, 6, 4, 5]

// The return value is an array of size n, containing the order in which you would arrange the blocks to spell the word. There may be multiple solutions, just return one of them.

//time - O(n!)
//space - O(n)
public class Main {
    public static void main(String[] args) {
        char[][] blocks = { { 'G', 'B', 'A', 'C', 'D', 'E' }, { 'Q', 'L', 'M', 'O', 'B', 'C' },
			{ 'M', 'O', 'X', 'Q', 'Z', 'A' }, { 'G', 'M', 'T', 'L', 'N', 'E' }, { 'F', 'N', 'B', 'R', 'E', 'V' },
			{ 'C', 'D', 'E', 'F', 'G', 'H' } };
	    String word = "GOOGLE";
        System.out.println(findBlocks(blocks, word));
    }
    
    private static List<List<Integer>> findBlocks(char[][] blocks, String word) {
        //edge
        if(blocks == null || blocks.length == 0 || blocks[0].length == 0 || word == null || word.length() == 0)
        {
            return new ArrayList<>();
        }
        //number of blocks is equal to number of letters in the given word
        List<List<Integer>> result = new ArrayList<>(); 
        //tracks which letters are present in which blocks
        HashMap<Character, HashSet<Integer>> lettersAtBlocks = new HashMap<>(); 
        for(int i = 1; i <= blocks.length; i++)
        {
            char[] block = blocks[i - 1]; //blocks number starts from 1
            for(char letter : block)
            {
                if(!lettersAtBlocks.containsKey(letter))
                {
                    lettersAtBlocks.put(letter, new HashSet<>());
                }
                lettersAtBlocks.get(letter).add(i); //add an entry in map - letter in ith block
            }
        }
        boolean[] visited = new boolean[blocks.length + 1]; //use each block only one - so visited[]
        backtrack(lettersAtBlocks, word, result, new ArrayList<>(), visited, 0);
        return result;
    }
    
    private static void backtrack(HashMap<Character, HashSet<Integer>> lettersAtBlocks, String word, List<List<Integer>> result, List<Integer> path, boolean[] visited, int index) {
        //base
        if(index == word.length())
        {
            result.add(new ArrayList<>(path));
            return;
        }
        //logic
        char current = word.charAt(index);//find block number for current char
        HashSet<Integer> possibleBlocks = lettersAtBlocks.get(current); //possible blocks which has the current letter
        if(possibleBlocks != null)
        {
            for(Integer blockNumber : possibleBlocks)
            {
                if(!visited[blockNumber]) //current block is not used so far
                {
                    visited[blockNumber] = true; //mark as visited and add to result
                    path.add(blockNumber);
                    backtrack(lettersAtBlocks, word, result, path, visited, index + 1); //recurse
                    visited[blockNumber] = false; //backtrack
                    path.remove(path.size() - 1);
                }
            }
        }
        return;
    }
}
