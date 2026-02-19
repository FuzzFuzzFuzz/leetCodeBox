class Solution {
    public int countBinarySubstrings(String s) {

        // Track the count of the previous group of consecutive characters
        int previousGroupCount = 0;
        
        // Track the count of the current group of consecutive characters
        int currentGroupCount = 1;
        
        // Result counter for valid substrings
        int result = 0;
        
        // Iterate through the string starting from index 1
        for (int i = 1; i < s.length(); i++) {

            // If current character matches previous, we're still in the same group
            if (s.charAt(i) == s.charAt(i - 1)) {

                currentGroupCount++;
            }
            else {

                // We've reached a new group, so calculate valid substrings
                result += Math.min(previousGroupCount, currentGroupCount);
                
                // Update previous group count to current
                previousGroupCount = currentGroupCount;
                
                // Reset current group count
                currentGroupCount = 1;
            }
        }
        
        // Don't forget to add the count for the last pair of groups
        result += Math.min(previousGroupCount, currentGroupCount);
        
        return result;
    }
}
