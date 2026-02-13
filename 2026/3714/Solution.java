class Solution {
    public int longestBalanced(String s) {

        int stringLength = s.length();
        int maxLength = 1;
        
        // Check for consecutive same characters (simple balanced case)
        int consecutiveLength = 1;
        for (int i = 1; i < stringLength; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                consecutiveLength++;
                maxLength = Math.max(maxLength, consecutiveLength);
            }
            else {
                consecutiveLength = 1;
            }
        }
        
        // Track differences between character counts using prefix sums
        int countA = 0;
        int countB = 0;
        int countC = 0;
        Map<String, Integer> differenceMap = new HashMap<>();
        differenceMap.put("0,0", -1);
        
        for (int i = 0; i < stringLength; i++) {
            char currentChar = s.charAt(i);
            
            if (currentChar == 'a') {
                countA++;
            }
            else if (currentChar == 'b') {
                countB++;
            }
            else {
                countC++;
            }
            
            int diffAB = countA - countB;
            int diffAC = countA - countC;
            String key = diffAB + "," + diffAC;
            
            if (differenceMap.containsKey(key)) {
                int previousIndex = differenceMap.get(key);
                maxLength = Math.max(maxLength, i - previousIndex);
            }
            else {
                differenceMap.put(key, i);
            }
        }
        
        // Check all combinations of 2 characters by segmenting on the third
        maxLength = Math.max(maxLength, findBalancedWithTwoChars(s, 'a', 'b', 'c'));
        maxLength = Math.max(maxLength, findBalancedWithTwoChars(s, 'a', 'c', 'b'));
        maxLength = Math.max(maxLength, findBalancedWithTwoChars(s, 'b', 'c', 'a'));
        
        return maxLength;
    }
    
    // Find longest balanced substring containing only char1 and char2 (segmented by skipChar)
    private int findBalancedWithTwoChars(String s, char char1, char char2, char skipChar) {

        int maxLength = 0;
        int stringLength = s.length();
        
        for (int i = 0; i < stringLength; i++) {

            // Skip past the skipChar to find segment start
            while (i < stringLength && s.charAt(i) == skipChar) {
                i++;
            }
            
            if (i >= stringLength) {
                break;
            }
            
            int segmentStart = i;
            
            // Find segment end (before next skipChar)
            while (i < stringLength && s.charAt(i) != skipChar) {
                i++;
            }
            
            int segmentEnd = i - 1;
            
            // Process this segment with prefix difference approach
            int count1 = 0;
            int count2 = 0;
            Map<Integer, Integer> differenceIndexMap = new HashMap<>();
            differenceIndexMap.put(0, segmentStart - 1);
            
            for (int j = segmentStart; j <= segmentEnd; j++) {
                char currentChar = s.charAt(j);
                
                if (currentChar == char1) {
                    count1++;
                }
                else if (currentChar == char2) {
                    count2++;
                }
                
                int difference = count1 - count2;
                
                if (differenceIndexMap.containsKey(difference)) {
                    int previousIndex = differenceIndexMap.get(difference);
                    maxLength = Math.max(maxLength, j - previousIndex);
                }
                else {
                    differenceIndexMap.put(difference, j);
                }
            }
        }
        
        return maxLength;
    }
}
