class Solution {
    public int longestBalanced(String s) {

        int maxLength = 0;
        int n = s.length();

        // Check all possible substrings
        for (int startIndex = 0; startIndex < n; startIndex++) {

            int[] charFrequency = new int[26];
            int distinctChars = 0;

            // Expand the window from this starting position
            for (int endIndex = startIndex; endIndex < n; endIndex++) {
                
                char currentChar = s.charAt(endIndex);

                // Update distinct character count
                if (charFrequency[currentChar - 'a'] == 0) {
                    distinctChars++;
                }
                charFrequency[currentChar - 'a']++;

                int substringLength = endIndex - startIndex + 1;

                // Only check if length is divisible by distinct chars
                if (substringLength % distinctChars == 0) {
                    
                    int targetFrequency = substringLength / distinctChars;

                    if (isBalanced(charFrequency, targetFrequency)) {
                        
                        maxLength = Math.max(maxLength, substringLength);
                    }
                }
            }
        }

        return maxLength;
    }

    private boolean isBalanced(int[]charFrequency, int targetFrequency) {

        // Check if all present characters have the target frequency
        for (int frequency : charFrequency) {

            if (frequency > 0 && frequency != targetFrequency) {

                return false;
            }
        }

        return true;
    }
}
