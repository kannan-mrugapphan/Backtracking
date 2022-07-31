// 465.
// time - O(n! + E) -> n total number of people, E -> total number of transactions
//space - O(n)
class Solution {
    public int minTransfers(int[][] transactions) {
        //edge
        if(transactions == null || transactions.length == 0)
        {
            return 0;
        }
        
        //map tracks the total amount that each person owes or gets back
        //ket - personId, value - amount 
        HashMap<Integer, Integer> debts = new HashMap<>();
        for(int[] transaction : transactions)
        {
            int sender = transaction[0];
            int receiver = transaction[1];
            int amount = transaction[2];
            
            debts.put(sender, debts.getOrDefault(sender, 0) + amount); //sender has +ve balance
            debts.put(receiver, debts.getOrDefault(receiver, 0) - amount); //receiver has -ve balance
        }
        
        List<Integer> debtList = new ArrayList<>(debts.values()); //extract just the values into diff list
        return helper(debtList, 0);
    }
    
    private int helper(List<Integer> debts, int current) {
        
        //if the current person has 0 debt, then current person doesn't belong to any transaction in optimal case
        //so skip current person
        while(current < debts.size() && debts.get(current) == 0)
        {
            current++;
        }
        
        //base
        //current becomes out of bounds i.e every one is settled up
        //0 transactions are needed
        if(current == debts.size())
        {
            return 0;
        }
        
        //return value - initially inf
        int transactionsNeeded = Integer.MAX_VALUE;
        
        //for each potential person that current can settle up with
        for(int friend = current + 1; friend < debts.size(); friend++)
        {
            int friendAmount = debts.get(friend);
            //if friend has 0 debt then current can't settle up with friend, so skip friend
            if(friendAmount == 0)
            {
                continue;
            }
            //if current owes & friend gets back or vice versa, then current & friend can settle up with each other
            if((friendAmount > 0 && debts.get(current) < 0) || (friendAmount < 0 && debts.get(current) > 0))
            {
                //debts of friend gets reduced by equivalent amount of current person
                debts.set(friend, friendAmount + debts.get(current));
                //current is settled up, so recurse for next person
                //find number of trans needed for next (recurse)
                //update result
                transactionsNeeded = Math.min(transactionsNeeded, 1 + helper(debts, current + 1));
                //backtrack to settle up current with diff friend
                debts.set(friend, friendAmount);
            }
        }
        
        return transactionsNeeded; 
    }
}
