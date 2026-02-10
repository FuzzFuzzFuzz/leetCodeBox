class Solution {
    public int longestBalanced(int[] nums) {

        // Track the maximum length of balanced subarray found
        int maxLength = 0;
        
        // Try all possible starting positions
        for (int start = 0; start < nums.length; start++) {
            
            // Use sets to track distinct even and odd numbers
            Set<Integer> distinctEvens = new HashSet<>();
            Set<Integer> distinctOdds = new HashSet<>();
            
            // Expand the subarray from the current start position
            for (int end = start; end < nums.length; end++) {

                int currentNum = nums[end];
                
                // Add the current number to the appropriate set
                if (currentNum % 2 == 0) {
                    distinctEvens.add(currentNum);
                } 
                else {
                    distinctOdds.add(currentNum);
                }
                
                // Check if the subarray is balanced
                if (distinctEvens.size() == distinctOdds.size()) {

                    int currentLength = end - start + 1;
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }
      
        return maxLength;
    }
}
