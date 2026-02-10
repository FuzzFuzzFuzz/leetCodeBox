class Solution {
public:
    int longestBalanced(vector<int>& nums) {
      
        // Track the maximum length of balanced subarray found
        int maxLength = 0;
        
        // Try all possible starting positions
        for (int start = 0; start < nums.size(); start++) {

            // Use sets to track distinct even and odd numbers
            unordered_set<int> distinctEvens;
            unordered_set<int> distinctOdds;
            
            // Expand the subarray from the current start position
            for (int end = start; end < nums.size(); end++) {

                int currentNum = nums[end];
                
                // Add the current number to the appropriate set
                if (currentNum % 2 == 0) {
                    distinctEvens.insert(currentNum);
                }
                else {
                    distinctOdds.insert(currentNum);
                }
                
                // Check if the subarray is balanced
                if (distinctEvens.size() == distinctOdds.size()) {

                    int currentLength = end - start + 1;
                    maxLength = max(maxLength, currentLength);
                }
            }
        }
        
        return maxLength;
    }
};
