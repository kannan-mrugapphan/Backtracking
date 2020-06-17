// 39.
// time - O(2^n) - at each step, 2 choices, selecting or ignoring current
// space - O(n)
// same as coin change problem
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>(); //return list
        //edge
        if(candidates == null || candidates.length == 0)
        {
            return result; //return empty list
        }
        List<Integer> path = new ArrayList<>(); //path mimics tree traversal
        backtrack(candidates, 0, 0, target, path, result); //populates result
        return result;
    }
    
    private void backtrack(int[] candidates, int index, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        //base
        if(sum == target)
        {
            result.add(new ArrayList<>(path)); //add a copy of path to result
            return;
        }
        if(sum > target || index == candidates.length) //exceeded target or reached end of list
        {
            return;
        }
        //logic
        for(int i = index; i < candidates.length; i++)
        {
            //act
            //choose ith element
            path.add(candidates[i]); //add ith element to current path
            //recurse
            backtrack(candidates, i, sum + candidates[i], target, path, result); //recurse on the remaining elemnents (i is same - infinite supply of elements)
            //backtrack
            path.remove(path.size() - 1); //backtrack to explore other possiblities
        }
        return;
    }
}
