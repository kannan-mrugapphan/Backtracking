// 679.
// time - O(n^3 * 6^n)
// space - O(n)

//potentail memoization approach -> if a valid combination of cards list is already seen, cache it in a map and return result

class Solution {
    public boolean judgePoint24(int[] cards) {
        List<Double> cardss = new ArrayList<>();
        for(int card : cards)
        {
            cardss.add((double) card);
        }
        
        return isValid(cardss);
    }
    
    //double list because of division operation
    private boolean isValid(List<Double> cards) {
        //base
        if(cards.size() == 1)
        {
            //only one card remaining
            //valid cards list
            if(Math.abs(cards.get(0) - 24.0) <= 0.0001)
            {
                return true;
            }
            return false;
        }
        //logic
        //perform valid arithmetic operation on any 2 elements
        //pick elements by nested for loop
        for(int i = 0; i < cards.size() - 1; i++)
        {
            for(int j = i + 1; j < cards.size(); j++)
            {
                //child array size will be 1 smaller than cards array as first,seconde is used
                List<Double> child = new ArrayList<>(); 
                for(int other = 0; other < cards.size(); other++)
                {
                    if(other == i || other == j)
                    {
                        continue;
                    }
                    //copy other elements into array
                    child.add(cards.get(other));
                }
                
                double first = cards.get(i);
                double second = cards.get(j);
                
                //generate 6 copies for 6 possible opns
                List<Double> addition = new ArrayList<>(child);
                List<Double> multiplication = new ArrayList<>(child);
                List<Double> subtraction = new ArrayList<>(child);
                List<Double> inverseSubtraction = new ArrayList<>(child);
                List<Double> division = new ArrayList<>(child);
                List<Double> inverseDivision = new ArrayList<>(child);
                
                
                //add computation of first and second into copies 
                addition.add(first + second);
                multiplication.add(first * second);
                subtraction.add(first - second);
                inverseSubtraction.add(second - first);
                inverseDivision.add(second / first);
                division.add(first / second);
                
                if(isValid(addition) || isValid(multiplication) || isValid(subtraction) || isValid(inverseSubtraction) || isValid(division) || isValid(inverseDivision))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
}
