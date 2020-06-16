// 78.
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(); //return list
        //edge
        if(nums == null || nums.length == 0)
        {
            return result; //return empty list
        }
        backtrack(nums, 0, result, new ArrayList<Integer>()); //populates result list
        return result;
    }
    
    //time - O(2^n) - total number of subsets of list of size n
    //space - O(n) - max depth of call stack = size of nums[]
    private void backtrack(int[] nums, int index, List<List<Integer>> result, List<Integer> path) {
        //path is [] when helper is first called, [] is part of output, so add to result
        //every path at the root of a subtree is part of result
        result.add(new ArrayList<Integer>(path)); //add a copy of path at every step of recursive tree
        //base
        if(index == nums.length)
        {
            return; //return if end of nums[] is reached
        }
        //logic
        for(int i = index; i < nums.length; i++)
        {
            //act
            path.add(nums[i]); //add nums[index] to path
            //recurse
            backtrack(nums, i + 1, result, path); //recurse on remaining nums[]
            //backtrack
            path.remove(path.size() - 1); //remove the last element to make path return to same state before recursive call
        }
        return;
    }
}
