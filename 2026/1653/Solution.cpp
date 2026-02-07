class Solution {
public:
    int minimumDeletions(string s) {
      
        int bCount = 0;
        int deletions = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s[i];
            
            if (currentChar == 'b') {
                bCount++;
            } else {
                deletions = min(deletions + 1, bCount);
            }
        }
        
        return deletions;
    }
};
