class Solution {
    public String makeLargestSpecial(String s) {

        // Base case: empty string
        if (s.length() == 0) {
            return s;
        }
        
        // List to store processed mountain substrings
        List<String> mountains = new ArrayList<>();
        
        // Track balance: +1 for '1', -1 for '0'
        int balance = 0;
        
        // Start index of current mountain
        int start = 0;
        
        // Find all mountains (special substrings at current depth)
        for (int i = 0; i < s.length(); i++) {

            // Update balance
            if (s.charAt(i) == '1') {
                balance++;
            }
            else {
                balance--;
            }
            
            // When balance returns to 0, we found a complete mountain
            if (balance == 0) {

                // Extract the mountain substring
                String mountain = s.substring(start, i + 1);
                
                // Extract inner part (remove outer '1' and '0')
                String inner = mountain.substring(1, mountain.length() - 1);
                
                // Recursively process inner part and wrap with '1' and '0'
                String processed = "1" + makeLargestSpecial(inner) + "0";
                
                // Add processed mountain to list
                mountains.add(processed);
                
                // Move start to next mountain
                start = i + 1;
            }
        }
        
        // Sort mountains in reverse order (lexicographically largest first)
        Collections.sort(mountains, Collections.reverseOrder());
        
        // Concatenate all mountains
        StringBuilder result = new StringBuilder();
        
        for (String mountain : mountains) {
            result.append(mountain);
        }
        
        return result.toString();
    }
}
