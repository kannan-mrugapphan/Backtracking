// 113.
//time - O(n) - preorder traversal
//sapce - O(h) - max call stack size is depth of tree
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        //edge
        if(root == null)
        {
            return new ArrayList<>(); //empty path
        }
        List<List<Integer>> result = new ArrayList<>(); //return list
        List<Integer> path = new ArrayList<>(); //temp list 
        backtrack(root, 0, sum, path, result);
        return result;
    }
    
    private void backtrack(TreeNode root, int sum, int target, List<Integer> path, List<List<Integer>> result) {
        //base
        if(root == null)
        {
            return;
        }
        if(root.left == null && root.right == null)
        {
            //leaf is reached
            if(root.val + sum == target)
            {
                List<Integer> temp = new ArrayList<>(path);
                temp.add(root.val);
                result.add(temp); //add a copy of path to result if sum is 0
            }
            return;
        }
        //logic
        sum += root.val; //add current root val to sum
        path.add(root.val); //add root to current path
        backtrack(root.left, sum, target, path, result); //recurse on left child
        backtrack(root.right, sum, target, path, result); //recurse on left child
        path.remove(path.size() - 1); //backtrack
    }
}
