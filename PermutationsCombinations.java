// 46.
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return new ArrayList<>(); //empty list
        }
        
        HashMap<Integer, Integer> counts = new HashMap<>(); //freq of number in nums[]
        for(int num : nums) 
        {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        List<List<Integer>> result = new ArrayList<>(); //return list
        List<Integer> path = new ArrayList<>(); //inital path to traverse the tree
        dfs(nums, counts, path, result); //populates result
        return result;
    }
    
    //time - O(n!) - total number of permutations
    //space - O(n) - call stack size is at max n
    private void dfs(int[] nums, HashMap<Integer, Integer> counts, List<Integer> path, List<List<Integer>> result) {
        //base
        if(path.size() == nums.length)
        {
            result.add(new ArrayList<>(path)); //add a copy of path to result
            return;
        }
        //logic
        for(int i = 0; i < nums.length; i++)
        {
            if(counts.get(nums[i]) > 0)
            {
                //act
                counts.put(nums[i], counts.get(nums[i]) - 1); // reduce count of nums[i] by 1
                path.add(nums[i]); //add nums[i] to path
                //recurse
                dfs(nums, counts, path, result); 
                //backtrack
                path.remove(path.size() - 1); //remove last element to revert to prev state
                counts.put(nums[i], counts.get(nums[i]) + 1); //restore counts
            }
        }
        return;
    }
}

// 77.
// time - O(n! / k!(n - k)!) - number of combinations = nCk
// space - O(n + k)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>(); //return list
        List<Integer> path = new ArrayList<>(); 
        helper(n, k, path, result, 1);
        return result;
    }
    
    private void helper(int n, int k, List<Integer> path, List<List<Integer>> result, int current) {
        //base
        if(path.size() == k)
        {
            //k number are already choosen
            result.add(new ArrayList<>(path)); //add a copy of current path to result
            return;
        }
        if(path.size() > k)
        {
            return;
        }
        //logic
        for(int number = current; number <= n; number++)
        {
            path.add(number); //choose current
            helper(n, k, path, result, number + 1); //recurse
            path.remove(path.size() - 1); //remove last element to revrt to prev state
        }
        return;
    }
}
