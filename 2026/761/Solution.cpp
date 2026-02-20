class Solution {
public:
    string makeLargestSpecial(string s) {
        
        // Base case: empty string
        if (s.length() == 0) {
            return s;
        }
        
        // Vector to store processed mountain substrings
        vector<string> mountains;
        
        // Track balance: +1 for '1', -1 for '0'
        int balance = 0;
        
        // Start index of current mountain
        int start = 0;
        
        // Find all mountains (special substrings at current depth)
        for (int i = 0; i < s.length(); i++) {

            // Update balance
            if (s[i] == '1') {
                balance++;
            }
            else {
                balance--;
            }
            
            // When balance returns to 0, we found a complete mountain
            if (balance == 0) {
                // Extract the mountain substring
                string mountain = s.substr(start, i + 1 - start);
                
                // Extract inner part (remove outer '1' and '0')
                string inner = mountain.substr(1, mountain.length() - 2);
                
                // Recursively process inner part and wrap with '1' and '0'
                string processed = "1" + makeLargestSpecial(inner) + "0";
                
                // Add processed mountain to vector
                mountains.push_back(processed);
                
                // Move start to next mountain
                start = i + 1;
            }
        }
        
        // Sort mountains in reverse order (lexicographically largest first)
        sort(mountains.begin(), mountains.end(), greater<string>());
        
        // Concatenate all mountains
        string result = "";
        
        for (string mountain : mountains) {
            result += mountain;
        }
        
        return result;
    }
};
