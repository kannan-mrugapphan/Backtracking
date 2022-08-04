// 698.

// brute force - similar to matchsticks to square
// have an array of size k, pick an element and place it in support array if it doesn't breach target sum
// once all elements are placed and all k indices in support array equals target sum, return true
// brute force run time - O(k^n) -> each element has k choices

//approach - pick elements that sum up to target sum, mark the selected ones as visited
// recurse with 1 less parts remaining
// time - O(k * 2^n) -> choose elements that sum up to target runs for 2^n, potentially it runs k times 

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //edge
        if(nums == null || nums.length == 0)
        {
            return false;
        }
        
        int totalSum = 0;
        for(int num : nums)
        {
            totalSum += num;
        }
        
        //total sum of all elements in nums must be divisible by k to form a valid partition
        if(totalSum % k != 0)
        {
            return false;
        }
        
        int targetSum = totalSum / k; //each index in support list must have sum equals targetSum
        boolean[] visited = new boolean[nums.length];
        return helper(nums, 0, targetSum, 0, visited, k);
    }
    
    private boolean helper(int[] nums, int index, int targetSum, int currentSum, boolean[] visited, int partitionsRemaining) {
        //base
        //once remaining aprts becomes 0, check if every index in nums is used or not
        if(partitionsRemaining == 0)
        {
            for(boolean num : visited)
            {
                if(!num)
                {
                    return false;
                }
            }
            return true;
        }
        if(currentSum == targetSum)
        {
            //one valid partition is formed
            //start picking elements from 0th index again considering the modified visited[] and 1 less partitions
            return helper(nums, 0, targetSum, 0, visited, partitionsRemaining - 1);
        }
        
        //logic
        //pick elements that potentially sum upto targetSum, so number of partitions in next recursive call becomes k - 1
        for(int i = index; i < nums.length; i++)
        {
            //if current element is available and doesn't breach target
            if(!visited[i] && currentSum + nums[i] <= targetSum)
            {
                visited[i] = true;
                if(helper(nums, i + 1, targetSum, currentSum + nums[i], visited, partitionsRemaining))
                {
                    return true;//recurse
                }
                visited[i] = false; //backtrack so ith element can go into diff partition
            }
        }
        return false;
    }
}
