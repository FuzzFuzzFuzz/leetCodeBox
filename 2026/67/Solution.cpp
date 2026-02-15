class Solution {
public:
    string addBinary(string a, string b) {

        // String to build result from right to left
        string result = "";
        
        // Pointers starting from the end of each string
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        
        // Track carry value (0 or 1)
        int carry = 0;
        
        // Continue while there are digits to process or carry exists
        while (indexA >= 0 || indexB >= 0 || carry > 0) {
            
            // Get digit from string a (0 if index out of bounds)
            int digitA = (indexA >= 0) ? a[indexA] - '0' : 0;
            
            // Get digit from string b (0 if index out of bounds)
            int digitB = (indexB >= 0) ? b[indexB] - '0' : 0;
            
            // Calculate sum of both digits plus carry
            int sum = digitA + digitB + carry;
            
            // Current digit is sum mod 2
            int currentDigit = sum % 2;
            
            // New carry is sum divided by 2
            carry = sum / 2;
            
            // Append current digit to result
            result += to_string(currentDigit);
            
            // Move pointers left
            indexA--;
            indexB--;
        }
        
        // Reverse since we built from right to left
        reverse(result.begin(), result.end());
        
        return result;
    }
};
